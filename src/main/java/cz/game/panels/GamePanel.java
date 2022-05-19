package cz.game.panels;

import cz.game.inventory.InventoryItem;
import cz.game.scenes.Scene;
import cz.game.utils.Utils;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

@Setter
public class GamePanel extends JLayeredPane {
	private URL backgroundImage;

	@Getter
	private Scene loadedScene;

	@Getter
	private boolean dialogActive;

	public GamePanel(int width, int height) {
		this.setLayout(null);
		this.setSize(width, height);
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.BLACK);
		this.setVisible(true);
	}

	/**
	 * Returns scene objects to state before item selection.
	 */
	public void resetToOriginalVals() {
		this.loadedScene.getObjects().forEach(item -> {
			item.setState(item.getCurrentState());
			item.resetToolTip();
		});
	}

	public void dialogStarted() {
		this.dialogActive = true;
	}

	public void dialogEnded() {
		this.dialogActive = false;
	}

	public void fireItemSelectedEvent(InventoryItem selectedItem) {
		this.loadedScene.getObjects().forEach(item -> {
			item.setCursor(Utils.getUseCursor());
			item.changeTooltip("Použít " + selectedItem.getItemName().label + " na " + item.getItemName().label, false);
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			g.drawImage(ImageIO.read(this.backgroundImage), 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
