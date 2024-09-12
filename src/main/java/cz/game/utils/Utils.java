package cz.game.utils;

import cz.game.TheGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Utils {
	private static Cursor useCursor;
	private static Cursor grabCursor;
	private static Cursor walkCursor;
	private static Cursor handleCursor;
	private static Cursor talkCursor;

	public static Cursor grabCursor() {
		if (grabCursor == null) {
			grabCursor = getCursorByName("hand.png");
		}
		return grabCursor;
	}
	public static Cursor handleCursor() {
		if (handleCursor == null) {
			handleCursor = getCursorByName("handle.png");
		}
		return handleCursor;
	}

	public static Cursor talkCursor() {
		if (talkCursor == null) {
			talkCursor = getCursorByName("talk.png");
		}
		return talkCursor;
	}

	public static Cursor discoverCursor() {
		return getCursorByName("magnifyingGlass.png");
	}

	public static Cursor getWalkCursor() {
		if (walkCursor == null) {
			walkCursor = getCursorByName("feet.png");
		}
		return walkCursor;
	}

	public static Cursor getUseCursor() {
		if (useCursor == null) {
			useCursor = getCursorByName("use.png");
		}
		return useCursor;
	}

	public static Cursor getCursorByName(String filename) {
		InputStream imgStream = TheGame.class.getResourceAsStream("/resources/cursors/" + filename);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(TheGame.class.getResource("/cursors/" + filename));
		Image a = image.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
		return toolkit.createCustomCursor(a, new Point(0, 0), "asdas");
	}

	public static Image iconToImage(Icon icon) {
		if (icon instanceof ImageIcon) {
			return ((ImageIcon)icon).getImage();
		}
		else {
			int w = icon.getIconWidth();
			int h = icon.getIconHeight();
			GraphicsEnvironment ge =
					GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice gd = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gd.getDefaultConfiguration();
			BufferedImage image = gc.createCompatibleImage(w, h);
			Graphics2D g = image.createGraphics();
			icon.paintIcon(null, g, 0, 0);
			g.dispose();
			return image;
		}
	}
}
