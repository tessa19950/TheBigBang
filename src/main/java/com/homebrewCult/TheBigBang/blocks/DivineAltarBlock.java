package com.homebrewCult.TheBigBang.blocks;

import java.util.Random;

import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import com.homebrewCult.TheBigBang.init.ModSounds;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class DivineAltarBlock extends AbstractFurnaceBlock {

	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 13.0D, 16.0D);

	public DivineAltarBlock(Properties properties) {
		super(properties);
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	protected void interactWith(World worldIn, BlockPos pos, PlayerEntity player) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof DivineAltarTile) {
			NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider)tileEntity, tileEntity.getPos());
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (stateIn.get(LIT)) {
			DivineAltarTile tile = (DivineAltarTile) worldIn.getTileEntity(pos);
			double d0 = (double)pos.getX() + 0.5D;
			double d1 = (double)pos.getY();
			double d2 = (double)pos.getZ() + 0.5D;
			if (tile.timer % 5 == 0) {
				worldIn.playSound(d0, d1, d2, ModSounds.MONSTER_FURNACE_LIT, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
			}
			if (tile.timer % 10 == 0) {
				worldIn.addParticle(ModParticleTypes.HOLY_HEXAGRAM.get(), d0, d1 + 0.75D, d2, 0.0D, 0.02D, 0.0D);
			}
			tile.timer++;
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new DivineAltarTile();
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
}
