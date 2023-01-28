package com.homebrewCult.TheBigBang.world;

import com.google.common.collect.ImmutableMap;
import com.homebrewCult.TheBigBang.util.MathUtility;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CaveRoomConfig implements IPlacementConfig {
    public final int chance;

    public CaveRoomConfig(int chance) {
        this.chance = chance;
    }

    public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
        return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("chance"), ops.createInt(this.chance))));
    }

    public static CaveRoomConfig deserialize(Dynamic<?> data) {
        int i = data.get("chance").asInt(0);
        return new CaveRoomConfig(i);
    }

    public static class CaveRoom extends Placement<CaveRoomConfig> {
        public CaveRoom(Function<Dynamic<?>, ? extends CaveRoomConfig> configIn) {
            super(configIn);
        }

        @Override
        public Stream<BlockPos> getPositions(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generatorIn, Random random, CaveRoomConfig configIn, BlockPos pos) {
            if (random.nextFloat() < 1.0F / (float)configIn.chance) {
                int x = random.nextInt(16);
                int z = random.nextInt(16);
                BlockPos surface = worldIn.getHeight(Heightmap.Type.MOTION_BLOCKING, pos.add(x, 0, z));
                int y = MathUtility.intInRange(random, 16, Math.max(16, surface.getY() - 8));
                return Stream.of(new BlockPos(surface.getX(), y, surface.getZ()));
            } else {
                return Stream.empty();
            }
        }
    }
}
