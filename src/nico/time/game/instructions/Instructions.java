package nico.time.game.instructions;

import org.lwjgl.glfw.GLFW;

import nico.time.engine.gamestates.GameState;
import nico.time.engine.gamestates.GameStateManager;
import nico.time.engine.input.Keyboard;
import nico.time.engine.rendering.Camera;
import nico.time.engine.rendering.MasterRenderer;
import nico.time.game.GameObject;

public class Instructions extends GameState {

	private Camera camera;
	private GameObject instructions;
	
	public Instructions(GameStateManager stateManager) {
		super(stateManager);
		this.camera = new Camera();
		this.instructions = new GameObject("htp", 0.0f, 0.0f);
	}

	@Override
	protected void init() {
		this.camera.setPosition(0.0f, 0.0f, 10.0f);
		this.instructions.setScale(10.0f, 5.0f);
	}

	@Override
	protected void loop() {
		if(Keyboard.isKeyDown(GLFW.GLFW_KEY_ESCAPE))
			stateManager.backToPrevious();
	}

	@Override
	protected void render(MasterRenderer renderer) {
		renderer.setCamera(camera);
		renderer.objects.add(instructions);
	}

}
