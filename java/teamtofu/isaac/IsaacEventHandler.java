package teamtofu.isaac;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import teamtofu.isaac.item.ICollectableItem;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class IsaacEventHandler {
	
	@SubscribeEvent
	public void handleHitEvents(LivingHurtEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if (Util.hasIsaacItem(player, new ItemStack(Isaac.wafer))) {
				event.ammount = 1.0F;
			}
		}
	}
	
	@SubscribeEvent
	public void consumeCollectables(LivingUpdateEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			for (int i = 0; i<player.inventory.getSizeInventory(); i++) {
				if (player.inventory.getStackInSlot(i) != null && player.inventory.getStackInSlot(i).getItem() instanceof ICollectableItem) {
					if (((ICollectableItem) player.inventory.getStackInSlot(i).getItem()).hasPickupText()) {
						Util.addIsaacItemWithChatNotification(player, player.inventory.getStackInSlot(i));
					}
					else
					{
						Util.addIsaacItem(player, player.inventory.getStackInSlot(i));
					}
					player.inventory.setInventorySlotContents(i, null);
				}
			}
		}
	}
	
}
