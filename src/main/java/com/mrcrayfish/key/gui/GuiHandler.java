package com.mrcrayfish.key.gui;

import com.mrcrayfish.key.items.ItemKeys;
import com.mrcrayfish.key.tileentity.TileEntityKeyRack;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GuiKeys.ID) {
			return new GuiKeys(player.inventory, ItemKeys.getInv(player), player);
		}
		if (ID == GuiKeyRack.ID) {
			TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
			if (tileEntity instanceof TileEntityKeyRack) {
				return new GuiKeyRack(player.inventory, (TileEntityKeyRack) tileEntity);
			}
		}
		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GuiKeys.ID) {
			return new ContainerKeys(player.inventory, ItemKeys.getInv(player));
		}
		if (ID == GuiKeyRack.ID) {
			TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
			if (tileEntity instanceof TileEntityKeyRack) {
				return new ContainerKeyRack(player.inventory, (TileEntityKeyRack) tileEntity);
			}
		}
		return null;
	}
}
