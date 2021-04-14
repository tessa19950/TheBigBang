package com.homebrewCult.TheBigBang.items;

import java.util.List;

import com.homebrewCult.TheBigBang.TheBigBang;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.LootTable;

public class GephardItem extends SwordItem {

	public GephardItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
		if(!player.world.isRemote) {
			LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld)player.world)).withRandom(player.getRNG()).withLuck(player.getLuck());
			lootcontext$builder = lootcontext$builder.withParameter(LootParameters.THIS_ENTITY, player).withParameter(LootParameters.POSITION, new BlockPos(player)).withParameter(LootParameters.DAMAGE_SOURCE, DamageSource.GENERIC);
			LootTable lootTable = entity.world.getServer().getLootTableManager().getLootTableFromLocation(entity.getType().getLootTable());
			List<ItemStack> items = lootTable.generate(lootcontext$builder.build(LootParameterSets.ENTITY));
			TheBigBang.print("LootTable of entity " + entity.getName().getString());
			for(ItemStack i : items) {
				entity.getEntityWorld().addEntity(new ItemEntity(entity.world, entity.posX, entity.posY, entity.posZ, i));
			}
		}
		return super.onLeftClickEntity(stack, player, entity);
	}
}
