package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.entities.SnailShellEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class SnailShellItem extends Item {
    public SnailShellItem(Properties properties) {
        super(properties);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isRemote) {
            SnailShellEntity snailShell = new SnailShellEntity(worldIn, playerIn, itemstack);
            snailShell.setItem(itemstack);
            snailShell.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.addEntity(snailShell);
        }

        if (!playerIn.abilities.isCreativeMode)
            itemstack.shrink(1);

        playerIn.addStat(Stats.ITEM_USED.get(this));
        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }
}
