package nico.time.game.playing.building;

import nico.time.engine.utils.math.Vector2f;
import nico.time.game.GameObject;

public class Dialog extends GameObject {

	public Dialog() {
		super("dialog", 0.0f, 0.0f);
		super.scale = new Vector2f(2.0f, 1.0f);
		super.zLayer = 1;
	}

	public void setPosition(Vector2f position) {
		super.position.x = position.x;
		super.position.y = position.y + 0.5f;
	}
}
