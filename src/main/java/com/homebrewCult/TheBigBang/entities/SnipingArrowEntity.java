package com.homebrewCult.TheBigBang.entities;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

import javax.vecmath.Vector3d;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.init.ModEntities;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;

import com.homebrewCult.TheBigBang.init.ModSounds;
import com.sun.javafx.geom.Vec3f;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class SnipingArrowEntity extends AbstractArrowEntity {

	private BlockState inBlockState;
	private int ticksInGround;
	private int ticksInAir;
	private static final DataParameter<Integer> CHASE_TARGET = EntityDataManager.createKey(SnipingArrowEntity.class, DataSerializers.VARINT);
	
	public SnipingArrowEntity(EntityType<?> type, World worldIn) {
		super(ModEntities.SNIPING_ARROW, worldIn);
	}
	
	public SnipingArrowEntity(World worldIn) {
		super(ModEntities.SNIPING_ARROW, worldIn);
	}
	
	public SnipingArrowEntity(World worldIn, double x, double y, double z) {
		super(ModEntities.SNIPING_ARROW, x, y, z, worldIn);
	}

	public SnipingArrowEntity(World worldIn, LivingEntity shooter) {
		super(ModEntities.SNIPING_ARROW, shooter, worldIn);
	}
	
	protected void registerData() {
		super.registerData();
		this.dataManager.register(CHASE_TARGET, -1);
	}
	
	public int getChaseTarget() {
		return this.dataManager.get(CHASE_TARGET);
	}
	
	public void setChaseTarget(int targetId) {
		this.dataManager.set(CHASE_TARGET, targetId);
	}
	
	@Override
	public void onAddedToWorld() {
		super.onAddedToWorld();
	}
	
	@Override
	public void tick() {
		if(world.isRemote && this.ticksExisted == 1) {
			Entity shooter = this.world.getClosestPlayer(posX, posY, posZ);
			if(shooter != null) {
				for (int i = 0; i < 32; ++i) {
					double x = Math.cos((double) i / 32D * Math.PI * 2D);
					double y = Math.sin((double) i / 32D * Math.PI * 2D);
					double radius = 0.2D;
					Vec3d vec = new Vec3d(x, y, 0).mul(radius, radius, radius);
					vec = vec.rotatePitch(-shooter.getPitch(1) * (float) Math.PI / 180).rotateYaw(-shooter.getYaw(1) * (float) Math.PI / 180);



					double forwardSpeed = 0.2D;
					double radialSpeed = 0.5D;
					Vec3d motion = this.getMotion();
					motion = motion.mul(forwardSpeed, forwardSpeed, forwardSpeed).add(vec.mul(radialSpeed, radialSpeed, radialSpeed));
					this.world.addParticle(ModParticleTypes.SYMBOL_GOLD.get(), this.posX + vec.x, this.posY + vec.y, this.posZ + vec.z, motion.x, motion.y, motion.z);
				}
			}
		}

		boolean flag = this.func_203047_q();
		Vec3d motion = this.getMotion();
		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt(func_213296_b(motion));
			this.rotationYaw = (float)(MathHelper.atan2(motion.x, motion.z) * (double)(180F / (float)Math.PI));
			this.rotationPitch = (float)(MathHelper.atan2(motion.y, (double)f) * (double)(180F / (float)Math.PI));
			this.prevRotationYaw = this.rotationYaw;
			this.prevRotationPitch = this.rotationPitch;
		}

		BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);
		BlockState blockstate = this.world.getBlockState(blockpos);
		if (!blockstate.isAir(this.world, blockpos) && !flag) {
			VoxelShape voxelshape = blockstate.getCollisionShape(this.world, blockpos);
			if (!voxelshape.isEmpty()) {
				for(AxisAlignedBB axisalignedbb : voxelshape.toBoundingBoxList()) {
					if (axisalignedbb.offset(blockpos).contains(new Vec3d(this.posX, this.posY, this.posZ))) {
						this.inGround = true;
						break;
					}
				}
			}
		}

		if (this.arrowShake > 0) {
			--this.arrowShake;
		}

		if (this.isWet()) {
			this.extinguish();
		}

		if (this.inGround && !flag) {
			if (this.inBlockState != blockstate && this.world.areCollisionShapesEmpty(this.getBoundingBox().grow(0.06D))) {
				this.inGround = false;
				this.setMotion(motion.mul((double)(this.rand.nextFloat() * 0.2F), (double)(this.rand.nextFloat() * 0.2F), (double)(this.rand.nextFloat() * 0.2F)));
			} else if (!this.world.isRemote) {
				this.tryDespawn();
			}

			++this.timeInGround;
		} else {
			this.timeInGround = 0;
			++this.ticksInAir;
			Vec3d vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
			Vec3d vec3d2 = vec3d1.add(motion);
			RayTraceResult raytraceresult = this.world.rayTraceBlocks(new RayTraceContext(vec3d1, vec3d2, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this));
			if (raytraceresult.getType() != RayTraceResult.Type.MISS) {
				vec3d2 = raytraceresult.getHitVec();
			}
			
			if(this.getChaseTarget() == -1) {	
				AxisAlignedBB bb = new AxisAlignedBB(this.getPositionVector(), this.getPositionVec().add(this.getMotion().mul(30D, 30D, 30D)));
				List<Entity> entities = this.world.getEntitiesInAABBexcluding(this.getShooter(), bb, EntityPredicates.IS_ALIVE);			
				double lowestAngle = 30D;
				Entity target = null;
				for(Entity entity : entities) {
					if(entity instanceof MobEntity) {
						if(((MobEntity)entity).getEntitySenses().canSee(this)) {				
							Vec3d entityDir = entity.getPositionVec().subtract(this.getPositionVec()).normalize();
							double angle = Math.acos(this.getMotion().normalize().dotProduct(entityDir)) / Math.PI * 180;
							if(angle < lowestAngle) {
								lowestAngle = angle;
								target = entity;
							}
						}
					}
				}
				if(target != null) {
					this.setChaseTarget(target.getEntityId());
				}
			} else {
				Entity entity = this.world.getEntityByID(this.getChaseTarget());
				if(entity != null && this.getShooter() != null) {
					double turnPct = MathHelper.clamp((this.getDistanceSq(entity) / this.getShooter().getDistanceSq(entity)), 0D, 1D);
					Vec3d targetPos = entity.getPositionVec().add(0, entity.getBoundingBox().getYSize() * 0.5D, 0);
					Vec3d targetDir = targetPos.subtract(this.getPositionVec()).normalize();
					double x = MathHelper.lerp(turnPct, targetDir.x * motion.length(), motion.x);
					double y = MathHelper.lerp(turnPct, targetDir.y * motion.length(), motion.y);
					double z = MathHelper.lerp(turnPct, targetDir.z * motion.length(), motion.z);
					this.setMotion(x, y, z);
				}
			}
			
			while(!this.removed) {
				EntityRayTraceResult entityraytraceresult = this.func_213866_a(vec3d1, vec3d2);
				if (entityraytraceresult != null) {
					raytraceresult = entityraytraceresult;
				}
				if (raytraceresult != null && raytraceresult.getType() == RayTraceResult.Type.ENTITY) {
					Entity entity = ((EntityRayTraceResult)raytraceresult).getEntity();
					Entity entity1 = this.getShooter();
					if (entity instanceof PlayerEntity && entity1 instanceof PlayerEntity && !((PlayerEntity)entity1).canAttackPlayer((PlayerEntity)entity)) {
						raytraceresult = null;
						entityraytraceresult = null;
					}
				}
				if (raytraceresult != null && !flag && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
					this.onHit(raytraceresult);
					this.isAirBorne = true;
				}
				if (entityraytraceresult == null || this.func_213874_s() <= 0) {
					break;
				}
				raytraceresult = null;
			}
			
			motion = this.getMotion();
			double d1 = motion.x;
			double d2 = motion.y;
			double d0 = motion.z;
			if (this.getIsCritical()) {
				for(int i = 0; i < 4; ++i) {
					//this.world.addParticle(ParticleTypes.CRIT, this.posX + d1 * (double)i / 4.0D, this.posY + d2 * (double)i / 4.0D, this.posZ + d0 * (double)i / 4.0D, -d1, -d2 + 0.2D, -d0);
				}
			}
			
			this.posX += d1;
			this.posY += d2;
			this.posZ += d0;
			float f4 = MathHelper.sqrt(func_213296_b(motion));
			if (flag) {
				this.rotationYaw = (float)(MathHelper.atan2(-d1, -d0) * (double)(180F / (float)Math.PI));
			} else {
				this.rotationYaw = (float)(MathHelper.atan2(d1, d0) * (double)(180F / (float)Math.PI));
			}
			
			for(this.rotationPitch = (float)(MathHelper.atan2(d2, (double)f4) * (double)(180F / (float)Math.PI)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
				;
			}
			
			while(this.rotationPitch - this.prevRotationPitch >= 180.0F) {
				this.prevRotationPitch += 360.0F;
			}
			
			while(this.rotationYaw - this.prevRotationYaw < -180.0F) {
				this.prevRotationYaw -= 360.0F;
			}
			
			while(this.rotationYaw - this.prevRotationYaw >= 180.0F) {
				this.prevRotationYaw += 360.0F;
			}
			
			this.rotationPitch = MathHelper.lerp(0.2F, this.prevRotationPitch, this.rotationPitch);
			this.rotationYaw = MathHelper.lerp(0.2F, this.prevRotationYaw, this.rotationYaw);
			if (this.isInWater()) {
				for(int j = 0; j < 4; ++j) {
					//this.world.addParticle(ParticleTypes.BUBBLE, this.posX - d1 * 0.25D, this.posY - d2 * 0.25D, this.posZ - d0 * 0.25D, d1, d2, d0);
				}
			}
			
			this.setPosition(this.posX, this.posY, this.posZ);
			this.doBlockCollisions();
		}
		      
		if (!this.world.isRemote) {
			this.setFlag(6, this.isGlowing());
		}
		this.baseTick();	
	}
	
	@Override
	protected void onHit(RayTraceResult raytraceResultIn) {
		super.onHit(raytraceResultIn);	
		RayTraceResult.Type raytraceresult$type = raytraceResultIn.getType();
		boolean flag = raytraceresult$type == RayTraceResult.Type.ENTITY || raytraceresult$type == RayTraceResult.Type.BLOCK;
		if (flag && !this.world.isRemote) {	
			this.remove();
		}
		if (raytraceresult$type == RayTraceResult.Type.ENTITY) {
			float pitch = 0.9F + world.rand.nextFloat() * 0.2F;
			world.playSound(null, getPosition(), ModSounds.SNIPING_ARROW_HIT, SoundCategory.PLAYERS, 1, pitch);
		}
		super.onHit(raytraceResultIn);	
	}

	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack(Items.ARROW);
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public float getBrightness() {
		return 1.0F;
	}

	@OnlyIn(Dist.CLIENT)
	public int getBrightnessForRender() {
		return 15728880;
	}

}
