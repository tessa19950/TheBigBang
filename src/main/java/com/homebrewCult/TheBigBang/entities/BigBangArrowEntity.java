package com.homebrewCult.TheBigBang.entities;

import com.homebrewCult.TheBigBang.TheBigBang;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.*;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class BigBangArrowEntity extends AbstractArrowEntity {

    protected BlockState inBlockState;
    protected int ticksInGround;
    protected int ticksInAir;

    public BigBangArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public BigBangArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn, double x, double y, double z) {
        super(type, x, y, z, worldIn);
    }

    public BigBangArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn, LivingEntity shooter) {
        super(type, shooter, worldIn);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
    }

    @Override
    public void tick() {
        boolean isNoClip = this.getNoClip();
        Vec3d motion = this.getMotion();
        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = MathHelper.sqrt(horizontalMag(motion));
            this.rotationYaw = (float)(MathHelper.atan2(motion.x, motion.z) * (double)(180F / (float)Math.PI));
            this.rotationPitch = (float)(MathHelper.atan2(motion.y, f) * (double)(180F / (float)Math.PI));
            this.prevRotationYaw = this.rotationYaw;
            this.prevRotationPitch = this.rotationPitch;
        }

        BlockPos blockpos = new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ());
        BlockState blockstate = this.world.getBlockState(blockpos);
        if (!blockstate.isAir(this.world, blockpos) && !isNoClip) {
            VoxelShape voxelshape = blockstate.getCollisionShape(this.world, blockpos);
            if (!voxelshape.isEmpty()) {
                for(AxisAlignedBB axisalignedbb : voxelshape.toBoundingBoxList()) {
                    if (axisalignedbb.offset(blockpos).contains(new Vec3d(this.getPosX(), this.getPosY(), this.getPosZ()))) {
                        this.inGround = true;
                        break;
                    }
                }
            }
        }

        if (this.arrowShake > 0)
            --this.arrowShake;
        if (this.isWet())
            this.extinguish();

        if (this.inGround && !isNoClip) {
            ++this.timeInGround;
            if (this.inBlockState != blockstate && this.world.hasNoCollisions(this.getBoundingBox().grow(0.06D))) {
                this.inGround = false;
                setMotion(motion.mul(rand.nextFloat() * 0.2F, rand.nextFloat() * 0.2F, rand.nextFloat() * 0.2F));
            } else if (!world.isRemote)
                tryDespawn();
        } else {
            this.timeInGround = 0;
            ++this.ticksInAir;
            Vec3d vec3d2 = this.getPositionVec();
            Vec3d vec3d3 = vec3d2.add(motion);
            RayTraceResult trace = this.world.rayTraceBlocks(new RayTraceContext(vec3d2, vec3d3, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this));
            if (trace.getType() != RayTraceResult.Type.MISS) {
                vec3d3 = trace.getHitVec();
            }

            Vec3d mod = getFlightModifications(motion);
            this.setMotion(mod.x, mod.y, mod.z);

            while(!this.removed) {
                EntityRayTraceResult entityTrace = this.rayTraceEntities(vec3d2, vec3d3);
                if (entityTrace != null)
                    trace = entityTrace;
                if (trace != null && trace.getType() == RayTraceResult.Type.ENTITY) {
                    Entity entity = ((EntityRayTraceResult)trace).getEntity();
                    Entity shooter = this.getShooter();
                    if (entity instanceof PlayerEntity && shooter instanceof PlayerEntity && !((PlayerEntity)shooter).canAttackPlayer((PlayerEntity)entity)) {
                        trace = null;
                        entityTrace = null;
                    }
                }
                if (trace != null && trace.getType() != RayTraceResult.Type.MISS && !isNoClip && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, trace)) {
                    this.onHit(trace);
                    this.isAirBorne = true;
                }
                if (entityTrace == null || this.getPierceLevel() <= 0)
                    break;
                trace = null;
            }

            motion = this.getMotion();
            double mx = motion.x;
            double my = motion.y;
            double mz = motion.z;
            if (isNoClip) this.rotationYaw = (float)(MathHelper.atan2(-mx, -mz) * (double)(180F / (float)Math.PI));
            else this.rotationYaw = (float)(MathHelper.atan2(mx, mz) * (double)(180F / (float)Math.PI));

            while(this.rotationPitch - this.prevRotationPitch >= 180.0F)
                this.prevRotationPitch += 360.0F;
            while(this.rotationYaw - this.prevRotationYaw < -180.0F)
                this.prevRotationYaw -= 360.0F;
            while(this.rotationYaw - this.prevRotationYaw >= 180.0F)
                this.prevRotationYaw += 360.0F;

            double px = this.getPosX() + mx;
            double py = this.getPosY() + my;
            double pz = this.getPosZ() + mz;
            this.rotationPitch = MathHelper.lerp(0.2F, this.prevRotationPitch, this.rotationPitch);
            this.rotationYaw = MathHelper.lerp(0.2F, this.prevRotationYaw, this.rotationYaw);
            if (this.isInWater()) {
                for(int j = 0; j < 4; ++j)
                    this.world.addParticle(ParticleTypes.BUBBLE, this.getPosX() - py * 0.25D, this.getPosY() - pz * 0.25D, this.getPosZ() - mz * 0.25D, py, pz, mz);
            }
            this.setPosition(px, py, pz);
            this.doBlockCollisions();
        }

        if (!this.world.isRemote)
            this.setFlag(6, this.isGlowing());
        this.baseTick();
    }

    private void tryDespawn() {
        func_225516_i_();
    }

    protected Vec3d getFlightModifications(Vec3d motion) { return motion; };

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
