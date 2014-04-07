package teamtofu.isaac.item;

import teamtofu.isaac.Isaac;
import net.minecraft.item.Item;

public class ItemCollectable extends Item implements ICollectableItem {

	public ItemCollectable() {
		super();
		setCreativeTab(Isaac.tab);
	}
	
	@Override
	public boolean hasPickupText() {
		return true;
	}
	

}
