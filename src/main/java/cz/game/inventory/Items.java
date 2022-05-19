package cz.game.inventory;

public enum Items {

	// HOME
	HOME_KEY("Klíč z bytu"),
	HOME_WINDOW("Okno"),
	HOME_WINDOW_SHIT("Okno pomazané hovnem"),
	HOME_SPRAYS("Spreje"),
	HOME_DOOR("Dveře"),
	HOME_PHONE("Telefon"),
	SHIT("Hovno"),
	HOME_PAPER_STAND("Stojan na lejstra"),
	HOME_CARPET("Koberec"),
	HOME_LETTER("Tajemný dopis"),
	HOME_HOPGOBLINEC("Hopgoblinec"),

	// LETTER HOME
	LETTER_HOME_BACK("Zpět"),

	// MAP
	MAP_CHURCH("Kostel"),
	MAP_HOME("Domů"),

	// HOME WINDOW
	HOME_WINDOW_BACK("Zpátky"),

	// OUTSIDE HOME
	OUTSIDE_HOME_DOOR("Dveře"),
	OUTSIDE_HOME_FOOD_WINDOW("Hladové okno"),
	OUTSIDE_HOME_BELL_VALOVY("Valový"),
	OUTSIDE_HOME_BELL_NOVOTNY("Novotný"),
	OUTSIDE_HOME_BELL_BANANAS("Bananas"),
	OUTSIDE_HOME_WALK_AWAY("Jít pryč"),

	// OUTSIDE CHURCH
	OUTSIDE_CHURCH_CEMENTERY("Hřbitov"),
	OUTSIDE_CHURCH_BACK("Jít pryč"),
	OUTSIDE_CHURCH_WANTED_WOLF("Plakát hledaného"),

	// TOILETS
	TOILETS_VYDEJOVE_OKNO_ZAVRENE("Výdejové okénko"),
	TOILETS_WOLF("Wolf"),

	// RANDOM FOR NOW TODO
	PLASTIC_GLOVES("Gumové rukavice"),
	TOILET_PAPER("Toaleťák");

	public final String label;

	Items(String label) {
		this.label = label;
	}
}
