package cz.game.scenes;

import cz.game.Registry;
import cz.game.TheGame;
import cz.game.dialog.Dialog;
import cz.game.dialog.DialogStep;
import cz.game.inventory.InventoryItem;
import cz.game.inventory.Items;
import cz.game.inventory.items.Shit;
import cz.game.objects.Item;
import cz.game.objects.State;

import java.awt.*;

import static cz.game.Registry.Vals.TOLIET_VYDEJ_OPENED;
import static cz.game.inventory.Items.SHIT;

public class Toilets extends Scene {
	private final static String RESOURCE_PREFIX = "/scenes/toilets/";

	private Dialog wolfDialog;

	public Toilets() {
		this.setBackgroundImageUrl(TheGame.class.getResource("/images" + RESOURCE_PREFIX + "toilets.png"));

		Item vydejOkenko = new Item(RESOURCE_PREFIX + "vydej_closed.png", State.DISCOVER, 879, 361, "Omrknout výdejové okénko",
		                            Items.TOILETS_VYDEJOVE_OKNO_ZAVRENE);
		this.objects.add(vydejOkenko);

		Item wolf = new Item(RESOURCE_PREFIX + "wolf_empty.png", State.TALK, 851, 170, "Pokecat s Wolfem", Items.TOILETS_WOLF);
		wolf.setDialogPoint(new Point(908, 215));

		DialogStep wolfDialogSteps = new DialogStep();
		wolfDialogSteps.setAnswer("Čus, co je?");
		DialogStep createShitClosed = new DialogStep();
		createShitClosed.setAnswer("Nejdřív si otevři výdejové okénko");
		createShitClosed.setCondition((game) -> !Registry.contains(TOLIET_VYDEJ_OPENED));
		createShitClosed.setOnClick((dialog -> {
			vydejOkenko.setState(State.HANDLE);
			vydejOkenko.setToolTipText("Otevřít");
			vydejOkenko.setOnClickWithoutItem(() -> {
				this.setBackgroundImageUrl(TheGame.class.getResource("/images" + RESOURCE_PREFIX + "toliets_opened.png"));
				TheGame.instance.loadScene(Scenes.TOILETS);
				Registry.add(TOLIET_VYDEJ_OPENED);
				vydejOkenko.delete();
			});
			dialog.loadDialogOptions(createShitClosed);
		}));
		wolfDialogSteps.getResponses().put("Vyrobíš mi, prosím, hovno? ", createShitClosed);

		DialogStep whatDoYouDo = new DialogStep();
		whatDoYouDo.setAnswer("Jsi debil? Co asi! Schovávám se před xichtovkou, kdyby mě našli, je po mě");
		whatDoYouDo.setOnClick((dialog -> dialog.loadDialogOptions(whatDoYouDo)));

		wolfDialogSteps.getResponses().put("Co tam děláš?", whatDoYouDo);

		DialogStep createShitOpened = new DialogStep();
		createShitOpened.setCondition((game) -> Registry.contains(TOLIET_VYDEJ_OPENED));
		createShitOpened.setAnswer("Jasně Valový, tobě rád naseru");
		createShitOpened.setOnClick((dialog -> {
			Item shit = new Item("/scenes/garage/shit.png", State.COLLECT, 865, 350, "Sebrat hovno", Items.SHIT);
			shit.setVisible(true);
			shit.setOnClickWithoutItem(() -> {
				TheGame.instance.getInventoryPanel().addInventoryItem(Shit.create());
				shit.delete();
			});
			this.getObjects().add(shit);
			TheGame.instance.getGamePanel().add(shit, -1);
			dialog.loadDialogOptions(createShitOpened);
		}));
		wolfDialogSteps.getResponses().put("Vyrobíš mi, prosím, hovno?", createShitOpened);

		wolfDialog = new Dialog(wolf, wolfDialogSteps);
		wolf.setOnClickWithoutItem(() -> wolfDialog.start());
		this.objects.add(wolf);

	}
}