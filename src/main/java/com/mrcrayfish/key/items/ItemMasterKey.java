package com.mrcrayfish.key.items;

import com.mrcrayfish.key.lock.LockData;
import com.mrcrayfish.key.lock.WorldLockData;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.LockCode;
import net.minecraft.world.World;

public class ItemMasterKey extends Item {
	public ItemMasterKey() {
		setMaxStackSize(1);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side,
			float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			EntityPlayerMP playerMp = (EntityPlayerMP) playerIn;
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if (tileEntity instanceof TileEntityLockable) {
				TileEntityLockable tileEntityLockable = (TileEntityLockable) tileEntity;
				if (tileEntityLockable.isLocked()) {
					tileEntityLockable.setLockCode(LockCode.EMPTY_CODE);
					playerMp.playerNetServerHandler.sendPacket(new S02PacketChat(
							(new ChatComponentText(EnumChatFormatting.GREEN + "Succesfully unlocked this block.")),
							(byte) 2));
				}
			} else if ((worldIn.getBlockState(pos).getBlock() instanceof BlockDoor)
					&& (worldIn.getBlockState(pos).getBlock() != Blocks.iron_door)) {
				worldIn.getBlockState(pos).getBlock();
				IBlockState state = worldIn.getBlockState(pos);
				if ((state.getValue(BlockDoor.HALF)) == BlockDoor.EnumDoorHalf.UPPER) {
					pos = pos.down();
				}

				WorldLockData lockedDoorData = WorldLockData.get(worldIn);
				LockData lockedDoor = lockedDoorData.getLock(pos);
				if (lockedDoor != null) {
					if (lockedDoor.isLocked()) {
						lockedDoor.setLockCode(LockCode.EMPTY_CODE);
						playerMp.playerNetServerHandler.sendPacket(new S02PacketChat(
								(new ChatComponentText(EnumChatFormatting.GREEN + "Succesfully unlocked this block.")),
								(byte) 2));
						lockedDoorData.markDirty();
					}
				}
			}
		}
		return true;
	}
}