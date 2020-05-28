package nico.time.game.playing.map;

import nico.time.engine.utils.math.Vector2f;
import nico.time.game.GameObject;
import nico.time.game.playing.timer.Timer;

public class Tower extends GameObject {

	private int animationTime;
	private int animationDelay;
	
	public Tower() {
		super("hourglass", 2.35f, 0.35f);
		super.scale = new Vector2f(2.0f, 2.0f);
		super.atlasSize = new Vector2f(3, 2);
		this.animationTime = 0;
		this.animationDelay = 10;
		super.zLayer = 3;
	}

	public void updateAnimation() {
		this.animationTime++;
		if(animationTime == animationDelay) {
			super.uv.x++;
			this.animationTime = 0;
		}
	}
	
	public void checkIfEmpty(Timer timer) {
		if(timer.getTotalSeconds() == 0) {
			super.uv.y = 1;
		}
	}
}
