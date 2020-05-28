package nico.time.game.titlescreen;

import org.lwjgl.glfw.GLFW;

import nico.time.engine.gamestates.GameStateManager;
import nico.time.engine.input.Mouse;
import nico.time.engine.input.MousePosition;
import nico.time.engine.rendering.Camera;
import nico.time.engine.rendering.MasterRenderer;
import nico.time.engine.utils.math.Vector3f;
import nico.time.game.GameObject;
import nico.time.game.instructions.Instructions;
import nico.time.game.playing.PlayingState;

public class MainMenu {

	private final String[] options;
	private final GameObject[] menuOptions;
	private final GameObject[] menuSelected;
	
	private int selected;
	
	public MainMenu(String...options) {
		this.options = options;
		this.menuOptions = new GameObject[options.length];
		this.menuSelected = new GameObject[options.length];
		this.selected = 0;
		for(int i = 0; i < options.length; i++) {
			this.menuOptions[i] = new GameObject(options[i], 0.0f, 0.0f - i * 0.5f);
			this.menuSelected[i] = new GameObject(options[i] + "_s", 0.0f, 0.0f - i * 0.5f);
		}
	}
	
	public void updateSelection(Camera camera) {
		Vector3f mouse = MousePosition.getWorldCoords(camera.getPosition());
		for(int i = 0; i < menuOptions.length; i++) {
			if(mouse.y > menuOptions[i].getPosition().subtract(menuOptions[i].getScale().divide(4.0f)).y && mouse.y < menuOptions[i].getPosition().add(menuOptions[i].getScale().divide(4.0f)).y) {
				this.selected = i;
			}
		}
	}
	
	public void detectChoice(GameStateManager gameStateManager) {
		if(Mouse.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_1)) {
			switch(options[selected]) {
			case "menu_start":
				gameStateManager.changeState(new PlayingState(gameStateManager));
				break;
			case "menu_how":
				gameStateManager.changeState(new Instructions(gameStateManager));
				break;
			case "menu_quit":
				gameStateManager.clearStack();
				break;
			}
		}
	}
	
	public void addObjectsToRenderer(MasterRenderer renderer) {
		for(int i = 0; i < menuOptions.length; i++) {
			if(i == selected)
				renderer.objects.add(menuSelected[i]);
			else
				renderer.objects.add(menuOptions[i]);
		}
	}
}
