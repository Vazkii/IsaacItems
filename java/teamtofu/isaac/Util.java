package teamtofu.isaac;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StatCollector;

public class Util {
	
	public static ArrayList<ItemStack> getIsaacInventory(EntityPlayer player) {
		if (player.getEntityData().hasKey("isaacItems")) {
			NBTTagCompound nbttagcompound = player.getEntityData();
			NBTTagList nbttaglist = (NBTTagList) nbttagcompound.getTag("isaacItems");
			ArrayList<ItemStack> isaacItems = new ArrayList<ItemStack>();
			for (int i = 0; i < nbttaglist.tagCount(); i++)
			{
				NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
				byte b0 = nbttagcompound1.getByte("Slot");
				isaacItems.add(ItemStack.loadItemStackFromNBT(nbttagcompound1));
			}
			return isaacItems;
		}
		else
		{
			return new ArrayList<ItemStack>();
		}
	}
	
	public static boolean hasIsaacItem(EntityPlayer player, Item item) {
		ArrayList<ItemStack> inv = getIsaacInventory(player);
		boolean contains = false;
		for (ItemStack i : inv) {
			if (i.getItem() == item) {
				contains = true;
			}
		}
		return contains;
	}
	
	public static void addIsaacItem(EntityPlayer player, ItemStack item) {
		if (!player.getEntityData().hasKey("isaacItems")) {
			NBTTagList nbttaglist = new NBTTagList();
			ItemStack item2 = item.copy();
			item.stackSize = 1;
			player.getEntityData().setTag("isaacItems", nbttaglist);	
		}
		NBTTagCompound nbttagcompound = player.getEntityData();
		NBTTagList nbttaglist = (NBTTagList) nbttagcompound.getTag("isaacItems");
		NBTTagCompound nbttagcompound1 = new NBTTagCompound();
		item.writeToNBT(nbttagcompound1);
		nbttaglist.appendTag(nbttagcompound1);
	}
	
	public static void addIsaacItemWithChatNotification(EntityPlayer player, ItemStack item) {
		addIsaacItem(player,item);
		player.addChatMessage(new ChatComponentTranslation(StatCollector.translateToLocal(item.getUnlocalizedName()+".pickupText"),new Object()));
	}
}
