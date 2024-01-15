package com.homebrewCult.TheBigBang.world.maple_gardens_features;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class MapleTreeFeature extends AbstractTreeFeature<BaseTreeFeatureConfig> {
	
	private static final BlockState DEFAULT_TRUNK = Blocks.OAK_LOG.getDefaultState();
	private static final BlockState DEFAULT_LEAF = Blocks.AIR.getDefaultState();
	public final int minTreeHeight;
	public final BlockState trunk;
	public BlockState leaf;
	
	public MapleTreeFeature(Function<Dynamic<?>, ? extends BaseTreeFeatureConfig> configFactoryIn) {
		this(configFactoryIn, 5, DEFAULT_TRUNK, DEFAULT_LEAF, false);
	}

	public MapleTreeFeature(Function<Dynamic<?>, ? extends BaseTreeFeatureConfig> configFactoryIn, int minTreeHeightIn, BlockState trunkState, BlockState leafState, boolean vinesGrowIn) {
		super(configFactoryIn);
		this.minTreeHeight = minTreeHeightIn;
		this.trunk = trunkState;
		this.leaf = leafState;
	}

	@Override
	protected boolean place(IWorldGenerationReader generationReader, Random rand, BlockPos positionIn, Set<BlockPos> p_225557_4_, Set<BlockPos> p_225557_5_, MutableBoundingBox boundingBoxIn, BaseTreeFeatureConfig configIn) {
		/*
		if(this.leaf.getBlock().equals(Blocks.AIR)) {
			this.leaf = rand.nextDouble() > 0.2d ? ModBlocks.RED_MAPLE_LEAVES.getDefaultState() : ModBlocks.YELLOW_MAPLE_LEAVES.getDefaultState();
		}
		
		int i = this.getHeight(rand);
		boolean flag = true;
		if (position.getY() >= 1 && position.getY() + i + 1 <= worldIn.getMaxHeight()) {
			for(int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
				int k = 1;
				if (j == position.getY()) {
					k = 0;
				}

				if (j >= position.getY() + 1 + i - 2) {
					k = 2;
				}

				BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
				for(int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
					for(int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
						if (j >= 0 && j < worldIn.getMaxHeight()) {
							if (!func_214587_a(worldIn, blockpos$mutableblockpos.setPos(l, j, i1))) {
								flag = false;
							}
						} else {
							flag = false;
						}
					}
				}
			}

			if (!flag) {
				return false;
			} else if (isSoil(worldIn, position.down(), getSapling()) && position.getY() < worldIn.getMaxHeight() - i - 1) {
				this.setDirtAt(worldIn, position.down(), position);
	            for(int l2 = position.getY() - 3 + i; l2 <= position.getY() + i; ++l2) {
	               int l3 = l2 - (position.getY() + i);
	               int j4 = 1 - l3 / 2;

	               for(int j1 = position.getX() - j4; j1 <= position.getX() + j4; ++j1) {
	                  int k1 = j1 - position.getX();

	                  for(int l1 = position.getZ() - j4; l1 <= position.getZ() + j4; ++l1) {
	                     int i2 = l1 - position.getZ();
	                     if (Math.abs(k1) != j4 || Math.abs(i2) != j4 || rand.nextInt(2) != 0 && l3 != 0) {
	                        BlockPos blockpos = new BlockPos(j1, l2, l1);
	                        if (isAirOrLeaves(worldIn, blockpos) || func_214576_j(worldIn, blockpos)) {
	                           this.setLogState(changedBlocks, worldIn, blockpos, this.leaf, p_208519_5_);
	                        }
	                     }
	                  }
	               }
	            }          
	            return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		 */
		return true;
	}

	protected int getHeight(Random random) {
		return this.minTreeHeight + random.nextInt(3);
	}
}
