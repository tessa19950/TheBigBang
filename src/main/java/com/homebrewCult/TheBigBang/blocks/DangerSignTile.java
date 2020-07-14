package com.homebrewCult.TheBigBang.blocks;
import net.minecraft.entity.Entity;
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
import net.minecraftforge.fml.network.PacketDistributor;
import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.IQuestEntity;
import com.homebrewCult.TheBigBang.gui.quests.Quest;
import com.homebrewCult.TheBigBang.gui.quests.Questline;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import com.homebrewCult.TheBigBang.inventory.DangerSignContainer;
import com.homebrewCult.TheBigBang.network.BigBangPacketHandler;
import com.homebrewCult.TheBigBang.network.Packet_AddSpawnParticle;
import com.homebrewCult.TheBigBang.util.DangerSignPart;
import com.homebrewCult.TheBigBang.util.MathUtility;
import java.util.ArrayList;
import java.util.List;

public class DangerSignTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
	public DangerSignContainer container;
	private boolean isMaster = false;
	private boolean statusConfirmed = false;
	
	private int spawnDelayTimer = 0;
	private final int SPAWN_DELAY = 60;
	private final int MAX_SPAWN_COUNT = 3;
	private final int MAX_SPAWN_RANGE = 10;
	
	private int questlineIndex;
	private int entitiesKilled;
	private int[] completedQuests;
	public Questline questline;
	private ArrayList<Entity> entityList;
	
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
		if(statusConfirmed && isMaster) {
			masterTick();
		} else if (!statusConfirmed) {
			if(world.getBlockState(pos).get(DangerSignBlock.PART) == DangerSignPart.BASE) {
				initializeMaster();
			}
			statusConfirmed = true;
			markDirty();
		}
	}
	
	public void initializeMaster() {
		isMaster = true;
		if(!world.isRemote) {
			questline = this.getBlockState().get(DangerSignBlock.QUESTLINE);
			questlineIndex = questline.ordinal();
			completedQuests = new int[0];
			TheBigBang.print("Initialized Server Master DangerSignTe");
			world.notifyBlockUpdate(pos, this.getBlockState(), this.getBlockState(), 2);
		} else {
			TheBigBang.print("Initialized Client Master DangerSignTe");
		}
		markDirty();

	}
	
	public void masterTick() {
		if(!world.isRemote) {
			if(entityList == null) {
				entityList = new ArrayList<Entity>();
				for (Entity e : getQuestEntitiesInRange()) {
					entityList.add(e);
				}
				TheBigBang.print("Found " + entityList.size() + " existing entities.");
				return;
			}
			
			if(entityList.size() < MAX_SPAWN_COUNT) {
				this.spawnDelayTimer--;
				if(spawnDelayTimer <= 0) {
					spawnQuestEntity();
					this.spawnDelayTimer = SPAWN_DELAY;
				}
			}
			this.removeKilledEntities();
		}
	}

	public void spawnQuestEntity() {
		int spawnX = pos.getX() + MathUtility.randomIntegerInRange(-MAX_SPAWN_RANGE, MAX_SPAWN_RANGE);
		int spawnZ = pos.getZ() + MathUtility.randomIntegerInRange(-MAX_SPAWN_RANGE, MAX_SPAWN_RANGE);
		BigBangPacketHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new Packet_AddSpawnParticle(new BlockPos(spawnX, pos.getY() + 1, spawnZ)));
		
		IQuestEntity questEntity = (IQuestEntity) questline.getEntityType().spawn(world, null, null, new BlockPos(spawnX, pos.getY(), spawnZ), SpawnReason.SPAWNER, true, true);
		Quest[] availableQuests = this.getAvailableQuests(); 
		for(int i = 0; i < 3 && i < this.getAvailableQuestCount(); i++) {
			if(availableQuests[i].hasQuestItem()) {
				questEntity.addQuestItem(availableQuests[i].getQuestItem());
			}
		}
		Entity newEntity = (Entity) questEntity;
		entityList.add(newEntity);
	}
	
	private List<Entity> getQuestEntitiesInRange() {
		BlockPos AABBStart = this.pos.up(MAX_SPAWN_RANGE*2).north(MAX_SPAWN_RANGE*2).east(MAX_SPAWN_RANGE*2);
		BlockPos AABBEnd = this.pos.down(MAX_SPAWN_RANGE*2).south(MAX_SPAWN_RANGE*2).west(MAX_SPAWN_RANGE*2);
		return this.world.getEntitiesWithinAABB(MobEntity.class, new AxisAlignedBB(AABBStart, AABBEnd), (entity) -> {
			return entity.getType() == questline.getEntityType(); 
		});
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
	
	@Override
	public void remove() {
		clearSavedData();
		super.remove();
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
		tag.putInt(getID() + "questline_index", questlineIndex);
		tag.putInt(getID() + "entities_killed", entitiesKilled);
		tag.putIntArray(getID() + "completed_quests", completedQuests);
		return tag;
	}
	
	@Override
	public void handleUpdateTag(CompoundNBT tag) {
		super.handleUpdateTag(tag);
		questlineIndex = tag.getInt(getID() + "questline_index");
		questline = Questline.getQuestlineByIndex(questlineIndex);
		entitiesKilled = tag.getInt(getID() + "entities_killed");
		completedQuests = tag.getIntArray(getID() + "completed_quests");
	}
	
	public String getID() {
		return (TheBigBang.MODID + this.pos.toString());
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
	public void read(CompoundNBT compound) {
		super.read(compound);		
		if(compound.contains(getID() + "is_master")) {
			isMaster = compound.getBoolean(getID() + "is_master");
			statusConfirmed = true;
		} 
		if(this.isMaster) {
			if(compound.contains(getID() + "questline_index")) {
				questlineIndex = compound.getInt(getID() + "questline_index");
				questline = Questline.getQuestlineByIndex(questlineIndex);
			} else {
				questlineIndex = 0;
				questline = Questline.getQuestlineByIndex(questlineIndex);
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
		compound.putBoolean(getID() + "is_master", isMaster);
		if(this.isMaster) {
			compound.putInt(getID() + "questline_index", questlineIndex);
			compound.putInt(getID() + "entities_killed", entitiesKilled);	
			compound.putIntArray(getID() + "completed_quests", completedQuests);
		}	
		return super.write(compound);
	}
}
