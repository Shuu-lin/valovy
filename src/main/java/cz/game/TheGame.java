package cz.game;

import cz.game.inventory.InventoryItem;
import cz.game.panels.DialogPanel;
import cz.game.panels.GamePanel;
import cz.game.panels.InventoryPanel;
import cz.game.scenes.Home;
import cz.game.scenes.HomeWindow;
import cz.game.scenes.HomeWindowShit;
import cz.game.scenes.LetterHome;
import cz.game.scenes.OutsideChurch;
import cz.game.scenes.OutsideHome;
import cz.game.scenes.Scene;
import cz.game.scenes.Scenes;
import cz.game.scenes.Toilets;
import cz.game.utils.AudioPlayer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter(value = AccessLevel.PRIVATE)
public class TheGame extends JFrame {
	final public static int WINDOW_WIDTH = 1400;
	final public static int WINDOW_HEIGHT = 900;
	public static TheGame instance;

	private InventoryPanel inventoryPanel;
	private GamePanel gamePanel;
	private DialogPanel dialogPanel;
	private AudioPlayer audioPlayer;
	private Map<Scenes, Scene> scenes = new HashMap<>();

	public static void main(String[] args) {
		ToolTipManager.sharedInstance().setInitialDelay(0);
		ToolTipManager.sharedInstance().setDismissDelay(999999999);

		TheGame game = new TheGame();
		instance = game;
		game.setTitle("The Valový GAME");
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setResizable(false);
		game.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		game.setLayout(new BorderLayout());
		game.setAudioPlayer(new AudioPlayer());
		var screen = Toolkit.getDefaultToolkit().getScreenSize();
		game.setLocation(new Point( (int) (screen.getWidth() / 2) - (WINDOW_WIDTH / 2), (int) (screen.getHeight() / 2) - (WINDOW_HEIGHT / 2)));

		game.setInventoryPanel(new InventoryPanel(WINDOW_WIDTH, (int) Math.round(WINDOW_HEIGHT * 0.14)));
		game.add(game.getInventoryPanel(), BorderLayout.PAGE_START);

		game.setGamePanel(new GamePanel(WINDOW_WIDTH, (int) Math.round(WINDOW_HEIGHT * 0.76)));
		game.add(game.getGamePanel(), BorderLayout.CENTER);

		game.setDialogPanel(new DialogPanel(WINDOW_WIDTH, (int) Math.round(WINDOW_HEIGHT * 0.10)));
		game.add(game.getDialogPanel(), BorderLayout.PAGE_END);

		game.createScenes();

		game.loadScene(Scenes.MAP   );


		game.setVisible(true);
	}

	public void showMessage(String text) {
		TheGame.instance.getDialogPanel().showMessage(text);
	}

	public void loadScene(Scenes sceneName) {
		Scene scene = this.getScenes().get(sceneName);
		this.getGamePanel().removeAll();
		this.gamePanel.setBackgroundImage(scene.getBackgroundImageUrl());
		scene.getObjects().forEach(item -> {
			this.getGamePanel().add(item, 0);
			item.setVisible(true);
		});
		this.repaintLoadedScene();
		this.getDialogPanel().showMessage("");
		this.gamePanel.setLoadedScene(scene);
	}

	public void repaintLoadedScene() {
		this.gamePanel.paintAll(this.gamePanel.getGraphics());
	}

	public void setSelectedItem(InventoryItem item) {
		getInventoryPanel().setSelectedItem(item);
	}

	public Optional<InventoryItem> getSelectedItem() {
		return getInventoryPanel().getSelectedItem();
	}

	public void createScenes() {
		this.getScenes().put(Scenes.HOME, new Home());
		this.getScenes().put(Scenes.HOME_WINDOW, new HomeWindow());
		this.getScenes().put(Scenes.HOME_WINDOW_SHIT, new HomeWindowShit());
		this.getScenes().put(Scenes.OUTSIDE_HOME, new OutsideHome());
		this.getScenes().put(Scenes.TOILETS, new Toilets());
		this.getScenes().put(Scenes.OUTSIDE_CHURCH, new OutsideChurch());
		this.getScenes().put(Scenes.MAP, new cz.game.scenes.Map());
		this.getScenes().put(Scenes.LETTER_HOME, new LetterHome());
	}
}