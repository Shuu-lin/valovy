package cz.game.scenes;

import cz.game.TheGame;
import cz.game.inventory.Items;
import cz.game.inventory.items.PlasticGloves;
import cz.game.objects.Item;
import cz.game.objects.State;

public class OutsideHruska extends Scene {
	private final static String RESOURCE_PREFIX = "/scenes/outside_hruska/";

	public OutsideHruska() {
		this.setBackgroundImageUrl(TheGame.class.getResource("/images" + RESOURCE_PREFIX + "outside_hruska.png"));

		Item trashBin = new Item(RESOURCE_PREFIX + "trash_bin_gloves.png", State.DISCOVER, 512, 373, "Prohledat popelnici",
		                     Items.OUTSIDE_HRUSKA_TRASH_BIN);
		trashBin.setOnClickWithoutItem(() -> {
			TheGame.instance.getInventoryPanel().addInventoryItem(PlasticGloves.create());
			TheGame.instance.getDialogPanel().showMessage("Našel jsi gumové rukavice.");
			trashBin.setIcon(RESOURCE_PREFIX + "trash_bin.png");
			trashBin.setOnClickWithoutItem(() -> {
				TheGame.instance.getDialogPanel().showMessage("Nic zajímavého tam už není. Ale radši klikni ještě jednou.");
				trashBin.setOnClickWithoutItem(() -> TheGame.instance.showMessage("Nic zajímavého tam už není."));
			});
		});
		this.objects.add(trashBin);

		Item dvere = new Item(RESOURCE_PREFIX + "dvere_inside_empty.png", State.MOVE, 802, 354,
				"Do Hrušky", null);
		dvere.setOnClickWithoutItem(() -> TheGame.instance.loadScene(Scenes.INSIDE_HRUSKA_PECIVO));
		this.objects.add(dvere);


		Item seno = new Item(RESOURCE_PREFIX + "xxx.png", State.TALK, 31, 337, "Pokecat s Kubou",
		                     Items.OUTSIDE_HRUSKA_SENO);
		this.objects.add(seno);

		Item kanal = new Item(RESOURCE_PREFIX + "kanal_empty.png", State.DISCOVER, 133, 524, "Kanál",
		                      Items.OUTSIDE_HRUSKA_KANAL);
		kanal.setOnClickWithoutItem(() -> TheGame.instance.loadScene(Scenes.TOILETS));
		this.objects.add(kanal);

		Item walkAway = new Item(RESOURCE_PREFIX + "walk_away.png", State.MOVE, 0, 569, "Jít pryč",
		                         Items.OUTSIDE_HRUSKA_WALK_AWAY);
		walkAway.setOnClickWithoutItem(() -> TheGame.instance.loadScene(Scenes.MAP));
		this.objects.add(walkAway);
	}
}
