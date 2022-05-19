package cz.game.dialog;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class DialogOption extends JLabel {

	@Setter
	@Getter
	protected Consumer<Dialog> onClick;

	@Setter
	@Getter
	public Runnable reloadDialog;

	public DialogOption(String label) {
		super(label);
		this.setSize(60, 60);
		this.setPreferredSize(this.getSize());
		this.setVisible(true);
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.setFont(new Font("Comic Sans MS", Font.BOLD, 17));

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(final MouseEvent e) {
				e.getComponent().setForeground(Color.RED);
			}
			@Override
			public void mouseExited(final MouseEvent e) {
				e.getComponent().setForeground(Color.BLACK);
			}
			@Override
			public void mousePressed(final MouseEvent e) {
				DialogOption option = (DialogOption) e.getComponent();
				if (option.getOnClick() != null) {
					option.onClick.accept((Dialog) option.getParent());
				}
			}
		});
	}
}