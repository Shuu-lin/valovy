package cz.game.scenes;

import cz.game.TheGame;
import cz.game.inventory.Items;
import cz.game.objects.Item;
import cz.game.objects.State;

public class Map extends Scene {
	private final static String RESOURCE_PREFIX = "/scenes/map/";

	public Map() {
		this.setBackgroundImageUrl(TheGame.class.getResource("/images" + RESOURCE_PREFIX + "map.png"));

		Item church = new Item(RESOURCE_PREFIX + "church.png", State.MOVE, 391, 76, "Ke kostelu", Items.MAP_CHURCH);
		church.setOnClickWithoutItem(() -> TheGame.instance.loadScene(Scenes.OUTSIDE_CHURCH));
		this.objects.add(church);

		Item hruska = new Item(RESOURCE_PREFIX + "hruska.png", State.MOVE, 1169, 507, "K Hrušce", Items.MAP_HRUSKA);
		hruska.setOnClickWithoutItem(() -> TheGame.instance.loadScene(Scenes.OUTSIDE_HRUSKA));
		this.objects.add(hruska);

		Item home = new Item(RESOURCE_PREFIX + "home.png", State.MOVE, 17, 44, "Domů", Items.MAP_HOME);
		home.setOnClickWithoutItem(() -> TheGame.instance.loadScene(Scenes.OUTSIDE_HOME));
		this.objects.add(home);
	}
}
