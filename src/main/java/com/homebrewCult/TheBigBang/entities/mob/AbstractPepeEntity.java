package com.homebrewCult.TheBigBang.entities.mob;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Optional;

public class AbstractPepeEntity extends AnimalEntity {
    public static final DataParameter<Integer> HAT;
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.COD, Items.SALMON, Items.TROPICAL_FISH);

    public AbstractPepeEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Nullable
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.setHat(this.world.rand.nextInt(3));
        return spawnDataIn;
    }

    public boolean isBreedingItem(ItemStack stack) { return TEMPTATION_ITEMS.test(stack); }

    @Nullable @Override public AgeableEntity createChild(AgeableEntity ageable) { return null; }

    public int getHat() {
        return dataManager.get(HAT);
    }

    public void setHat(int i) {
        dataManager.set(HAT, i);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("Hat", this.getHat());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setHat(compound.getInt("Hat"));
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(HAT, 0);
    }

    static {
        HAT = EntityDataManager.createKey(AbstractPepeEntity.class, DataSerializers.VARINT);
    }
}
