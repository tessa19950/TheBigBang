package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.TheBigBang;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModParticleTypes {	
	
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, TheBigBang.MODID);
	public static final RegistryObject<BasicParticleType> ITEM_BLUE_SLIME = PARTICLE_TYPES.register("item_blue_slime", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> MAGIC_CLAW_LEFT = PARTICLE_TYPES.register("magic_claw_left", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> MAGIC_CLAW_RIGHT = PARTICLE_TYPES.register("magic_claw_right", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> HOLY_HEXAGRAM = PARTICLE_TYPES.register("holy_hexagram", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> HOLY_CIRCLE = PARTICLE_TYPES.register("holy_circle", () -> new BasicParticleType(true));

}