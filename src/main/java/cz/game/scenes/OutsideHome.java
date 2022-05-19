package cz.game.scenes;

import cz.game.TheGame;
import cz.game.dialog.Dialog;
import cz.game.dialog.DialogStep;
import cz.game.inventory.Items;
import cz.game.objects.Item;
import cz.game.objects.State;
import cz.game.utils.Sound;

import java.awt.*;

public class OutsideHome extends Scene {
	private final static String RESOURCE_PREFIX = "/scenes/outside_home/";

	public OutsideHome() {
		this.setBackgroundImageUrl(TheGame.class.getResource("/images" + RESOURCE_PREFIX + "outside_home.png"));

		final Item door = new Item(RESOURCE_PREFIX + "door_empty.png", State.MOVE, 725, 294, "Jít domů",
		                           Items.OUTSIDE_HOME_DOOR);
		door.setOnClickWithoutItem(() -> {
			TheGame.instance.loadScene(Scenes.HOME);
		});
		door.setOnClickWithItem((x) -> {});
		this.objects.add(door);


		final Item bellValovy = new Item(RESOURCE_PREFIX + "bell_empty.png", State.HANDLE, 846, 343, "Zazvonit na 'Valový'",
		                                 Items.OUTSIDE_HOME_BELL_VALOVY);
		bellValovy.setOnClickWithoutItem(() -> TheGame.instance.getDialogPanel().showMessage("Nikdo není doma, jak je to možné?"));
		this.objects.add(bellValovy);
		final Item bellNovotny = new Item(RESOURCE_PREFIX + "bell_empty.png", State.HANDLE, 846, 385, "Zazvonit na 'Novotný'",
		                                 Items.OUTSIDE_HOME_BELL_NOVOTNY);
		this.objects.add(bellNovotny);
		final Item bellBananas = new Item(RESOURCE_PREFIX + "bell_empty.png", State.HANDLE, 846, 364, "Zazvonit na 'Bananas'",
		                                 Items.OUTSIDE_HOME_BELL_BANANAS);
		bellBananas.setDialogPoint(new Point(870, 372));
		DialogStep bananasDialog = new DialogStep();
		bananasDialog.setAnswer("Ano? asdasdasdasdasdasdassddasda");
		bananasDialog.getResponses().put("Dobrý den, je Bananas doma?", null);
		bellBananas.setOnClickWithoutItem(() -> {
			new Dialog(bellBananas, bananasDialog);
		});
		this.objects.add(bellBananas);


		final Item foodWindow = new Item(RESOURCE_PREFIX + "food_window_empty.png", State.TALK, 273, 320, "Pokecat s bábou",
		                                 Items.OUTSIDE_HOME_FOOD_WINDOW);
		foodWindow.setDialogPoint(new Point(362, 387));

		DialogStep foodWindowStep = new DialogStep();
		Dialog foodWindowDialog = new Dialog(foodWindow, foodWindowStep);
		foodWindowStep.setAnswer("Ahoj, Romane!");
		foodWindowStep.setSpeech(Sound.AHOJ_ROMANE);
		foodWindowStep.getResponses().put("Dobrý den", null);
		foodWindowStep.getResponses().put("Na co čumíš bábo", null);
		DialogStep shitStep = new DialogStep();
		shitStep.setAnswer("Ďěkuji, to je ale krásné lejníčko.");
		shitStep.setSpeech(Sound.DEKUJI_ZA_LEJNO);
		shitStep.setCondition(theGame -> theGame.getInventoryPanel().hasItem(Items.SHIT));
		shitStep.setOnClick((dialog) ->  {
			dialog.loadDialogOptions(shitStep);
			TheGame.instance.getInventoryPanel().deleteItem(Items.SHIT);
		});
		foodWindowStep.getResponses().put("[ Dát hovno ]", shitStep);

		foodWindow.setOnClickWithoutItem(foodWindowDialog::start);
		this.objects.add(foodWindow);

		Item walkAway = new Item(RESOURCE_PREFIX + "walk_away.png", State.MOVE, 1175, 448, "Jít pryč",
		                         Items.OUTSIDE_HOME_WALK_AWAY);
		walkAway.setOnClickWithoutItem(() -> TheGame.instance.loadScene(Scenes.MAP));
		this.objects.add(walkAway);
	}
}
