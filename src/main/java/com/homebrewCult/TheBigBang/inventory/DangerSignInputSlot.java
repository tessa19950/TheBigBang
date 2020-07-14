package com.homebrewCult.TheBigBang.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;

public class DangerSignInputSlot extends Slot {

	private boolean isEnabled;
	
	public DangerSignInputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
	
	public void enableInputSlot() {
		isEnabled = true;
	}
	
	public void disableInputSlot() {
		isEnabled = false;
	}

}
