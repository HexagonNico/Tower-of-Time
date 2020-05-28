package nico.time.engine.gamestates;

import nico.time.engine.rendering.MasterRenderer;
import nico.time.game.titlescreen.TitleScreen;

public class EngineTester extends GameState {
	
	public EngineTester(GameStateManager stateManager) {
		super(stateManager);
	}

	@Override
	protected void init() {
		super.stateManager.clearStack(new TitleScreen(stateManager));
	}

	@Override
	protected void loop() {}

	@Override
	protected void render(MasterRenderer renderer) {}

}
