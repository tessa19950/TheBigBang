package com.homebrewCult.TheBigBang.gui;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.gui.quests.Quests;
import com.homebrewCult.TheBigBang.inventory.DangerSignContainer;
import com.homebrewCult.TheBigBang.network.BigBangPacketHandler;
import com.homebrewCult.TheBigBang.network.Packet_ClearQuestSlots;
import com.homebrewCult.TheBigBang.network.Packet_HideInputSlots;
import com.homebrewCult.TheBigBang.network.Packet_ShowInputSlots;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class DangerSignScreen extends ContainerScreen<DangerSignContainer> {

	private ResourceLocation DANGER_SIGN_GUI = new ResourceLocation(TheBigBang.MODID, "textures/gui/danger_sign_gui.png"); 
	
	private int BUTTON_XPOS = 8, BUTTON_WIDTH = 160, BUTTON_HEIGHT = 19; 
	private int ACCEPT_XPOS = 133, ACCEPT_YPOS = 149, ACCEPT_WIDTH = 35, ACCEPT_HEIGHT = 11, ACCEPT_UVX = 256, ACCEPT_UVY = 184;
	private int INPUTSLOT_XPOS = 54, INPUTSLOT_YPOS = 122, INPUTSLOT_WIDTH = 18, INPUTSLOT_HEIGHT = 18, INPUTSLOT_SPACING = 4, INPUTSLOT_UVX = 256, INPUTSLOT_UVY = 166;
	private int questlogX, questlogY, detailsX, detailsY;
	private int prevDetailsPos;
	private int nextDetailsPos;
	
	private Quests[] availableQuests;
	private int questSelected = -1;
	private String detailsText = "";
	private int inputSlotCount = 0;
	
	public DangerSignScreen(DangerSignContainer container, PlayerInventory playerInventory, ITextComponent titleIn) {
		super(container, playerInventory, titleIn);
		updateAvailableQuests();
	}
	
	@Override
	public void tick() {	
		super.tick();
		if(questSelected == -1) { //No quest selected
			if (nextDetailsPos > 0) {
				prevDetailsPos = nextDetailsPos;
				nextDetailsPos -= 16;
			} else {
				prevDetailsPos = 0;
				nextDetailsPos = 0;
			}
		} else { //Quest selected
			if(nextDetailsPos < 132) {
				prevDetailsPos = nextDetailsPos;
				nextDetailsPos += 16;
			} else {
				prevDetailsPos = 132;
				nextDetailsPos = 132;
			}
		}
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseScreenPosX, int mouseScreenPosY) {  
	      minecraft.getTextureManager().bindTexture(DANGER_SIGN_GUI);
	      GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
	      
	      //Setting base panel positions and sizes
	      int detailsOffset = Math.round(prevDetailsPos + (nextDetailsPos - prevDetailsPos) * partialTicks);
	      questlogX = (width - xSize) / 2 - 66;
	      questlogY = (height - ySize) / 2;
	      detailsX = (width - xSize) / 2 - 66 + detailsOffset;
	      detailsY = (height - ySize) / 2;
	      
	      //Drawing of the details panel
	      blit(detailsX, detailsY, blitOffset, 256F, 0.0F, xSize, ySize, 256, 512);
	      
          //Drawing the description text
    	  int color = 0x555555;
    	  int wrapwidth = 126;
    	  font.drawSplitString(detailsText, detailsX + 48, detailsY + 5, wrapwidth, color);
	      minecraft.getTextureManager().bindTexture(DANGER_SIGN_GUI);
	      GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
    	  
    	  //Drawing of the input slots
    	  for(int i = 0; i < inputSlotCount; i++) {     
    		  blit(detailsX + INPUTSLOT_XPOS + (INPUTSLOT_SPACING + INPUTSLOT_WIDTH) * i, detailsY + INPUTSLOT_YPOS, INPUTSLOT_UVX, INPUTSLOT_UVY, INPUTSLOT_WIDTH, INPUTSLOT_HEIGHT, 512, 256);
    	  }  
		  
	      //Drawing of the accept button
		  int mouseX = mouseScreenPosX - (detailsX + ACCEPT_XPOS);
		  int mouseY = mouseScreenPosY - (detailsY + ACCEPT_YPOS);
		  int uvY = ACCEPT_UVY;
		  if(questSelected != -1) {
			  if(container.canHandInQuest(availableQuests[questSelected])) {
				  if (mouseX >= 0 && mouseY >= 0 && mouseX < ACCEPT_WIDTH && mouseY < ACCEPT_HEIGHT) {
					  uvY += ACCEPT_HEIGHT * 2;
		          } else {
		        	  uvY += ACCEPT_HEIGHT * 1;
		          }
			  } 
		  } 
	      blit(detailsX + ACCEPT_XPOS, detailsY + ACCEPT_YPOS, ACCEPT_UVX, uvY, ACCEPT_WIDTH, ACCEPT_HEIGHT, 512, 256);
	      
	      //Drawing of the quest log panel
	      blit(questlogX, questlogY, blitOffset, 0.0F, 0.0F, xSize, ySize, 256, 512);
	      
	      //Drawing of the quest log buttons
	      for(int buttonIndex = 0; buttonIndex < availableQuests.length && buttonIndex < 3; ++buttonIndex)
	      {
		      minecraft.getTextureManager().bindTexture(DANGER_SIGN_GUI);
	    	  GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
	    	  blitOffset = 0;
	    	  int buttonX = questlogX + BUTTON_XPOS;
	    	  int buttonY = questlogY + 14;
	    	  color = 0x555555;
	    	  
	    	  //Draw the Button
	    	  boolean selected = (questSelected == buttonIndex);
	    	  if(selected) { //Draw Selected Version
	        	  blit(buttonX, buttonY + BUTTON_HEIGHT * buttonIndex, 0, 204, BUTTON_WIDTH, BUTTON_HEIGHT, 512, 256);
	              color = 0xD6D6D6;
	    	  } else { //Draw MouseOver or Normal Version
	    		  mouseX = mouseScreenPosX - (questlogX + 8);
	    		  mouseY = mouseScreenPosY - (questlogY + 14 + BUTTON_HEIGHT * buttonIndex);
	    		  if (mouseX >= 0 && mouseY >= 0 && mouseX < BUTTON_WIDTH && mouseY < BUTTON_HEIGHT) {
	            	  blit(buttonX, buttonY + BUTTON_HEIGHT * buttonIndex, 0, 185, BUTTON_WIDTH, BUTTON_HEIGHT, 512, 256);
		          } else {
		        	  blit(buttonX, buttonY + BUTTON_HEIGHT * buttonIndex, 0, 166, BUTTON_WIDTH, BUTTON_HEIGHT, 512, 256);
		          }
	    	  }  
              //Draw the Icon
              blit(buttonX + 2, buttonY + BUTTON_HEIGHT * buttonIndex, 0, 223, 16, 16, 512, 256); 
              //Draw the Title text
	    	  String title = availableQuests[buttonIndex].getTitle();
	    	  wrapwidth = BUTTON_WIDTH - 10;
	    	  font.drawSplitString(title, buttonX + 20, questlogY + 19 + BUTTON_HEIGHT * buttonIndex, wrapwidth, color);
	      }
	      
    	  //Drawing of the required items
		  if(questSelected != -1 && nextDetailsPos >= 132) {
	    	  for(int i = 0; i < inputSlotCount; i++) {
	 	          int j = detailsX + INPUTSLOT_XPOS + 1 + (INPUTSLOT_SPACING + INPUTSLOT_WIDTH) * i;
		          int k = detailsY + INPUTSLOT_YPOS + 1;
	    	      ItemStack itemStack = new ItemStack(availableQuests[questSelected].getRequiredItemByIndex(i), availableQuests[questSelected].getRequiredItemAmountByIndex(i));
	    	      renderGhostItem(itemStack, j, k);
	    	  }
		  }   	
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		font.drawString("Wanted", -58, 5, 4210752);
		font.drawString(playerInventory.getDisplayName().getFormattedText(), -58, (float)(ySize - 96 + 4), 4210752);
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
	      renderBackground();
	      super.render(mouseX, mouseY, partialTicks);
	      renderHoveredToolTip(mouseX, mouseY);
	}
	
	public void renderGhostItem(ItemStack stack, int x, int y) {
	      RenderHelper.enableGUIStandardItemLighting();
	      GlStateManager.disableLighting();
	      
	      itemRenderer.renderItemAndEffectIntoGUI(stack, x, y);
	      GlStateManager.depthFunc(516);
          AbstractGui.fill(x, y, x + 16, y + 16, 0xC9365A7F);
          GlStateManager.depthFunc(515);
          renderGhostItemOverlay(font, stack, x, y);
          
	      GlStateManager.enableLighting();
	      RenderHelper.disableStandardItemLighting();
	}
	
	public void renderGhostItemOverlay(FontRenderer font, ItemStack stack, int x, int y) {
		if (!stack.isEmpty()) {
		    if (stack.getCount() != 1) {
		       String s = String.valueOf(stack.getCount());
		       GlStateManager.disableLighting();
		       GlStateManager.disableDepthTest();
		       font.drawString(s, (float)(x + 20 - 2 - font.getStringWidth(s)), (float)(y + 7 + 3), 0x26405A);
		       font.drawString(s, (float)(x + 19 - 2 - font.getStringWidth(s)), (float)(y + 6 + 3), 0xA5BFD9);
		       GlStateManager.enableLighting();
		       GlStateManager.enableDepthTest();
		    }
		}
	}	

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int p_mouseClicked_5_) 
	{
		for(int buttonIndex = 0; buttonIndex < availableQuests.length && buttonIndex < 3; ++buttonIndex) 
		{
			double d0 = mouseX - (double)(questlogX + BUTTON_XPOS);
			double d1 = mouseY - (double)(questlogY + 14 + BUTTON_HEIGHT * buttonIndex);
			if (d0 >= 0.0D && d1 >= 0.0D && d0 < BUTTON_WIDTH && d1 < BUTTON_HEIGHT) 
			{
				buttonClicked(buttonIndex);
				return true;
			}
		}
		if(questSelected != -1) {
			double d0 = mouseX - (double)(detailsX + ACCEPT_XPOS);
			double d1 = mouseY - (double)(detailsY + ACCEPT_YPOS);
			if (d0 >= 0.0D && d1 >= 0.0D && d0 < ACCEPT_WIDTH && d1 < ACCEPT_HEIGHT) {
				acceptButtonClicked(availableQuests[questSelected]);
				return true;
			}
		}
		return super.mouseClicked(mouseX, mouseY, p_mouseClicked_5_);
	}
	
	public void buttonClicked(int index) {
		if(index == questSelected) {
			questSelected = -1;
			container.hideInputSlots();
			BigBangPacketHandler.INSTANCE.sendToServer(new Packet_HideInputSlots(container.tileEntity.getPos()));
		} else {
			questSelected = index;
			inputSlotCount = availableQuests[questSelected].getRequiredItems().size();
			container.showInputSlots(inputSlotCount);
			BigBangPacketHandler.INSTANCE.sendToServer(new Packet_ShowInputSlots(container.tileEntity.getPos(), inputSlotCount));
		}
		BigBangPacketHandler.INSTANCE.sendToServer(new Packet_ClearQuestSlots(container.tileEntity.getPos()));
		updateDescriptionText();
	}
	
	public void acceptButtonClicked(Quests quest) {
		if(container.canHandInQuest(quest)) {
			container.handInQuest(container.tileEntity.questline.getIndexByQuest(quest));
			updateAvailableQuests();
		}
	}
	
	public void updateAvailableQuests() {
		availableQuests = container.tileEntity.getAvailableQuests();
		questSelected = -1;
	}
	
	public void updateDescriptionText() {
		if(questSelected != -1) {
			Quests quest = availableQuests[questSelected];
			detailsText = quest.getDescription();
			if(quest.getRequiredKills() > 0) {
				String name = container.tileEntity.questline.getName();
				detailsText = detailsText.replaceAll("#entity#", name);
				detailsText = detailsText + "\n" + name + " " + container.tileEntity.getKillCount() + "/" + availableQuests[questSelected].getRequiredKills();
			}
		}
	}
}
