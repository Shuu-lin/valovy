package cz.game;

import java.util.HashSet;
import java.util.Set;

public class Registry {
	public enum Vals {
		TOLIET_VYDEJ_OPENED,
		HOME_LETTER_DISCOVERED,
	}
	static private final Set<Vals> vals = new HashSet<>();

	public static void add(Vals val) {
		vals.add(val);
	}

	public static boolean contains(Vals val) {
		return vals.contains(val);
	}
}