package cz.game.dialog;

import cz.game.TheGame;
import cz.game.objects.Item;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class Dialog extends JPanel {
	static int DIALOG_BUBBLE_WIDTH = 427;
	static int DIALOG_BUBBLE_HEIGHT = 320;
	private boolean bottomOriented = false;

	private final Item startedBy;
	private BufferedImage dialogBubble;
	private DialogStep dialogOptions;

	@SneakyThrows
	public Dialog(Item startedBy, DialogStep dialogOptions) {
		super();
		this.startedBy = startedBy;
		this.dialogOptions = dialogOptions;
		this.setVisible(true);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setOpaque(false);
		this.setSize(DIALOG_BUBBLE_WIDTH, DIALOG_BUBBLE_HEIGHT);
		this.setPreferredSize(this.getSize());
	}

	public void start() {
		TheGame.instance.getGamePanel().dialogStarted();
		this.setLocation(this.countLocationAndLoadBubble());
		this.loadDialogOptions(this.dialogOptions);
		startedBy.getParent().add(this, 1);
		startedBy.getParent().revalidate();
		startedBy.getParent().repaint();
	}

	public void close() {
		Container parent = this.getParent();
		parent.remove(this);
		TheGame.instance.getGamePanel().dialogEnded();
		parent.revalidate();
		parent.repaint();
	}

	public void loadDialogOptions(DialogStep dialogOptions) {
		if (dialogOptions.getSpeech() != null) {
			TheGame.instance.getAudioPlayer().playSound(dialogOptions.getSpeech());
		}

		this.removeAll();
		this.revalidate();
		this.repaint();

		JLabel line = new JLabel("-------------------------------------");
		line.setAlignmentX(CENTER_ALIGNMENT);
		line.setBorder(new CompoundBorder(this.getBorder(), new EmptyBorder(0, 0, 7, 0)));

		this.add(new Answer(this, dialogOptions.getAnswer(), this.bottomOriented));

		this.add(line);
		dialogOptions.getResponses().keySet().forEach(text -> {
			var option = dialogOptions.getResponses().get(text);
			var predicate = Optional.ofNullable(option).map(DialogStep::getCondition).orElse(null);
			if (predicate == null || predicate.test(TheGame.instance)) {
				DialogOption opt = new DialogOption(text);
				opt.setOnClick(Optional.ofNullable(option).map(DialogStep::getOnClick).orElse(null));
				this.add(opt);
			}
		});
		this.add(new EndDialogOption());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(dialogBubble, 0, 0, null);
		revalidate();
	}

	@SneakyThrows
	private Point countLocationAndLoadBubble() {
		Point point = this.startedBy.getDialogPoint();
		Dimension size = TheGame.instance.getGamePanel().getSize();

		if ((size.getWidth() / 2) > point.getX()) {
			if ((size.getHeight() / 2) > point.getY()) {
				this.dialogBubble = ImageIO.read(TheGame.class.getResource("/images/dialog/dialog_left_top.png"));
				this.bottomOriented = true;
				return new Point((int) point.getX(), (int) point.getY());
			}
			this.dialogBubble = ImageIO.read(TheGame.class.getResource("/images/dialog/dialog_left_bottom.png"));
			return new Point((int) point.getX(), (int) point.getY() - DIALOG_BUBBLE_HEIGHT);
		} else {
			if ((size.getHeight() / 2) > point.getY()) {
				this.dialogBubble = ImageIO.read(TheGame.class.getResource("/images/dialog/dialog_right_top.png"));
				this.bottomOriented = true;
				return new Point((int) point.getX() - DIALOG_BUBBLE_WIDTH, (int) point.getY());
			}
			this.dialogBubble = ImageIO.read(TheGame.class.getResource("/images/dialog/dialog_right_bottom.png"));
			return new Point((int) point.getX() - DIALOG_BUBBLE_WIDTH, (int) point.getY() - DIALOG_BUBBLE_HEIGHT);
		}
	}
}