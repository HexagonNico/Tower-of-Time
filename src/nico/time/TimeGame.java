package nico.time;

import nico.time.engine.Engine;
import nico.time.engine.gamestates.GameStateManager;
import nico.time.engine.rendering.MasterRenderer;

public class TimeGame {

	private final Engine engine;
	private final MasterRenderer renderer;
	private final GameStateManager game;
	
	public TimeGame() {
		this.engine = new Engine();
		this.renderer = new MasterRenderer();
		this.game = new GameStateManager();
	}
	
	private void run() {
		while(engine.isRunning() && !game.shouldClose()) {
			this.game.loop();
			this.game.render(renderer);
			this.renderer.render();
			this.engine.update();
		}
		
		this.renderer.cleanUp();
		this.engine.terminate();
	}
	
	public static void main(String[] args) {
		new TimeGame().run();
	}
}
