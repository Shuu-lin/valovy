package cz.game.inventory.items;

import cz.game.inventory.InventoryItem;

import static cz.game.inventory.Items.HOME_KEY;

public class HomeKey extends InventoryItem {
	private HomeKey() {
		super("home_key.png", "Klíč z domu", HOME_KEY);
	}

	public static InventoryItem create() {
		return new HomeKey();
	}
}