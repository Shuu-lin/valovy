package cz.game.utils;

public enum Music {
	HUDBA("hudba.mp3"),
	LETTER_HOME("letter_home.mp3");

	public String fileName;

	Music(String fileName) {
		this.fileName = fileName;
	}
}
