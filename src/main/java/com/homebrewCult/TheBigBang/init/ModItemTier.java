package com.homebrewCult.TheBigBang.init;

import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.LazyLoadBase;

import java.util.function.Supplier;

public enum ModItemTier implements IItemTier {
    BLESSED_ADAMANTIUM(3, 1561, 12.0F, 3.0F, 10, () -> { //Warrior Items
        return Ingredient.fromItems(new IItemProvider[]{ModItems.BLESSED_ADAMANTIUM_INGOT});
    }),
    BLESSED_ORIHALCON(3, 1561, 8.0F, 3.0F, 10, () -> { //Thief Items
        return Ingredient.fromItems(new IItemProvider[]{ModItems.BLESSED_ORIHALCON_INGOT});
    }),
    BLESSED_MITHRIL(3, 1561, 8.0F, 3.0F, 10, () -> { //Mage Items
        return Ingredient.fromItems(new IItemProvider[]{ModItems.BLESSED_MITHRIL_INGOT});
    }),
    BLESSED_GOLD(3, 1561, 8.0F, 3.0F, 22, () -> { //Bowman Items
        return Ingredient.fromItems(new IItemProvider[]{ModItems.BLESSED_GOLD_INGOT});
    });

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyLoadBase<Ingredient> repairMaterial;

    private ModItemTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {
        this.harvestLevel = harvestLevelIn;
        this.maxUses = maxUsesIn;
        this.efficiency = efficiencyIn;
        this.attackDamage = attackDamageIn;
        this.enchantability = enchantabilityIn;
        this.repairMaterial = new LazyLoadBase(repairMaterialIn);
    }

    public int getMaxUses() {
        return this.maxUses;
    }

    public float getEfficiency() {
        return this.efficiency;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairMaterial() {
        return (Ingredient)this.repairMaterial.getValue();
    }
}
