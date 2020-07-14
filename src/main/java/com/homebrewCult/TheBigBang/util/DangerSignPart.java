package com.homebrewCult.TheBigBang.util;

import net.minecraft.util.IStringSerializable;

public enum DangerSignPart implements IStringSerializable {

	BASE("base"),
	TOPLEFT("topleft"),
	TOPRIGHT("topright"),
	BOTTOMRIGHT("bottomright");
	
	private final String name;
	
	private DangerSignPart(String name) {
	   this.name = name;
	}
	
	public String toString() {
	   return this.name;
	}
	
	public String getName() {
	   return this.name;
	}
	
}