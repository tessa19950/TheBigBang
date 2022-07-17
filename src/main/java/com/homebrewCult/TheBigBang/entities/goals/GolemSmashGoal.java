package com.homebrewCult.TheBigBang.entities.goals;

import com.homebrewCult.TheBigBang.entities.mob.AbstractGolemEntity;
import net.minecraft.entity.ai.goal.Goal;

import java.util.*;

public class GolemSmashGoal extends Goal {

    public static final int SHOCKWAVE_DURATION = 3 * 20;
    public static final int SHOCKWAVE_COOLDOWN = 13 * 20;
    private final AbstractGolemEntity golem;

    public GolemSmashGoal(AbstractGolemEntity golem) {
        super();
        this.golem = golem;
        this.setMutexFlags(EnumSet.of(Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean shouldExecute() {
        if(!golem.isAngry())
            return false;
        if(golem.ticksExisted - golem.getShockwaveTick() > SHOCKWAVE_COOLDOWN && golem.world.rand.nextDouble() < 0.016)
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

    }

    @Override
    public boolean shouldContinueExecuting() {
        return golem.ticksExisted - golem.getShockwaveTick() <= SHOCKWAVE_DURATION;
    }
}
