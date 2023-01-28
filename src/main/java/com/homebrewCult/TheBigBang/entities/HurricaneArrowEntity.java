package com.homebrewCult.TheBigBang.entities;

import com.homebrewCult.TheBigBang.init.ModEntities;
import com.homebrewCult.TheBigBang.init.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class HurricaneArrowEntity extends BigBangArrowEntity {
	
	public HurricaneArrowEntity(EntityType<?> type, World worldIn) {
		super(ModEntities.HURRICANE_ARROW, worldIn);
	}
	
	public HurricaneArrowEntity(World worldIn) {
		super(ModEntities.HURRICANE_ARROW, worldIn);
	}
	
	public HurricaneArrowEntity(World worldIn, double x, double y, double z) {
		super(ModEntities.HURRICANE_ARROW, worldIn, x, y, z);
	}

	public HurricaneArrowEntity(World worldIn, LivingEntity shooter) {
		super(ModEntities.HURRICANE_ARROW, worldIn, shooter);
	}
	
	@Override
	protected void onHit(RayTraceResult raytraceResultIn) {
		super.onHit(raytraceResultIn);
		RayTraceResult.Type trace = raytraceResultIn.getType();
		boolean flag = trace == RayTraceResult.Type.ENTITY || trace == RayTraceResult.Type.BLOCK;
		if (flag && !this.world.isRemote)
			this.remove();
		if (trace == RayTraceResult.Type.ENTITY) {
			float pitch = 0.9F + world.rand.nextFloat() * 0.2F;
			world.playSound(null, getPosition(), ModSounds.SNIPING_ARROW_HIT, SoundCategory.PLAYERS, 1, pitch);
		}
	}

}
