package com.homebrewCult.TheBigBang.entities;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.init.ModEntities;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import com.homebrewCult.TheBigBang.init.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.util.List;

public class SnipingArrowEntity extends BigBangArrowEntity {

	private static final DataParameter<Integer> CHASE_TARGET = EntityDataManager.createKey(SnipingArrowEntity.class, DataSerializers.VARINT);
	
	public SnipingArrowEntity(EntityType<?> type, World worldIn) {
		super(ModEntities.SNIPING_ARROW, worldIn);
	}
	
	public SnipingArrowEntity(World worldIn) {
		super(ModEntities.SNIPING_ARROW, worldIn);
	}
	
	public SnipingArrowEntity(World worldIn, double x, double y, double z) {
		super(ModEntities.SNIPING_ARROW, worldIn, x, y, z);
	}

	public SnipingArrowEntity(World worldIn, LivingEntity shooter) {
		super(ModEntities.SNIPING_ARROW, worldIn, shooter);
	}
	
	@Override
	public void onAddedToWorld() {
		super.onAddedToWorld();
	}

	protected void registerData() {
		super.registerData();
		this.dataManager.register(CHASE_TARGET, -1);
	}

	@Override
	public void tick() {
		if(world.isRemote && this.ticksExisted == 1)
			spawnRingParticle();
		super.tick();
	}

	private void spawnRingParticle() {

	}

	@Override
	protected Vec3d getFlightModifications(Vec3d motion) {
		if(this.getChaseTarget() == -1) {
			findNewChaseTarget();
			return motion;
		}
		Entity entity = this.world.getEntityByID(this.getChaseTarget());
		if(entity != null && this.getShooter() != null) {
			double turnPct = MathHelper.clamp((this.getDistanceSq(entity) / this.getShooter().getDistanceSq(entity)), 0D, 1D);
			Vec3d targetPos = entity.getPositionVec().add(0, entity.getBoundingBox().getYSize() * 0.5D, 0);
			Vec3d targetDir = targetPos.subtract(this.getPositionVec()).normalize();
			double x = MathHelper.lerp(turnPct, targetDir.x * motion.length(), motion.x);
			double y = MathHelper.lerp(turnPct, targetDir.y * motion.length(), motion.y);
			double z = MathHelper.lerp(turnPct, targetDir.z * motion.length(), motion.z);
			return new Vec3d(x, y, z);
		}
		return motion;
	}

	private void findNewChaseTarget() {
		AxisAlignedBB bb = new AxisAlignedBB(this.getPositionVector(), this.getPositionVec().add(this.getMotion().mul(30D, 30D, 30D)));
		List<Entity> entities = this.world.getEntitiesInAABBexcluding(this.getShooter(), bb, EntityPredicates.IS_ALIVE);
		double lowestAngle = 30D;
		Entity target = null;
		for(Entity entity : entities) {
			if(entity instanceof MobEntity && ((MobEntity)entity).getEntitySenses().canSee(this)) {
				Vec3d entityDir = entity.getPositionVec().subtract(this.getPositionVec()).normalize();
				double angle = Math.acos(this.getMotion().normalize().dotProduct(entityDir)) / Math.PI * 180;
				if (angle < lowestAngle) {
					lowestAngle = angle;
					target = entity;
				}
			}
		}
		if(target != null)
			this.setChaseTarget(target.getEntityId());
	}

	public int getChaseTarget() {
		return this.dataManager.get(CHASE_TARGET);
	}

	public void setChaseTarget(int targetId) {
		this.dataManager.set(CHASE_TARGET, targetId);
	}
	
	@Override
	protected void onHit(RayTraceResult raytraceResultIn) {
		super.onHit(raytraceResultIn);	
		RayTraceResult.Type trace = raytraceResultIn.getType();
		if(raytraceResultIn instanceof EntityRayTraceResult) {
			if(((EntityRayTraceResult)raytraceResultIn).getEntity().getType().equals(EntityType.ENDERMAN))
				return;
		}
		if (trace == RayTraceResult.Type.BLOCK && !this.world.isRemote)
			this.remove();
		if (trace == RayTraceResult.Type.ENTITY) {
			float pitch = 0.9F + world.rand.nextFloat() * 0.2F;
			world.playSound(null, getPosition(), ModSounds.SNIPING_ARROW_HIT, SoundCategory.PLAYERS, 1, pitch);
		}
	}

}
