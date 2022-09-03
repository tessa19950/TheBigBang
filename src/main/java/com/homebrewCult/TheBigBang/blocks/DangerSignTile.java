package com.homebrewCult.TheBigBang.blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
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
import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.gui.quests.Quest;
import com.homebrewCult.TheBigBang.gui.quests.Questline;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import com.homebrewCult.TheBigBang.inventory.DangerSignContainer;
import com.homebrewCult.TheBigBang.util.DangerSignPart;
import com.homebrewCult.TheBigBang.util.IQuestEntity;
import com.homebrewCult.TheBigBang.util.MathUtility;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DangerSignTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
	private static final Double ACTIVATION_RADIUS = 24.0D;
	private static final int MAX_SPAWN_COUNT = 8;
	private static final int MAX_SPAWN_RANGE = 8;
	private static final int SPAWN_DELAY = 40;
	
	public DangerSignContainer container;
	private boolean isBasePart = false;
	private boolean blockStateChecked = false;
	
	private int spawnDelayTimer = 0;
	private int entitiesKilled = 0;
	private int[] completedQuests = new int[]{};
	public Questline questline = Questline.None;
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
			if(state.get(DangerSignBlock.PART) == DangerSignPart.BASE && state.get(DangerSignBlock.QUESTLINE) != Questline.None) {
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
		PlayerEntity p = world.getClosestPlayer(this.pos.getX(), this.pos.getY(), this.pos.getZ(), ACTIVATION_RADIUS, false);
		if(world.isRemote || p == null)
			return;

		//Check if we have a list of enemies, if not, search the area for entities matching my questline.
		if(entityList == null) {
			entityList = new ArrayList<>();
			entityList.addAll(getQuestEntitiesInRange());
			return;
		}
		
		//If I haven't spawned the max amount of entities yet, add new ones.
		if(entityList.size() < MAX_SPAWN_COUNT) {
			this.spawnDelayTimer--;
			if(spawnDelayTimer <= 0) {
				spawnQuestEntity();
				this.spawnDelayTimer = SPAWN_DELAY;
			}
		}
		this.removeKilledEntities();
	}
	
	public void spawnQuestEntity() {
		boolean validSpawn = false;
		BlockPos spawnPos = new BlockPos(0,0,0);
		
		//Try 8 times to find a valid spawn position.
		for(int i = 0; i < 8; i++) {
			int spawnX = pos.getX() + MathUtility.intInRange(rand, -MAX_SPAWN_RANGE, MAX_SPAWN_RANGE);
			int spawnY = pos.getY() + MathUtility.intInRange(rand, 0, 4);
			int spawnZ = pos.getZ() + MathUtility.intInRange(rand, -MAX_SPAWN_RANGE, MAX_SPAWN_RANGE);
			spawnPos = new BlockPos(spawnX, spawnY, spawnZ);
			
			//Try 8 times to find an air block, followed by a non air block.
			boolean foundAir = false;
			for(int j = 0; j < 8; j++) {
				if(world.getBlockState(spawnPos.down(j)).getBlock() == Blocks.AIR) {
					foundAir = true;
				} else if(foundAir) {
					validSpawn = true;
					spawnPos = spawnPos.down(j - 1);
					break;
				} else {
					break;
				}
			}
			
			//Break if we've found a valid location
			if(validSpawn) {
				break;
			}
		}
		
		if(validSpawn) {
			IQuestEntity questEntity = (IQuestEntity) questline.getRandomEntityType().spawn(world, null, null, spawnPos, SpawnReason.SPAWNER, true, true);
			Quest[] availableQuests = this.getAvailableQuests(); 
			for(int i = 0; i < 3 && i < this.getAvailableQuestCount(); i++) {
				if(availableQuests[i].hasQuestItem())
					questEntity.getQuestEntityHandler().addQuestItem(availableQuests[i].getRequiredQuestItem());
			}
			Entity newEntity = (Entity) questEntity;
			entityList.add(newEntity);
		}
	}
	
	private List<Entity> getQuestEntitiesInRange() {
		List<Entity> entities = new ArrayList<Entity>();
		for(EntityType<?> e : questline.getEntityTypes()) {
			BlockPos AABBStart = this.pos.up(MAX_SPAWN_RANGE*2).north(MAX_SPAWN_RANGE*2).east(MAX_SPAWN_RANGE*2);
			BlockPos AABBEnd = this.pos.down(MAX_SPAWN_RANGE*2).south(MAX_SPAWN_RANGE*2).west(MAX_SPAWN_RANGE*2);
			entities.addAll(
			this.world.getEntitiesWithinAABB(MobEntity.class, new AxisAlignedBB(AABBStart, AABBEnd), (entity) -> {return entity.getType() == e;})
			);
		}
		return entities;
	}
	
	public void removeKilledEntities() {
		for(int i = 0; i < entityList.size(); i++) {
			if(!entityList.get(i).isAlive()) {
				onEntityKilled(i);
			}
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
	
	public Quest[] getAvailableQuests() {
		Quest[] available = new Quest[getAvailableQuestCount()];
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
		for(int i = 0; i < completedQuests.length; i++) {
			newCompletedQuests[i] = completedQuests[i];
		}
		newCompletedQuests[completedQuests.length] = questIndex;
		completedQuests = newCompletedQuests;
		
	}
	
	private boolean isQuestCompleted(int questIndex) {
		for (int q : completedQuests) {
			if(q == questIndex) {
				return true;
			}
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
		questline = Questline.getQuestlineByIndex(tag.getInt(getID() + "questline_index"));
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
				questline = Questline.getQuestlineByIndex(compound.getInt(getID() + "questline_index"));
			} else {
				questline = Questline.getQuestlineByIndex(0);
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
