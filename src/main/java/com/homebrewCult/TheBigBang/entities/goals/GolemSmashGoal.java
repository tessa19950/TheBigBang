package com.homebrewCult.TheBigBang.entities.goals;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.AbstractGolemEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.*;

public class GolemSmashGoal extends Goal {

    public static final int SHOCKWAVE_DURATION = 3 * 20;
    public static final int SHOCKWAVE_COOLDOWN = 13 * 20;
    private final AbstractGolemEntity golem;
    private List<LivingEntity> nearbyEntities = new ArrayList<>();

    public GolemSmashGoal(AbstractGolemEntity golem) {
        super();
        this.golem = golem;
        this.setMutexFlags(EnumSet.of(Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean shouldExecute() {
        if(!golem.isAngry())
            return false;
        if(golem.ticksExisted - golem.getShockwaveTick() > SHOCKWAVE_COOLDOWN)
            return golem.world.getClosestPlayer(golem, 10) != null;
        return false;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        golem.setShockwaveTick(golem.ticksExisted);
    }

    @Override
    public void tick() {
        super.tick();
        int attackTimer = golem.ticksExisted - golem.getShockwaveTick();
        if(attackTimer >= SHOCKWAVE_DURATION - 20) {
            int shockwaveTimer = MathHelper.clamp(attackTimer - SHOCKWAVE_DURATION + 20, 0, 20);
            if(shockwaveTimer == 1)
                nearbyEntities = golem.world.getEntitiesWithinAABB(LivingEntity.class, golem.getBoundingBox().grow(10), l -> !(l instanceof AbstractGolemEntity));

            Set<BlockPos> shockwavePoints = new HashSet<>();
            final BlockPos start = golem.getPosition().down();
            int pointCount = 4 + shockwaveTimer;
            double radius = 1 + ((double)shockwaveTimer/2.0D);

            int debugChecks = 0;
            int debugBlocks = 0;

            for(int i = 0; i < pointCount; ++i) {
                debugChecks++;
                double angle = ((double) i / (double) pointCount) * Math.PI * 2.0D;
                int x = (int)Math.round(Math.sin(angle) * radius);
                int z = (int)Math.round(Math.cos(angle) * radius);

                if(shockwavePoints.stream().noneMatch(p -> p.getX() == x && p.getZ() == z))
                    shockwavePoints.add(start.add(x, 0, z));
            }

            TheBigBang.LOGGER.debug("In shockwave tick " + shockwaveTimer + " we're checking " + debugChecks + " points.");

            ListIterator<LivingEntity> iterator = nearbyEntities.listIterator();
            for (BlockPos point : shockwavePoints) {
                BlockPos ground = getNearestGround(point);
                if(ground == null)
                    continue;
                debugBlocks++;
                while(iterator.hasNext()) {
                    LivingEntity entity = iterator.next();
                    Vec3d pos = entity.getPositionVec();
                    if (pos.distanceTo(new Vec3d(ground.getX(), ground.getY() + 1, ground.getZ())) < 2 && entity.onGround) {
                        entity.addVelocity(0, 0.8, 0);
                        entity.attackEntityFrom(DamageSource.MAGIC, 1);
                        iterator.remove();
                    }
                }
                FallingBlockEntity fallingBlock = new FallingBlockEntity(golem.world,
                        ground.getX() + 0.5D, ground.getY(), ground.getZ() + 0.5D, golem.world.getBlockState(ground));
                fallingBlock.setVelocity(0, 0.3D, 0);
                golem.world.addEntity(fallingBlock);
            }

            TheBigBang.LOGGER.debug("In shockwave tick " + shockwaveTimer + " we've found " + shockwavePoints.size() + " points, with " + debugBlocks + " valid blocks.");
        }
    }

    private BlockPos getNearestGround(BlockPos base) {
        BlockState state = golem.world.getBlockState(base);
        BlockState stateAbove = golem.world.getBlockState(base.up());
        if(state.isSolid() && !stateAbove.isSolid())
            return base;
        else if (!state.isSolid() && golem.world.getBlockState(base.down()).isSolid())
            return base.down();
        else if (state.isSolid() & stateAbove.isSolid() && !golem.world.getBlockState(base.up().up()).isSolid())
            return base.up();
        return null;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return golem.ticksExisted - golem.getShockwaveTick() <= SHOCKWAVE_DURATION;
    }
}
