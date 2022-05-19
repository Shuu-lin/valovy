package cz.game.inventory.items;

import cz.game.inventory.InventoryItem;
import cz.game.inventory.Items;

public class PlasticGloves extends InventoryItem {

	private PlasticGloves() {
		super("plastic_gloves.png", "Gumové rukavice", Items.PLASTIC_GLOVES);
	}

	public static InventoryItem create() {
		return new PlasticGloves();
	}
}