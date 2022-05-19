package cz.game.scenes;

import cz.game.TheGame;
import cz.game.inventory.Items;
import cz.game.objects.Item;
import cz.game.objects.State;

public class HomeWindow extends Scene {
	private final static String RESOURCE_PREFIX = "/scenes/window_home/";

	public HomeWindow() {
		this.setBackgroundImageUrl(TheGame.class.getResource("/images" + RESOURCE_PREFIX + "window.png"));

		final Item emptyBack = new Item(RESOURCE_PREFIX + "emptyBack.png", State.MOVE, 1, 462, "Zpátky", Items.HOME_WINDOW_BACK);
		emptyBack.setOnClickWithoutItem(() -> TheGame.instance.loadScene(Scenes.HOME));
		objects.add(emptyBack);
	}
}