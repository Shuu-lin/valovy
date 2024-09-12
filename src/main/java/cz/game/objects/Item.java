package cz.game.objects;

import cz.game.TheGame;
import cz.game.inventory.Items;

public class Item extends ClickableObject {

	public Item(String imageName, State state, int locationX, int locationY, String toolTip, Items itemName) {
		super(TheGame.class.getClassLoader().getResourceAsStream("/images/" + imageName),//TheGame.class.getResourceAsStream("/images/" + imageName),
		      locationX,
		      locationY,
		      toolTip,
		      itemName);
		this.setState(state);
	}

	@Override
	public State getCurrentState() {
		return this.state;
	}

	@Override
	public void delete() {
		this.setVisible(false);
		this.getParent().remove(this);
		TheGame.instance.getGamePanel().getLoadedScene().getObjects().remove(this);
	}
}