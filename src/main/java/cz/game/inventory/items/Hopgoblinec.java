package cz.game.inventory.items;

import cz.game.inventory.InventoryItem;
import cz.game.inventory.Items;

public class Hopgoblinec extends InventoryItem {

	private Hopgoblinec() {
		super("hopgoblinec.png", "Hopgoblinec", Items.HOME_HOPGOBLINEC);
	}

	public static InventoryItem create() {
		return new Hopgoblinec();
	}
}