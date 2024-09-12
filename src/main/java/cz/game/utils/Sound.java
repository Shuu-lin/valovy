package cz.game.utils;

public enum Sound {
	SQUASH("squash.wav"),
	AHOJ_ROMANE("ahoj_romane.wav"),
	DEKUJI_ZA_LEJNO("dekuji_lejnicko.wav"),
	UKLID_KE_KURATUM("uklid_kurata.wav");

	public String fileName;

	Sound(String fileName) {
		this.fileName = fileName;
	}
}
