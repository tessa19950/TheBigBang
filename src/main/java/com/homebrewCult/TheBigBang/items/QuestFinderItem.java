package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.init.ModFeatures;
import com.homebrewCult.TheBigBang.world.danger_sign_structures.DesertSignStructure;
import com.homebrewCult.TheBigBang.world.danger_sign_structures.PlateauSignStructure;
import com.homebrewCult.TheBigBang.world.danger_sign_structures.StoneSignStructure;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldInfo;
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
            private double rotation;
            @OnlyIn(Dist.CLIENT)
            private double rot;
            @OnlyIn(Dist.CLIENT)
            private long lastUpdateTick;

            @OnlyIn(Dist.CLIENT)
            public float call(ItemStack stack, @Nullable World world, @Nullable LivingEntity entity) {
                if (entity == null)
                    return 0.0F;
                if (world == null)
                    world = entity.world;
                double d0;
                CompoundNBT nbt = stack.getOrCreateTag();
                if(world.dimension.isSurfaceWorld() && nbt.contains(QUEST_COORD_X_KEY) && nbt.contains(QUEST_COORD_Z_KEY)) {
                    double x = nbt.getInt(QUEST_COORD_X_KEY);
                    double z = nbt.getInt(QUEST_COORD_Z_KEY);
                    double d2 = Math.atan2(z - entity.getPosZ(), x - entity.getPosX()) / (double)((float)Math.PI * 2F);
                    double d1 = MathHelper.positiveModulo(entity.rotationYaw / 360.0D, 1.0D);
                    d0 = 0.5D - (d1 - 0.25D - d2);
                } else {
                    d0 = Math.random();
                }
                return MathHelper.positiveModulo(this.wobble(world, d0), 1.0F);
            }

            @OnlyIn(Dist.CLIENT)
            private float wobble(World worldIn, double angle) {
                if (worldIn.getGameTime() != this.lastUpdateTick) {
                    this.lastUpdateTick = worldIn.getGameTime();
                    double d0 = angle - this.rotation;
                    d0 = MathHelper.positiveModulo(d0 + 0.5D, 1.0D) - 0.5D;
                    this.rot += d0 * 0.1D;
                    this.rot *= 0.8D;
                    this.rotation = MathHelper.positiveModulo(this.rotation + this.rot, 1.0D);
                }
                return (float) this.rotation;
            }
        });
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(playerIn.getHeldItem(handIn).getItem() instanceof QuestFinderItem) {
            if(!worldIn.isRemote)
                new Thread(() -> searchNearestStructure(worldIn, playerIn, handIn)).start();
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    protected void searchNearestStructure(World worldIn, PlayerEntity playerIn, Hand handIn) {
        BlockPos blockPos = getNearestStructurePos(worldIn, new BlockPos(playerIn));
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (blockPos != null && stack.getItem() instanceof QuestFinderItem) {
            TheBigBang.print("Found structure at X:" + blockPos.getX() + " Z:" + blockPos.getZ());
            CompoundNBT nbt = stack.getOrCreateTag();
            nbt.putInt(QUEST_COORD_X_KEY, blockPos.getX());
            nbt.putInt(QUEST_COORD_Z_KEY, blockPos.getZ());
            stack.setTag(nbt);
            playerIn.setHeldItem(handIn, stack);
        }
    }

    protected BlockPos getNearestStructurePos(World worldIn, BlockPos pos) {
        if(worldIn.isRemote)
            return null;

        PlateauSignStructure plateauStructure = ModFeatures.DANGER_SIGN_PLATEAU_STRUCTURE.get();
        StoneSignStructure stoneStructure = ModFeatures.DANGER_SIGN_STONE_STRUCTURE.get();
        DesertSignStructure desertStructure = ModFeatures.DANGER_SIGN_DESERT_STRUCTURE.get();
        if(plateauStructure == null || stoneStructure == null || desertStructure == null)
            return null;

        BlockPos nearest = findNearestStructure(plateauStructure, (ServerWorld) worldIn, pos);
        BlockPos nearestStone = findNearestStructure(stoneStructure, (ServerWorld) worldIn, pos);
        BlockPos nearestDesert = findNearestStructure(desertStructure, (ServerWorld) worldIn, pos);
        if(isXCloser(pos, nearestStone, nearest))
            nearest = nearestStone;
        if(isXCloser(pos, nearestDesert, nearest))
            nearest = nearestDesert;
        return nearest;
    }

    protected BlockPos findNearestStructure(Structure<?> structure, ServerWorld world, BlockPos pos) {
        return world.findNearestStructure(structure.getStructureName(), pos, 100, false);
    }

    private boolean isXCloser(BlockPos a, BlockPos x, BlockPos y) {
        if(x == null)
            return false;
        if(y == null)
            return true;
        return a.distanceSq(x.getX(), x.getY(), x.getZ(), true) < a.distanceSq(y.getX(), y.getY(), y.getZ(), true);
    }
}
