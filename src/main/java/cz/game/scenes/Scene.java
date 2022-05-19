package cz.game.scenes;

import cz.game.objects.ClickableObject;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Scene {
	protected URL backgroundImageUrl;
	protected List<ClickableObject> objects = new ArrayList<>();
}
