package cz.game.inventory.items;

import cz.game.inventory.InventoryItem;
import cz.game.inventory.Items;

public class ToiletPaper extends InventoryItem {

	private ToiletPaper() {
		super("toilet_paper.png", "Hopgoblinec", Items.TOILET_PAPER);
	}

	public static InventoryItem create() {
		return new ToiletPaper();
	}
}
