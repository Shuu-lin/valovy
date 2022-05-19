package cz.game.scenes;

import cz.game.TheGame;
import cz.game.inventory.Items;
import cz.game.objects.Item;
import cz.game.objects.State;
import cz.game.utils.Music;

public class LetterHome extends Scene {
	private final static String RESOURCE_PREFIX = "/scenes/letter_home/";

	public LetterHome() {
		this.setBackgroundImageUrl(TheGame.class.getResource("/images" + RESOURCE_PREFIX + "letter.png"));

		Item walkBack = new Item(RESOURCE_PREFIX + "walk_away.png", State.MOVE, 29, 364, "Zpět", Items.LETTER_HOME_BACK);
		walkBack.setOnClickWithoutItem(() -> {
			TheGame.instance.loadScene(Scenes.HOME);
			TheGame.instance.getAudioPlayer().stopMusic(Music.LETTER_HOME);
		});
		this.objects.add(walkBack);
	}
}