package com.homebrewCult.TheBigBang.blocks;

import java.util.Map;

import com.google.common.collect.Maps;
import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import com.homebrewCult.TheBigBang.init.ModItems;
import com.homebrewCult.TheBigBang.init.ModRecipeTypes;
import com.homebrewCult.TheBigBang.inventory.DivineAltarContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;

public class DivineAltarTile extends AbstractFurnaceTileEntity {

	public DivineAltarContainer container;
	private final NonNullList<ItemStack> clientInventory = NonNullList.withSize(3, ItemStack.EMPTY);
	public int timer = 0;
	
	public DivineAltarTile() {
		super(ModBlocks.DIVINE_ALTAR_TILE, ModRecipeTypes.REFINING_RECIPE_TYPE);
	}
	
	public DivineAltarTile(TileEntityType<?> tileTypeIn, IRecipeType<? extends AbstractCookingRecipe> recipeTypeIn) {
		super(tileTypeIn, recipeTypeIn);
	}

	public static Map<Item, Integer> getBurnTimes() {		
		Map<Item, Integer> map = Maps.newLinkedHashMap();
		addItemBurnTime(map, Items.NETHER_STAR, 1600);
		addItemBurnTime(map, Items.DRAGON_HEAD, 1600);
		addItemBurnTime(map, ModItems.DRAKES_BLOOD, 800);
		addItemBurnTime(map, ModItems.SAP_OF_ANCIENT_TREE, 800);
		addItemBurnTime(map, ModItems.DARK_GOLEM_STONE, 800);
		addItemBurnTime(map, ModItems.GOLEM_STONE, 800);
		addItemBurnTime(map, ModItems.DRAGON_SKIN, 400);
		addItemBurnTime(map, ModItems.DARK_YETI_HORN, 400);
		addItemBurnTime(map, ModItems.YETI_HORN, 400);
		addItemBurnTime(map, Items.WITHER_SKELETON_SKULL, 400);
		addItemBurnTime(map, Items.SKELETON_SKULL, 400);
		addItemBurnTime(map, Items.ZOMBIE_HEAD, 400);
		addItemBurnTime(map, Items.CREEPER_HEAD, 400);
		addItemBurnTime(map, ModItems.DRAKES_SKULL, 200);
		addItemBurnTime(map, ModItems.DARK_STONE_GOLEM_RUBBLE, 200);
		addItemBurnTime(map, ModItems.STONE_GOLEM_RUBBLE, 200);
		addItemBurnTime(map, Items.ENDER_PEARL, 200);
		addItemBurnTime(map, Items.GHAST_TEAR, 200);
		addItemBurnTime(map, Items.SHULKER_SHELL, 200);
		addItemBurnTime(map, ModItems.COLD_EYE_TAIL, 100);
		addItemBurnTime(map, ModItems.CURSE_EYE_TAIL, 100);
		addItemBurnTime(map, ModItems.EVIL_EYE_TAIL, 100);
		addItemBurnTime(map, ModItems.UNDEAD_CHARM, 100);
		addItemBurnTime(map, Items.BLAZE_ROD, 100);
		addItemBurnTime(map, Items.SPIDER_EYE, 100);
		addItemBurnTime(map, Items.SLIME_BALL, 100);
		addItemBurnTime(map, ModItems.OCTOPUS_LEG, 50);
		addItemBurnTime(map, ModItems.PIGS_RIBBON, 50);
		addItemBurnTime(map, ModItems.DARK_JRYETI_SKIN, 50);
		addItemBurnTime(map, ModItems.JRYETI_SKIN, 50);
		addItemBurnTime(map, ModItems.ORANGE_MUSHROOM_CAP, 50);
		addItemBurnTime(map, ModItems.BLUE_MUSHROOM_CAP, 50);
		addItemBurnTime(map, Items.MAGMA_CREAM, 50);
		addItemBurnTime(map, Items.PHANTOM_MEMBRANE, 50);
		addItemBurnTime(map, ModItems.RED_SNAIL_SHELL, 25);
		addItemBurnTime(map, ModItems.BLUE_SNAIL_SHELL, 25);
		addItemBurnTime(map, ModItems.GREEN_SNAIL_SHELL, 25);
		addItemBurnTime(map, Items.ROTTEN_FLESH, 25);
		addItemBurnTime(map, Items.BONE, 25);
		return map;
	}

	@Override
	public void tick() {
		super.tick();
	}

	private static void addItemBurnTime(Map<Item, Integer> map, IItemProvider itemProvider, int burnTimeIn) {
		map.put(itemProvider.asItem(), burnTimeIn);
	}
	
	protected int getBurnTime(ItemStack stack) {
		if (stack.isEmpty()) {
			return 0;
		} else {
			Item item = stack.getItem();
			int ret = stack.getBurnTime();
			return (ForgeEventFactory.getItemBurnTime(stack, ret == -1 ? getBurnTimes().getOrDefault(item, 0) : ret));
		}
	}
	
	public static boolean isFuel(ItemStack stack) {
		int ret = stack.getBurnTime();
		return (ForgeEventFactory.getItemBurnTime(stack, ret == -1 ? getBurnTimes().getOrDefault(stack.getItem(), 0) : ret)) > 0;
	}


	public NonNullList<ItemStack> getClientInventory() {
		return clientInventory;
	}

	@Override
	public void read(CompoundNBT compound) {
		this.clientInventory.clear();
		ItemStackHelper.loadAllItems(compound, this.clientInventory);
		super.read(compound);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		CompoundNBT nbt = super.write(compound);
		this.writeItems(nbt);
		return nbt;
	}

	@Override
	public CompoundNBT getUpdateTag() {
		CompoundNBT nbt = super.getUpdateTag();
		nbt = this.writeItems(new CompoundNBT());
		return nbt;
	}

	@Nullable
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(this.pos, 13, this.getUpdateTag());
	}

	private CompoundNBT writeItems(CompoundNBT compound) {
		super.write(compound);
		ItemStackHelper.saveAllItems(compound, this.items, true);
		return compound;
	}
	
	@Override
	protected ITextComponent getDefaultName() {
		return new StringTextComponent("Divine Altar");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory playerInventory) {
		return new DivineAltarContainer(id, playerInventory, this, this.furnaceData);
	}
}
