package nico.time.game.playing.timer;

import org.lwjgl.glfw.GLFW;

import nico.time.engine.gamestates.GameStateManager;
import nico.time.engine.input.Keyboard;
import nico.time.engine.rendering.MasterRenderer;

public class Timer {

	private long time;
	private int seconds;
	
	private TimerDigit[] digits;
	
	public Timer(int seconds) {
		this.time = System.currentTimeMillis() / 1000;
		this.seconds = seconds;
		this.digits = new TimerDigit[5];
		this.digits[0] = new TimerDigit(-0.5f, 2.25f);
		this.digits[1] = new TimerDigit(-0.25f, 2.25f);
		this.digits[2] = new TimerDigit(0.0f, 2.25f);
		this.digits[3] = new TimerDigit(0.25f, 2.25f);
		this.digits[4] = new TimerDigit(0.5f, 2.25f);
	}
	
	public void update() {
		if(System.currentTimeMillis() / 1000 - time == 1 && seconds > 0) {
			this.seconds--;
			this.time = System.currentTimeMillis() / 1000;
		}
		this.updateDisplay();
	}
	
	public int getTotalSeconds() {
		return seconds;
	}
	
	public int getSeconds() {
		return seconds % 60;
	}
	
	public int getMinutes() {
		return seconds / 60;
	}
	
	public void forceDecrease(int amount) {
		this.seconds -= amount;
		if(seconds < 0)
			this.seconds = 0;
	}
	
	public void increase(int amount) {
		if(seconds > 0)
			this.seconds += amount;
	}
	
	private void updateDisplay() {
		this.digits[0].setDigit(getMinutes() / 10);
		this.digits[1].setDigit(getMinutes() % 10);
		this.digits[2].setDigit(10);
		this.digits[3].setDigit(getSeconds() / 10);
		this.digits[4].setDigit(getSeconds() % 10);
	}
	
	public void addObjectsToRenderer(MasterRenderer renderer) {
		for(TimerDigit digit : digits) {
			renderer.objects.add(digit);
		}
	}
	
	public void detectLoss(GameStateManager gameStateManager) {
		if(seconds == 0) {
			if(Keyboard.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
				gameStateManager.backToPrevious();
			}
		}
	}
}
