package com.mrcrayfish.key.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiKeyRack extends GuiContainer {
	public static final int ID = 1;

	private static final ResourceLocation gui = new ResourceLocation("ckm:textures/gui/key_rack.png");
	private IInventory inventoryKeys;

	public GuiKeyRack(IInventory inventoryPlayer, IInventory inventoryKeys) {
		super(new ContainerKeyRack(inventoryPlayer, inventoryKeys));
		this.inventoryKeys = inventoryKeys;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(GuiKeyRack.gui);
		int l = (width - xSize) / 2;
		int i1 = (height - ySize) / 2;
		this.drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString(inventoryKeys.getName(), (xSize / 2) - 21, 5, 4210752);
		fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 4210752);
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
	}
}
