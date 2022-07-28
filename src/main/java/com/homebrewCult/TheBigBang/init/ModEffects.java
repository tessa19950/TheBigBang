package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.effects.BowmanEffect;
import com.homebrewCult.TheBigBang.effects.MagicianEffect;
import com.homebrewCult.TheBigBang.effects.ThiefEffect;
import com.homebrewCult.TheBigBang.effects.WarriorEffect;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModEffects {

    public static final DeferredRegister<Effect> EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS, TheBigBang.MODID);
    public static final RegistryObject<Effect> WARRIOR_EFFECT = EFFECTS.register("warrior_effect", WarriorEffect::new);
    public static final RegistryObject<Effect> MAGICIAN_EFFECT = EFFECTS.register("magician_effect", MagicianEffect::new);
    public static final RegistryObject<Effect> BOWMAN_EFFECT = EFFECTS.register("bowman_effect", BowmanEffect::new);
    public static final RegistryObject<Effect> THIEF_EFFECT = EFFECTS.register("thief_effect", ThiefEffect::new);

}
