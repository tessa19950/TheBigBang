package com.homebrewCult.TheBigBang.blocks;

import java.util.List;
import java.util.Random;

import com.homebrewCult.TheBigBang.entities.ManaRockEntity;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import com.homebrewCult.TheBigBang.init.ModSounds;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class DivineAltarBlock extends AbstractFurnaceBlock {

	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 13.0D, 16.0D);
	public static final int MANA_ROCK_RADIUS = 4;
	public static final int MANA_ROCK_CAP = 3;
	protected static final IntegerProperty MANA_ROCK_COUNT = IntegerProperty.create("mana_rock_count", 0, MANA_ROCK_CAP);

	public DivineAltarBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(MANA_ROCK_COUNT, 0));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return super.getStateForPlacement(context);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		updateManaRocks(state, worldIn, pos);
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(MANA_ROCK_COUNT);
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	protected void interactWith(World worldIn, BlockPos pos, PlayerEntity player) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		updateManaRocks(worldIn.getBlockState(pos), worldIn, pos);
		if (tileEntity instanceof DivineAltarTile) {
			NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider)tileEntity, tileEntity.getPos());
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		TileEntity tile = worldIn.getTileEntity(pos);
		if(tile instanceof DivineAltarTile) {
			DivineAltarTile altarTile = (DivineAltarTile) tile;
			Vec3d altarPos = new Vec3d(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
			for (int i = 0; i < altarTile.getClientManaRocks().size(); i++) {
				Entity rock = altarTile.getClientManaRocks().get(i);
				if((altarTile.timer + rock.getEntityId()) % 10 != 1)
					continue;
				Vec3d rockPos = rock.getPositionVec().add(0, 1, 0);
				double p0 = rockPos.x + (rand.nextDouble() - 0.5D) * 0.4;
				double p1 = rockPos.y + (rand.nextDouble() - 0.5D) * 0.4;
				double p2 = rockPos.z + (rand.nextDouble() - 0.5D) * 0.4;
				double s0 = (altarPos.x - rockPos.x) * 0.1;
				double s1 = (altarPos.y - rockPos.y) * 0.1;
				double s2 = (altarPos.z - rockPos.z) * 0.1;
				worldIn.addParticle(ModParticleTypes.SYMBOL_GOLD.get(), p0, p1, p2, s0, s1, s2);
			}
			if (stateIn.get(LIT)) {
				double d0 = pos.getX() + 0.5D;
				double d1 = pos.getY();
				double d2 = pos.getZ() + 0.5D;
				if (altarTile.timer % 5 == 0)
					worldIn.playSound(d0, d1, d2, ModSounds.MONSTER_FURNACE_LIT, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
				if (altarTile.timer % 10 == 0)
					worldIn.addParticle(ModParticleTypes.HOLY_HEXAGRAM.get(), d0, d1 + 0.75D, d2, 0.0D, 0.02D, 0.0D);
			}
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

	public static void updateManaRocks(BlockState state, World worldIn, BlockPos pos) {
		List<Entity> rocks = findManaRocks(worldIn, pos);
		int rockCount = Math.min(rocks.size(), MANA_ROCK_CAP);
		if(state.get(MANA_ROCK_COUNT) != rockCount) {
			worldIn.setBlockState(pos, state.with(MANA_ROCK_COUNT, rockCount));
		}
		TileEntity tile = worldIn.getTileEntity(pos);
		if(tile instanceof DivineAltarTile) {
			((DivineAltarTile) tile).forceUdateCookTime();
			if (worldIn.isRemote) {
				((DivineAltarTile) tile).getClientManaRocks().clear();
				((DivineAltarTile) tile).getClientManaRocks().addAll(rocks);
			}
		}
	}

	public static List<Entity> findManaRocks(World worldIn, BlockPos pos) {
		Vec3d vec = new Vec3d(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
		Vec3d boxA = vec.add(MANA_ROCK_RADIUS, MANA_ROCK_RADIUS - 1, MANA_ROCK_RADIUS);
		Vec3d boxB = vec.add(-MANA_ROCK_RADIUS, -MANA_ROCK_RADIUS + 1, -MANA_ROCK_RADIUS);
		return worldIn.getEntitiesWithinAABB(ManaRockEntity.class, new AxisAlignedBB(boxA, boxB));
	}
}
