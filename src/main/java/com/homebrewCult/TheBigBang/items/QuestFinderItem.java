package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.init.ModFeatures;
import com.homebrewCult.TheBigBang.world.ModWorldGen;
import com.homebrewCult.TheBigBang.world.danger_sign_structures.PlateauSignStructure;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class QuestFinderItem extends Item {

    public static final String QUEST_COORD_X_KEY = "quest_coord_x";
    public static final String QUEST_COORD_Z_KEY = "quest_coord_z";

    public QuestFinderItem(Properties properties) {
        super(properties);
        this.addPropertyOverride(new ResourceLocation("angle"), new IItemPropertyGetter() {
            @OnlyIn(Dist.CLIENT)
            public float call(ItemStack stack, @Nullable World world, @Nullable LivingEntity entity) {
                if (entity == null) {
                    return 0.0F;
                } else {
                    if (world == null)
                        world = entity.world;

                    double rot;
                    if (world.dimension.isSurfaceWorld()) {
                        double playerYaw = entity.rotationYaw;
                        playerYaw = MathHelper.positiveModulo(playerYaw / 360.0D, 1.0D);
                        double structureAngle = this.getSpawnToAngle(stack, entity) / (double)((float)Math.PI * 2F);
                        rot = 0.5D - (playerYaw - 0.25D - structureAngle);
                    } else {
                        rot = Math.random();
                    }
                    return MathHelper.positiveModulo((float)rot, 1.0F);
                }
            }

            @OnlyIn(Dist.CLIENT)
            private double getSpawnToAngle(ItemStack stack, Entity entity) {
                CompoundNBT nbt = stack.getOrCreateTag();
                if(nbt.contains(QUEST_COORD_X_KEY) && nbt.contains(QUEST_COORD_Z_KEY))
                        return Math.atan2(nbt.getInt(QUEST_COORD_Z_KEY) - entity.posZ, nbt.getInt(QUEST_COORD_X_KEY) - entity.posX);
                return 0.0;
            }
        });

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if(stack.getItem() instanceof QuestFinderItem) {
            if(!worldIn.isRemote) {
                PlateauSignStructure structure = ModFeatures.DANGER_SIGN_PLATEAU_STRUCTURE.get();
                BlockPos blockPos = structure.findNearest(worldIn, worldIn.getChunkProvider().getChunkGenerator(), new BlockPos(playerIn), 100, false);
                if (blockPos != null) {
                    TheBigBang.print("Found structure at X:" + blockPos.getX() + " Z:" + blockPos.getZ());
                    CompoundNBT nbt = stack.getOrCreateTag();
                    nbt.putInt(QUEST_COORD_X_KEY, blockPos.getX());
                    nbt.putInt(QUEST_COORD_Z_KEY, blockPos.getZ());
                }
            }
            return new ActionResult<>(ActionResultType.SUCCESS, stack);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
