package nico.time.game.playing.timer;

import nico.time.game.GameObject;

public class TimerDigit extends GameObject {

	public TimerDigit(float x, float y) {
		super("timer", x, y);
		super.scale.x = 0.25f;
		super.scale.y = 0.25f;
		super.atlasSize.x = 11;
		super.zLayer = 2;
	}

	public void setDigit(int digit) {
		super.uv.x = digit;
	}
}
