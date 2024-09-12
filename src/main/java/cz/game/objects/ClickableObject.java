package cz.game.objects;

import cz.game.TheGame;
import cz.game.inventory.InventoryItem;
import cz.game.inventory.Items;
import cz.game.utils.Utils;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.function.Consumer;

public abstract class ClickableObject extends JLabel {
	protected State state;

	@Setter
	@Getter
	protected Point dialogPoint;

	@Setter
	protected Runnable onClickWithoutItem;

	@Setter
	protected Consumer<InventoryItem> onClickWithItem;

	protected JToolTip toolTip;

	@Getter
	@Setter
	protected Items itemName;

	protected String originalToolTip;

	public ClickableObject(InputStream imageStream, int positionX, int positionY, String toolTip, Items itemName) {
        try {
            this.setIcon(new ImageIcon(imageStream.readAllBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.setSize(this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
		this.setLocation(positionX, positionY);
		this.setToolTipText(toolTip);
		this.itemName = itemName;
		this.originalToolTip = toolTip;
		this.setDoubleBuffered(true);

		this.setVisible(true);

		this.onClickWithItem = (x) -> {};

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!TheGame.instance.getGamePanel().isDialogActive()) {
					if (TheGame.instance.getInventoryPanel().getSelectedItem().isPresent()) {
						onClickWithItem.accept(TheGame.instance.getInventoryPanel().getSelectedItem().get());
					} else if (onClickWithoutItem != null) {
						onClickWithoutItem.run();
					}
				}
			}
		});

	}

	public abstract State getCurrentState();

	public abstract void delete();

	public void setState(State state) {
		this.state = state;
		switch (state) {
			case TALK:
				this.setCursor(Utils.talkCursor());
				break;
			case HANDLE:
				this.setCursor(Utils.handleCursor());
				break;
			case COLLECT:
				this.setCursor(Utils.grabCursor());
				break;
			case DISCOVER:
				this.setCursor(Utils.discoverCursor());
				break;
			case MOVE:
				this.setCursor(Utils.getWalkCursor());
				break;
		}
	}

	@Override
	public void setIcon(Icon icon) {
		super.setIcon(icon);
		this.paintAll(this.getGraphics());
	}

	public void setIcon(String string) {
		this.setIcon(new ImageIcon(TheGame.class.getResource("/images/" + string)));
	}

	@Override
	public JToolTip createToolTip() {
		if (this.toolTip == null) {
			JToolTip tip = super.createToolTip();
			tip.setDoubleBuffered(true);
			tip.setOpaque(false);
			tip.setBorder(null);
			tip.setForeground(Color.BLACK);
			tip.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
			tip.setCursor(this.getCursor());
			tip.setBackground(Color.GREEN);
			this.toolTip = tip;
		}

		return this.toolTip;
	}

	public void changeTooltip(String toolTip, boolean remember) {
		if (remember) {
			this.originalToolTip = toolTip;
		}
		this.setToolTipText(toolTip);
		this.setOpaque(false);
	}

	public void resetToolTip() {
		this.setToolTipText(this.originalToolTip);
	}

	@Override
	public Point getToolTipLocation(MouseEvent e) {
		Point p = e.getPoint();
		if (e.getLocationOnScreen().x + this.createToolTip().getSize().width > TheGame.WINDOW_WIDTH) {
			p.x += -100;
		} else {
			p.x += 25;
		}

		p.y += 30;
		return p;
	}
}