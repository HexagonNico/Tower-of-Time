package nico.time.game.playing;

import nico.time.engine.gamestates.GameState;
import nico.time.engine.gamestates.GameStateManager;
import nico.time.engine.rendering.Camera;
import nico.time.engine.rendering.MasterRenderer;
import nico.time.game.GameObject;
import nico.time.game.playing.building.DefenceManager;
import nico.time.game.playing.enemies.EnemyManager;
import nico.time.game.playing.map.Map;
import nico.time.game.playing.map.Tower;
import nico.time.game.playing.projectiles.ProjectilesManager;
import nico.time.game.playing.timer.Timer;

public class PlayingState extends GameState {

	private Camera camera;
	
	private Map map;
	private Tower tower;
	
	private Timer timer;
	
	private EnemyManager enemies;
	private DefenceManager defences;
	
	private ProjectilesManager projectiles;
	
	private GameObject gameOverMessage;
	private GameObject escMessage;
	
	public PlayingState(GameStateManager stateManager) {
		super(stateManager);
		this.camera = new Camera();
		this.map = new Map("tileset", "playing_map");
		this.tower = new Tower();
		this.timer = new Timer(300);
		this.enemies = new EnemyManager();
		this.defences = new DefenceManager();
		this.projectiles = new ProjectilesManager();
		this.gameOverMessage = new GameObject("game_over", 0.0f, 0.0f);
		this.escMessage = new GameObject("esc", 3.5f, -2.0f);
	}

	@Override
	protected void init() {
		this.camera.setPosition(0.0f, 0.0f, 8.0f);
		this.gameOverMessage.getScale().x = 2.0f;
		this.gameOverMessage.getScale().y = 2.0f;
	}

	@Override
	protected void loop() {
		this.tower.updateAnimation();
		this.timer.update();
		this.tower.checkIfEmpty(timer);
		this.enemies.updateSpawner(timer);
		this.enemies.moveEnemies(map);
		this.enemies.checkEating(timer);
		this.enemies.checkEnemyHealth(timer);
		this.defences.updateIndicator(camera, map, timer);
		this.defences.buildDefence(timer);
		this.projectiles.moveProjectiles();
		this.projectiles.checkCollision(enemies);
		this.defences.defend(enemies, projectiles);
		this.timer.detectLoss(stateManager);
	}

	@Override
	protected void render(MasterRenderer renderer) {
		renderer.objects.add(tower);
		renderer.map.add(map);
		
		this.timer.addObjectsToRenderer(renderer);
		this.enemies.addObjectsToRenderer(renderer);
		this.defences.addObjectsToRenderer(renderer, timer);
		this.projectiles.addObjectsToRenderer(renderer);
		
		renderer.setCamera(camera);
		
		if(timer.getTotalSeconds() == 0) {
			renderer.objects.add(gameOverMessage);
			renderer.objects.add(escMessage);
		}
	}

}
