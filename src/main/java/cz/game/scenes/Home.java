package cz.game.scenes;

import cz.game.Registry;
import cz.game.TheGame;
import cz.game.inventory.InventoryItem;
import cz.game.inventory.Items;
import cz.game.inventory.items.HomeKey;
import cz.game.inventory.items.Hopgoblinec;
import cz.game.inventory.items.PlasticGloves;
import cz.game.inventory.items.Shit;
import cz.game.objects.Item;
import cz.game.objects.State;
import cz.game.utils.Music;
import cz.game.utils.Sound;

import javax.swing.*;

import static cz.game.inventory.Items.HOME_KEY;
import static cz.game.inventory.Items.HOME_PHONE;
import static cz.game.inventory.Items.SHIT;

public class Home extends Scene {
	private final static String RESOURCE_PREFIX = "/scenes/garage/";

	public Home() {
		this.setBackgroundImageUrl(TheGame.class.getResource("/images" + RESOURCE_PREFIX + "mainWithDoor.png"));

		final Item window = new Item(RESOURCE_PREFIX + "window1.png", State.DISCOVER, 340, 180, "Kouknout z okna", Items.HOME_WINDOW);
		window.setOnClickWithoutItem(() -> {
			if (window.getItemName() == Items.HOME_WINDOW_SHIT) {
				TheGame.instance.loadScene(Scenes.HOME_WINDOW_SHIT);
			} else {
				TheGame.instance.loadScene(Scenes.HOME_WINDOW);
			}
		});
		window.setOnClickWithItem((item -> {
			switch (item.getItemName()) {
				case SHIT:
					TheGame.instance.getAudioPlayer().playSound(Sound.SQUASH);
					try {
						Thread.sleep(500);
					} catch (InterruptedException ignored) {
					}
					TheGame.instance.setSelectedItem(null);
					window.setItemName(Items.HOME_WINDOW_SHIT);
					window.changeTooltip("Vykouknout z okna pomazaného hovnem", true);
					window.setIcon(new ImageIcon(TheGame.class.getResource("/images/" + RESOURCE_PREFIX + "shitStainedWindow.png")));
					item.delete();
					window.setOnClickWithItem(null);
					TheGame.instance.getDialogPanel().showMessage("Pomazal jsi okno hovnem.");
					break;
			}
		}));
		objects.add(window);

		Item shit = new Item("/inventoryItems/shit.png", State.DISCOVER, 220, 360, "Omrknout hovno", SHIT);
		shit.setOnClickWithoutItem(() -> {
			TheGame.instance.getDialogPanel().showMessage("Hovienko, ze včerejška. Sebereš ho?");
			shit.setToolTipText("Vzít hovno");
			shit.setState(State.COLLECT);
			shit.setOnClickWithoutItem(() -> {
				TheGame.instance.getInventoryPanel().addInventoryItem(Shit.create());
				TheGame.instance.getDialogPanel().showMessage("Sebral jsi " + SHIT.label + ".");
				shit.delete();
			});
		});
		objects.add(shit);

		Item carpet = new Item(RESOURCE_PREFIX + "carpet.png", State.DISCOVER, 615, 452, "Prozkoumat koberec",
		                       Items.HOME_CARPET);
		carpet.setOnClickWithoutItem(() -> TheGame.instance.getDialogPanel().showMessage("Koberec ukradený z IKEA. Nějak divně smrdí."));
		this.objects.add(carpet);

		Item emptyDoor = new Item(RESOURCE_PREFIX + "emptyDoor.png", State.MOVE, 1237, 214, "Vyjít ven", Items.HOME_DOOR);
		emptyDoor.setOnClickWithoutItem(() -> {
			TheGame.instance.loadScene(Scenes.OUTSIDE_HOME);
		});
		objects.add(emptyDoor);

		Item emptySprays = new Item(RESOURCE_PREFIX + "emptySprays.png", State.DISCOVER, 750, 235, "Nakradené spreje", Items.HOME_SPRAYS);
		objects.add(emptySprays);

		Item key = new Item("/inventoryItems/home_key.png", State.COLLECT, 1340, 329, "Vzít klíč", HOME_KEY);
		key.setOnClickWithoutItem(() -> {
			TheGame.instance.getInventoryPanel().addInventoryItem(HomeKey.create());
			TheGame.instance.getDialogPanel().showMessage("Sebral jsi " + HOME_KEY.label + ".");
			key.delete();
		});
		objects.add(key);

		final Item phone = new Item(RESOURCE_PREFIX + "emptyPhone.png", State.HANDLE, 605, 258, "Použít telefon", HOME_PHONE);
		objects.add(phone);

		Item paperStand = new Item(RESOURCE_PREFIX + "paper_stand.png", State.DISCOVER, 359, 322,
		                           "Stojan na důležitá lejstra", Items.HOME_PAPER_STAND);
		paperStand.setOnClickWithItem(item -> {
			if (item.getItemName() == SHIT) {
				TheGame.instance.getDialogPanel().showMessage("Jsem sice Valový, ale nebudu si doma vystavovat hovno.");
			}
		});
		paperStand.setOnClickWithoutItem(() -> TheGame.instance.getDialogPanel()
		                                                       .showMessage("Tady ležely moje papíry o nesvéprávnosti, a teď jsou fuč! Kde zmizely?"));
		objects.add(paperStand);

		Item letter = new Item(RESOURCE_PREFIX + "letter.png", State.HANDLE, 324, 524, "Přečíst dopis",
		                       Items.HOME_LETTER);
		letter.setOnClickWithoutItem(() -> {

			if (!Registry.contains(Registry.Vals.HOME_LETTER_DISCOVERED)) {
				paperStand.setOnClickWithoutItem(() -> TheGame.instance.getDialogPanel()
				                                                       .showMessage("Tady ležely moje papíry o nesvéprávnosti, než je Hopgoblin ukradl!"));

				carpet.setOnClickWithoutItem(() -> {
					TheGame.instance.getDialogPanel().showMessage("Dal jsi pryč koberec");
					Item hopgoblinec = new Item("/inventoryItems/hopgoblinec.png", State.COLLECT, 735, 483,
					                            "Sebrat HOPGOBLINEC", Items.HOME_HOPGOBLINEC);
					hopgoblinec.setOnClickWithoutItem(() -> {
						TheGame.instance.getDialogPanel().showMessage("Klasické lejníčko mi nevadí, ale na tohle holýma rukama nesáhnu.");
					});
					hopgoblinec.setOnClickWithItem(item -> {
						if (item.getItemName() == Items.PLASTIC_GLOVES) {
							TheGame.instance.getDialogPanel().showMessage("Sebral jsi hovno od Hopgoblina - HOPGOBLINEC!");
							TheGame.instance.getInventoryPanel().addInventoryItem(Hopgoblinec.create());
							hopgoblinec.delete();
							item.deselect();
						}
					});

					this.objects.add(hopgoblinec);
					TheGame.instance.getGamePanel().add(hopgoblinec, -1);
					carpet.delete();
				});
				carpet.setToolTipText("Dát pryč koberec");
				carpet.setState(State.HANDLE);
				TheGame.instance.getAudioPlayer().playMusic(Music.LETTER_HOME);
			}
			Registry.add(Registry.Vals.HOME_LETTER_DISCOVERED);
			TheGame.instance.loadScene(Scenes.LETTER_HOME);
		});
		this.objects.add(letter);

		TheGame.instance.getInventoryPanel().addInventoryItem(PlasticGloves.create());
	}
}
