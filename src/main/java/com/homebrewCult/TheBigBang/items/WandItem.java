package com.homebrewCult.TheBigBang.items;

import java.util.ArrayList;
import java.util.List;
import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WandItem extends Item {

	public WandItem(Properties properties) {
		super(properties);
	}

	@Override
	public boolean onEntitySwing(ItemStack stack, LivingEntity user) {
		TheBigBang.print("Used Wand");
		
		//Get the world and set up the "Range" AABB
		World world = user.getEntityWorld();
		Vec3d AABBStart = user.getPositionVec().add(new Vec3d(0,1,0));
		Vec3d AABBEnd = user.getPositionVec().add(user.getLookVec().scale(16));
		
		//Instantiate a List and populate it with Entities in range
		List<Entity> entities = new ArrayList<Entity>();
		entities.addAll(
		world.getEntitiesWithinAABB(MobEntity.class, new AxisAlignedBB(AABBStart, AABBEnd), (entityIn) -> {return true;})
		);
		
		//Find the closest entity from the List
		Entity best = null;
		int size = entities.size();
		if(size > 1) size = 2;
		switch(size) {
			case 2: {
				best = entities.get(0);
				double distance;
				for(Entity e : entities) {
					distance = user.getDistanceSq(e);
					if(distance < user.getDistanceSq(best)) {
						best = e;
					}
				}
			}
			case 1: {
				best = entities.get(0);
			}
			case 0: default: {
				break;
			}
		}
		
		//If an entity is selected apply damage to it
		if(best != null) {
			best.attackEntityFrom(DamageSource.MAGIC, 6);
			world.addParticle(ModParticleTypes.MAGIC_CLAW.get(), best.posX, best.posY, best.posZ, 0, 0, 0);
		}
		
		return super.onEntitySwing(stack, user);
	}
}
