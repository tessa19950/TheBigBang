package com.homebrewCult.TheBigBang.inventory;

import java.util.Map;

import com.homebrewCult.TheBigBang.blocks.DangerSignTile;
import com.homebrewCult.TheBigBang.gui.quests.Quest;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import com.homebrewCult.TheBigBang.network.BigBangPacketHandler;
import com.homebrewCult.TheBigBang.network.Packet_HandInQuest;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DangerSignContainer extends Container {
	
	public final IInventory questInventory = new Inventory(3);
	public DangerSignInputSlot[] inputSlots = new DangerSignInputSlot[3];
	
	public final DangerSignTile tileEntity;
	private final PlayerEntity player;
	private final World world;
	private final BlockPos pos;
	
	public DangerSignContainer(int id, PlayerInventory playerInventory, World world, BlockPos pos) {
	    super(ModBlocks.DANGER_SIGN_CONTAINER, id);
	    this.pos = pos;
		
		this.tileEntity = (DangerSignTile)world.getTileEntity(pos);	
		this.player = playerInventory.player;
		this.world = world;
		
		//Places the quest slots
		for(int i = 0; i < inputSlots.length; i++) {
			this.inputSlots[i] = (DangerSignInputSlot) this.addSlot(new DangerSignInputSlot(questInventory, i, 121 + 22 * i, 123));
		}
		
		//Places the player inventory slots
		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(playerInventory, j + i * 9 + 9, -58 + j * 18, 84 + i * 18));
			}
		}
		//Places the player hot bar slots
		for(int k = 0; k < 9; ++k) {
			this.addSlot(new Slot(playerInventory, k, -58 + k * 18, 142));
		}
	}

	public boolean canHandInQuest(Quest quest)
	{	
		boolean flag = true;
		if(tileEntity.getKillCount() >= quest.getRequiredKills()) {
			int i = 0;
			for(Map.Entry<Item, Integer> entry : quest.getRequiredItems().entrySet()) {
				if(entry.getKey() != questInventory.getStackInSlot(i).getItem() || entry.getValue() > questInventory.getStackInSlot(i).getCount()) {
					flag = false;
				} 
				i++;
			}
		} else {
			flag = false;
		}
	    return flag;
	}
	
	public void handInQuest(int questInt) {
		takeQuestItems(questInt);
		takeKillCount(questInt);
		BigBangPacketHandler.INSTANCE.sendToServer(new Packet_HandInQuest(pos, questInt));
		tileEntity.setQuestCompleted(questInt);
	}
	
	public void takeQuestItems(int questInt) {
		int[] amounts = tileEntity.questline.getQuestByIndex(questInt).getRequiredItemAmounts();	
		for(int i = 0; i < amounts.length; i++) {
			this.questInventory.getStackInSlot(i).shrink(amounts[i]);	
		}
		clearQuestInventory();
	}
	
	public void clearQuestInventory() {
		this.clearContainer(this.player, this.world, this.questInventory);
	}
	
	public void takeKillCount(int questInt) {
		int requiredKills = tileEntity.questline.getQuestByIndex(questInt).getRequiredKills();
		tileEntity.setKillCount(tileEntity.getKillCount() - requiredKills);
	}
	
	public void giveRewards(int questInt) {
        int exp = tileEntity.questline.getQuestByIndex(questInt).getExpReward();
        while(exp > 0) {
	        int expFragment = ExperienceOrbEntity.getXPSplit(exp);
	        exp -= expFragment;
	        world.addEntity(new ExperienceOrbEntity(world, pos.getX(), pos.getY() + 0.5D, pos.getZ() + 0.5D, expFragment));
        }
	}
	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return true;
	}
	
	@Override
	public void onContainerClosed(PlayerEntity playerIn) {
		this.clearContainer(this.player, this.world, this.questInventory);
		super.onContainerClosed(playerIn);
	}
	
	public void ShowInputSlots(int count) {
		for(int i = 0; i < inputSlots.length; i++) {
			if(count > i) {
				this.inputSlots[i].enableInputSlot();				
			} else {
				this.inputSlots[i].disableInputSlot();				
			}
		}
	}
	
	public void HideInputSlots( ) {
		for(int i = 0; i < inputSlots.length; i++) {
			this.inputSlots[i].disableInputSlot();
		}
	}
}
