package nico.time.game.playing.building;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import nico.time.engine.input.Keyboard;
import nico.time.engine.rendering.Camera;
import nico.time.engine.rendering.MasterRenderer;
import nico.time.game.playing.enemies.EnemyManager;
import nico.time.game.playing.map.Map;
import nico.time.game.playing.projectiles.ProjectilesManager;
import nico.time.game.playing.timer.Timer;

public class DefenceManager {

	private final ArrayList<DefenceTower> defences;
	
	private MouseIndicator indicator;
	
	private Dialog dialog;
	
	public DefenceManager() {
		this.defences = new ArrayList<>();
		this.indicator = new MouseIndicator();
		this.dialog = new Dialog();
	}
	
	public void updateIndicator(Camera camera, Map map, Timer timer) {
		if(!Keyboard.isKeyDown(GLFW.GLFW_KEY_W) && timer.getTotalSeconds() > 0)
			this.indicator.listenToMouseMovement(camera.getPosition());
		this.indicator.updateTexture(map, defences);
	}
	
	public void buildDefence(Timer timer) {
		if(indicator.allowsBuilding()) {
			this.dialog.setPosition(indicator.getPosition());
			if(Keyboard.isKeyDown(GLFW.GLFW_KEY_Q) && Keyboard.isKeyDown(GLFW.GLFW_KEY_W)) {
				if(timer.getTotalSeconds() > 120) {
					this.defences.add(new DefenceTower("defence_tower", indicator.getPosition(), 3, 50));
					timer.forceDecrease(120);
				}
			}
			else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_E) && Keyboard.isKeyDown(GLFW.GLFW_KEY_W)) {
				if(timer.getTotalSeconds() > 180) {
					this.defences.add(new DefenceTower("defence_tower_2", indicator.getPosition(), 2, 20));
					timer.forceDecrease(180);
				}
			}
		}
	}
	
	public void defend(EnemyManager enemies, ProjectilesManager projectiles) {
		for(DefenceTower defence : defences) {
			defence.shoot(enemies, projectiles);
		}
	}
	
	public void addObjectsToRenderer(MasterRenderer renderer, Timer timer) {
		for(DefenceTower defence : defences) {
			renderer.objects.add(defence);
		}
		renderer.objects.add(indicator);
		if(Keyboard.isKeyDown(GLFW.GLFW_KEY_W) && indicator.allowsBuilding() && timer.getTotalSeconds() > 0)
			renderer.objects.add(dialog);
	}
}
