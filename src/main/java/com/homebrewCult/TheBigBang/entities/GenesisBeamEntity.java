package com.homebrewCult.TheBigBang.entities;

import com.homebrewCult.TheBigBang.init.ModEntities;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class GenesisBeamEntity extends Entity {

	public static final int MAX_AGE = 10;
	
	public GenesisBeamEntity(EntityType<?> type, World worldIn) {
		super(ModEntities.GENESIS_BEAM, worldIn);
	}
	
	public GenesisBeamEntity(World worldIn) {
		super(ModEntities.GENESIS_BEAM, worldIn);
	}
	
	@Override
	public void onAddedToWorld() {
		this.world.addParticle(ModParticleTypes.HOLY_CIRCLE.get(), this.posX, this.posY + 7.1f, this.posZ, 0, -0.04f, 0);
		super.onAddedToWorld();
	}

	@Override
	public void tick() {
		super.tick();
		if(this.ticksExisted == 2) {
			this.world.addParticle(ModParticleTypes.HOLY_HEXAGRAM.get(), this.posX, this.posY + 6.6f, this.posZ, 0, -0.08f, 0);
		} else if (this.ticksExisted == 4) {
			this.world.addParticle(ModParticleTypes.HOLY_HEXAGRAM.get(), this.posX, this.posY + 6.1f, this.posZ, 0, -0.12f, 0);
			this.world.addParticle(ModParticleTypes.HOLY_CIRCLE.get(), this.posX, this.posY, this.posZ, 0, 0.04f, 0);
			this.world.addParticle(ParticleTypes.EXPLOSION, this.posX, this.posY + 1f, this.posZ, 0, 0, 0);
			for(int i = 0; i < 16; ++i) {
				double s0 = (this.world.rand.nextDouble() - 0.5D) * 0.5D;
				double s1 = (this.world.rand.nextDouble() - 0.5D) * 0.5D;
				double s2 = (this.world.rand.nextDouble() - 0.5D) * 0.5D;
				this.world.addParticle(ParticleTypes.POOF, this.posX, this.posY, this.posZ, s0, s1, s2);
			}
		}
		if(this.ticksExisted > MAX_AGE)
			this.remove();
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
	
	@Override
	protected void registerData() {
	}

	@Override
	protected void readAdditional(CompoundNBT compound) {
	}

	@Override
	protected void writeAdditional(CompoundNBT compound) {
	}
}
