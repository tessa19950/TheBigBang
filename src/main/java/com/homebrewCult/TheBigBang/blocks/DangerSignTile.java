package com.homebrewCult.TheBigBang.blocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import com.homebrewCult.TheBigBang.gui.quests.Quests;
import com.homebrewCult.TheBigBang.gui.quests.Questlines;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import com.homebrewCult.TheBigBang.inventory.DangerSignContainer;
import com.homebrewCult.TheBigBang.util.DangerSignPart;
import com.homebrewCult.TheBigBang.util.IQuestEntity;
import com.homebrewCult.TheBigBang.util.MathUtility;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DangerSignTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

	// AS YOU COMPLETE QUESTS, THE "SPAWN DIFFICULTY" GOES FROM 0 TO 1 TO 2.
	// THIS IS WHY ALL THESE VALUES HAVE 3 "STAGES"
	private static final double[] ACTIVATION_RADIUS = { 16.0D, 20.0D, 24.0D };
	private static final int[] SPAWN_CAP = { 3, 6, 12 };
	private static final int[] MAX_SPAWN_RANGE = { 6, 8, 12 };
	private static final int[] SPAWN_DELAY = { 160, 80, 40 };
	
	public DangerSignContainer container;
	private boolean isBasePart = false;
	private boolean blockStateChecked = false;
	
	private int spawnDelayTimer = 0;
	private int entitiesKilled = 0;
	private int[] completedQuests = new int[]{};
	public Questlines questline = Questlines.None;
	private ArrayList<Entity> entityList;
	private Random rand = new Random();
	
	public DangerSignTile() {
		super(ModBlocks.DANGER_SIGN_TILE);
	}

	public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {
		if(!world.isRemote) {
			container = new DangerSignContainer(id, playerInventory, world, pos);
			return this.container;
		}
		return null;
	}

	@Override
	public void tick() {
		if(world == null)
			return;
		if(blockStateChecked && isBasePart) {
			spawnerTick();
		} else if (!blockStateChecked) {
			BlockState state = world.getBlockState(pos);
			if(state.get(DangerSignBlock.PART) == DangerSignPart.BASE && state.get(DangerSignBlock.QUESTLINE) != Questlines.None) {
				isBasePart = true;
				if(!world.isRemote) {
					questline = this.getBlockState().get(DangerSignBlock.QUESTLINE);
					completedQuests = new int[0];
					world.notifyBlockUpdate(pos, this.getBlockState(), this.getBlockState(), 2);
				}
				markDirty();
			}
			blockStateChecked = true;
			markDirty();
		}
	}

	public void spawnerTick() {
		//Check if we're on the server and a player is in range.
		PlayerEntity p = world.getClosestPlayer(this.pos.getX(), this.pos.getY(), this.pos.getZ(),
				ACTIVATION_RADIUS[getSpawnDifficulty()], false);
		if(world.isRemote || p == null)
			return;

		//Check if we have a list of enemies, if not, search the area for entities matching my questline.
		if(entityList == null) {
			entityList = new ArrayList<>();
			entityList.addAll(getQuestEntitiesInRange());
			return;
		}
		
		//If I haven't spawned the max amount of entities yet, add new ones.
		if(entityList.size() < SPAWN_CAP[getSpawnDifficulty()]) {
			this.spawnDelayTimer--;
			if(spawnDelayTimer <= 0) {
				trySpawnQuestEntity();
				this.spawnDelayTimer = SPAWN_DELAY[getSpawnDifficulty()];
			}
		}
		this.removeKilledEntities();
	}

	public void trySpawnQuestEntity() {
		int range = MAX_SPAWN_RANGE[getSpawnDifficulty()];
		double x = pos.getX() + MathUtility.intInRange(rand, -range, range) + 0.5D;
		double y = pos.getY() + MathUtility.intInRange(rand, 0, 4);
		double z = pos.getZ() + MathUtility.intInRange(rand, -range, range) + 0.5D;

		EntityType<?> type = questline.getRandomEntityType();
		boolean flag = EntitySpawnPlacementRegistry.func_223515_a(type,
				world.getWorld(), SpawnReason.SPAWNER, new BlockPos(x, y, z), world.getRandom());
		if (world.areCollisionShapesEmpty(type.func_220328_a(x, y, z)) && flag) {
			Entity entity = type.spawn(world, null, null,
					new BlockPos(x, y, z), SpawnReason.SPAWNER, true, true);
			if(!(entity instanceof IQuestEntity))
				return;
			entity.setLocationAndAngles(x, y, z, rand.nextFloat() * 360, 0);
			if (entity instanceof MobEntity)
				((MobEntity)entity).spawnExplosionParticle();
			Quests[] availableQuests = this.getAvailableQuests();
			for(int i = 0; i < 3 && i < this.getAvailableQuestCount(); i++) {
				if(availableQuests[i].hasQuestItem())
					((IQuestEntity)entity).getQuestEntityHandler().addQuestItem(availableQuests[i].getRequiredQuestItem());
			}
			entityList.add(entity);
		}
	}
	
	private List<Entity> getQuestEntitiesInRange() {
		List<Entity> entities = new ArrayList<Entity>();
		for(EntityType<?> type : questline.getEntityTypes()) {
			int range = MAX_SPAWN_RANGE[getSpawnDifficulty()] * 2;
			BlockPos AABBStart = this.pos.up(range).north(range).east(range);
			BlockPos AABBEnd = this.pos.down(range).south(range).west(range);
			entities.addAll(this.world.getEntitiesWithinAABB(MobEntity.class,
					new AxisAlignedBB(AABBStart, AABBEnd), e -> e.getType() == type));
		}
		return entities;
	}

	private int getSpawnDifficulty() {
		if(completedQuests.length > 2) return 2;
		if(completedQuests.length > 0) return 1;
		return 0;
	}
	
	public void removeKilledEntities() {
		for(int i = 0; i < entityList.size(); i++) {
			if(!entityList.get(i).isAlive())
				onEntityKilled(i);
		}
	}
	
	public void onEntityKilled(int index) {
		entityList.remove(index);
		entitiesKilled++;
		world.notifyBlockUpdate(pos, this.getBlockState(), this.getBlockState(), 2);
		markDirty();
	}
	
	public int getKillCount() {
		return entitiesKilled;
	}
	
	public void setKillCount(int count) {
		entitiesKilled = count;
	}
	
	public Quests[] getAvailableQuests() {
		Quests[] available = new Quests[getAvailableQuestCount()];
		int checking = 0;
		for(int slot = 0; slot < available.length; slot++) {
			for(int i = checking; i < questline.getQuests().length; i++) {
				if(!isQuestCompleted(i)) {
					available[slot] = questline.getQuestByIndex(i);
					checking = i + 1;
					break;
				}
			}
		}
		return available;
	}
	
	private int getAvailableQuestCount() {
		int i = 0;
		for (int q = 0; q < questline.getQuests().length; q++) {
			if(!isQuestCompleted(q)) {
				i++;
			}
		}
		return i;
	}
	
	public void setQuestCompleted(int questIndex) {
		int[] newCompletedQuests = new int[completedQuests.length + 1];
		for(int i = 0; i < completedQuests.length; i++)
			newCompletedQuests[i] = completedQuests[i];
		newCompletedQuests[completedQuests.length] = questIndex;
		completedQuests = newCompletedQuests;
	}
	
	private boolean isQuestCompleted(int questIndex) {
		for (int q : completedQuests) {
			if(q == questIndex)
				return true;
		}
		return false;
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent(getType().getRegistryName().getPath());
	}
	
	public String getID() {
		return "";
	}
	
	@Override
	public void remove() {
		clearSavedData();
		super.remove();
	}
	
	public void clearSavedData( ) {
		CompoundNBT nbt = new CompoundNBT();
		nbt.remove(getID() + "is_master");
		nbt.remove(getID() + "questline_index");
		nbt.remove(getID() + "entities_killed");
		nbt.remove(getID() + "completed_quests");
		super.write(nbt);
	}
	
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(this.pos, 3, this.getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		super.onDataPacket(net, pkt);
		handleUpdateTag(pkt.getNbtCompound());
	}
	
	@Override
	public CompoundNBT getUpdateTag() {
		CompoundNBT tag = super.getUpdateTag();
		tag.putInt(getID() + "questline_index", questline.ordinal());
		tag.putInt(getID() + "entities_killed", entitiesKilled);
		tag.putIntArray(getID() + "completed_quests", completedQuests);
		return tag;
	}
	
	@Override
	public void handleUpdateTag(CompoundNBT tag) {
		super.handleUpdateTag(tag);
		questline = Questlines.getQuestlineByIndex(tag.getInt(getID() + "questline_index"));
		entitiesKilled = tag.getInt(getID() + "entities_killed");
		completedQuests = tag.getIntArray(getID() + "completed_quests");
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);		
		if(compound.contains(getID() + "is_master")) {
			isBasePart = compound.getBoolean(getID() + "is_master");
			blockStateChecked = true;
		} 
		if(this.isBasePart) {
			if(compound.contains(getID() + "questline_index")) {
				questline = Questlines.getQuestlineByIndex(compound.getInt(getID() + "questline_index"));
			} else {
				questline = Questlines.getQuestlineByIndex(0);
			}
			if(compound.contains(getID() + "entities_killed")) {
				entitiesKilled = compound.getInt(getID() + "entities_killed");
			}
			if(compound.contains(getID() + "completed_quests")) {
				completedQuests = compound.getIntArray(getID() + "completed_quests");
			} else {
				completedQuests = new int[0];
			}
		}	
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putBoolean(getID() + "is_master", isBasePart);
		if(this.isBasePart) {
			compound.putInt(getID() + "questline_index", questline.ordinal());
			compound.putInt(getID() + "entities_killed", entitiesKilled);	
			compound.putIntArray(getID() + "completed_quests", completedQuests);
		}	
		return super.write(compound);
	}
}
