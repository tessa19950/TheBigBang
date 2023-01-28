package com.homebrewCult.TheBigBang.entities;

import com.homebrewCult.TheBigBang.init.ModEffects;
import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class BombArrowEntity extends BigBangArrowEntity {

	public BombArrowEntity(EntityType<?> type, World worldIn) {
		super(ModEntities.BOMB_ARROW, worldIn);
	}
	
	public BombArrowEntity(World worldIn) {
		super(ModEntities.BOMB_ARROW, worldIn);
	}
	
	public BombArrowEntity(World worldIn, double x, double y, double z) {
		super(ModEntities.BOMB_ARROW, worldIn, x, y, z);
	}

	public BombArrowEntity(World worldIn, LivingEntity shooter) {
		super(ModEntities.BOMB_ARROW, worldIn, shooter);
	}
	
	@Override
	protected void onHit(RayTraceResult raytraceResultIn) {
		super.onHit(raytraceResultIn);	
		RayTraceResult.Type hitType = raytraceResultIn.getType();
		boolean flag = hitType == RayTraceResult.Type.ENTITY || hitType == RayTraceResult.Type.BLOCK;
		if (flag && !this.world.isRemote) {
			float power = 2.0f;
			Entity shooter = this.getShooter();
			if(shooter instanceof PlayerEntity) {
				EffectInstance effect = ((PlayerEntity) shooter).getActivePotionEffect(ModEffects.BOWMAN_EFFECT.get());
				power += (effect != null ? (1 + effect.getAmplifier()) * 0.5F : 0.0F);
			}
			Vec3d pos = this.getPositionVec();
			Explosion.Mode mode = ForgeEventFactory.getMobGriefingEvent(this.world, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
			this.world.createExplosion(this, pos.x, pos.y, pos.z, power, mode);
			this.remove();
		}
	}
}
