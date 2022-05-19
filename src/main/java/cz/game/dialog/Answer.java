package cz.game.dialog;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Answer extends JLabel {
	private final Dialog dialog;

	public Answer(Dialog dialog, String label, boolean bottomOriented) {
		if (label.length() > 35) {
			label = "<html><center>" + label + "</center></html>";
			System.out.println("adding html " + label);
		}
		this.setText(label);
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.setVisible(true);
		this.setSize(dialog.getWidth(), 50);
		this.setPreferredSize(this.getSize());
		this.setFont(new Font("Comic Sans MS", Font.BOLD,  18));
		int top = bottomOriented ? 100 : 20;
		this.setBorder(new CompoundBorder(this.getBorder(), new EmptyBorder(top, 20, 7, 20)));
		this.dialog = dialog;
	}

	public Answer(Dialog dialog, String label) {
		this(dialog, label, false);
	}
}