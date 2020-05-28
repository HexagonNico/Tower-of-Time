package nico.time.game.titlescreen;

import nico.time.engine.gamestates.GameState;
import nico.time.engine.gamestates.GameStateManager;
import nico.time.engine.rendering.Camera;
import nico.time.engine.rendering.MasterRenderer;
import nico.time.game.GameObject;

public class TitleScreen extends GameState {

	private Camera camera;
	
	private GameObject title;
	private MainMenu mainMenu;
	
	public TitleScreen(GameStateManager stateManager) {
		super(stateManager);
		this.camera = new Camera();
		this.title = new GameObject("title", 0.0f, 1.25f);
		this.mainMenu = new MainMenu("menu_start", "menu_how", "menu_quit");
	}

	@Override
	protected void init() {
		this.camera.setPosition(0.0f, 0.0f, 8.0f);
		this.title.setScale(2.0f, 2.0f);
	}

	@Override
	protected void loop() {
		this.mainMenu.updateSelection(camera);
		this.mainMenu.detectChoice(stateManager);
	}

	@Override
	protected void render(MasterRenderer renderer) {
		renderer.setCamera(camera);
		renderer.objects.add(title);
		this.mainMenu.addObjectsToRenderer(renderer);
	}
}
