package teamtofu.isaac.item;

import net.minecraft.item.Item;

public class ItemCollectable extends Item implements ICollectableItem {

	@Override
	public boolean hasPickupText() {
		return true;
	}

}
