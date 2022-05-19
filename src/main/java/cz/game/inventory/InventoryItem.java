package cz.game.inventory;

import cz.game.TheGame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Getter
@Setter
public class InventoryItem extends JLabel {
	private boolean selected;
	private Items itemName;

	public InventoryItem(String imageUrl, String toolTip, Items itemName) {
		this.setIcon(new ImageIcon(TheGame.class.getResource("/images/inventoryItems/" + imageUrl)));
		this.setSize(TheGame.instance.getInventoryPanel().getHeight() - 5, TheGame.instance.getInventoryPanel().getHeight() - 5);
		this.setSize(this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
		this.setToolTipText(toolTip);
		this.itemName = itemName;
		this.addMouseListener(this.createMouseAdapter());

		this.setVisible(true);
	}

	@Override
	public JToolTip createToolTip() {
		JToolTip tip = super.createToolTip();
		tip.setOpaque(false);
		tip.setBorder(null);
		tip.setForeground(Color.WHITE);
		tip.setFont(new Font("Comic Sans MS", Font.BOLD,  18));
		tip.setCursor(this.getCursor());
		return tip;
	}

	public void delete() {
		this.setVisible(false);
		TheGame.instance.getInventoryPanel().deleteItem(this);
	}

	private MouseAdapter createMouseAdapter() {
		return new MouseAdapter() {

			@Override
			public void mousePressed (MouseEvent e) {
				if (isSelected()) {
					deselect();
					return;
				}
				select();
			}
		};
	}

	public void deselect() {
		if (this.isSelected()) {
			setBorder(null);
			setSelected(false);
			TheGame.instance.setSelectedItem(null);
			TheGame.instance.getGamePanel().resetToOriginalVals();
		}
	}

	public void select() {
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		setSelected(true);
		TheGame.instance.setSelectedItem(getMe());
		TheGame.instance.getGamePanel().fireItemSelectedEvent(this);
	}

	@Override
	public Point getToolTipLocation(MouseEvent e) {
		Point p = e.getPoint();
		p.y += 15;
		p.x += 5;
		return p;
	}

	private InventoryItem getMe() {
		return this;
	}
}