package cz.game.utils;

public enum Sound {
	SQUASH("squash.wav"),
	AHOJ_ROMANE("ahoj_romane.wav"),
	DEKUJI_ZA_LEJNO("dekuji_lejnicko.wav");

	public String fileName;

	Sound(String fileName) {
		this.fileName = fileName;
	}
}
