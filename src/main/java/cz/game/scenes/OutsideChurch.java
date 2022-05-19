package cz.game.scenes;

import cz.game.TheGame;
import cz.game.inventory.Items;
import cz.game.objects.Item;
import cz.game.objects.State;

public class OutsideChurch extends Scene {
	private final static String RESOURCE_PREFIX = "/scenes/outside_church/";

	public OutsideChurch() {
		this.setBackgroundImageUrl(TheGame.class.getResource("/images" + RESOURCE_PREFIX + "outside_church.png"));

		Item cementeryEmpty = new Item(RESOURCE_PREFIX + "cementery_empty.png", State.MOVE, 993, 199, "Na hřbitov",
		                               Items.OUTSIDE_CHURCH_CEMENTERY);
		this.objects.add(cementeryEmpty);

		Item wolfWanted = new Item("wolf_wanted.png", State.DISCOVER, 518, 439, "Zčeknout plakát",
		                           Items.OUTSIDE_CHURCH_WANTED_WOLF);
		this.objects.add(wolfWanted);

		Item walkAway = new Item(RESOURCE_PREFIX + "walk_away.png", State.MOVE, 1161, 507, "Jít pryč",
		                               Items.OUTSIDE_CHURCH_BACK);
		walkAway.setOnClickWithoutItem(() -> TheGame.instance.loadScene(Scenes.MAP));
		this.objects.add(walkAway);
	}
}