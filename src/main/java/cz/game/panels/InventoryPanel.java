package cz.game.panels;

import cz.game.inventory.InventoryItem;
import cz.game.inventory.Items;
import cz.game.objects.Item;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class InventoryPanel extends JPanel {
	private List<InventoryItem> inventoryItems = new ArrayList<>();
	private Optional<InventoryItem> selectedItem = Optional.empty();

	public InventoryPanel(int width, int height) {
		this.setLayout(new FlowLayout(FlowLayout.LEADING));
		this.setSize(width, height);
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));
		this.setVisible(true);
	}

	public boolean hasItem(Items item) {
		return this.inventoryItems.stream().anyMatch(x -> x.getItemName() == item);
	}

	public void addInventoryItem(InventoryItem item) {
		this.inventoryItems.add(item);
		this.add(item);
		item.setVisible(true);
	}

	public void setSelectedItem(InventoryItem item) {
		if (item == null && this.selectedItem.isPresent()) {
			this.selectedItem.get().deselect();
		}
		this.selectedItem = Optional.ofNullable(item);
	}

	public void deleteItem(Items item) {
		this.inventoryItems.stream().filter(x -> x.getItemName() == item).findFirst().ifPresent(InventoryItem::delete);
	}

	public void deleteItem(InventoryItem item) {
		this.inventoryItems.remove(item);
		this.remove(item);
	}
}