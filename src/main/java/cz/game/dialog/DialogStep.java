package cz.game.dialog;

import cz.game.TheGame;
import cz.game.utils.Sound;
import lombok.Data;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Data
public class DialogStep {
	private String answer;
	private Map<String, DialogStep> responses = new TreeMap<>();
	private Predicate<TheGame> condition;
	private Consumer<Dialog> onClick;
	private Sound speech;
}