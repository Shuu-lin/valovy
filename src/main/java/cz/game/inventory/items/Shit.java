package cz.game.inventory.items;

import cz.game.inventory.InventoryItem;

import static cz.game.inventory.Items.SHIT;

public class Shit extends InventoryItem {
	private Shit() {
		super("shit.png", "Hovno", SHIT);
	}

	public static InventoryItem create() {
		return new Shit();
	}
}