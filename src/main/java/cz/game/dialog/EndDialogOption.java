package cz.game.dialog;

public class EndDialogOption extends DialogOption {

	public EndDialogOption() {
		this("[ Konec ]");

	}
	public EndDialogOption(final String label) {
		super(label);
		this.setOnClick(Dialog::close);
	}
}