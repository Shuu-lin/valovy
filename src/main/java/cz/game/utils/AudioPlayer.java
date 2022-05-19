package cz.game.utils;

import cz.game.TheGame;
import javazoom.jl.player.JavaSoundAudioDevice;
import javazoom.jl.player.Player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;
import java.lang.reflect.Field;

public class AudioPlayer {
	private static Player musicPlayer;
	private Music nowPlaying;

	public synchronized void playSound(Sound sound) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(TheGame.class.getResource("/sounds/" + sound.fileName));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ignored) {
		}
	}

	public synchronized void playMusic(Music music) {
		try {
			new Thread(() -> {
				try {
					musicPlayer = new Player(TheGame.class.getResourceAsStream("/music/" + music.fileName));
					nowPlaying = music;
					musicPlayer.play();
				} catch (Exception ignored) {
				}
			}).start();

		} catch (Exception ignored) {
		}
	}

	public synchronized void stopMusic() {
		musicPlayer.close();
	}

	public synchronized void stopMusic(Music music) {
		if (this.nowPlaying == music) {
			musicPlayer.close();
		}
	}
}