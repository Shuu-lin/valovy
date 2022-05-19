package cz.game.panels;

import javax.swing.*;
import java.awt.*;

public class DialogPanel extends JPanel {
	final private JLabel text;

	public DialogPanel(int width, int height) {
		this.setLayout(new FlowLayout(FlowLayout.LEADING));
		this.setSize(width, height);
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.BLACK);
		this.setVisible(true);
		text = new JLabel();
		text.setForeground(Color.WHITE);
		text.setAlignmentX(CENTER_ALIGNMENT);
		text.setSize(width, height);
		text.setMaximumSize(text.getSize());
		text.setPreferredSize(text.getSize());
		text.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		this.add(text);
	}

	public void showMessage(String text) {
		this.text.setText("<html><center>" + text + "</center></html>");
	}
}
