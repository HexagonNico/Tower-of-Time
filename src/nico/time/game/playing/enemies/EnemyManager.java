package nico.time.game.playing.enemies;

import java.util.ArrayList;
import java.util.Random;

import nico.time.engine.rendering.MasterRenderer;
import nico.time.engine.utils.math.Vector2f;
import nico.time.game.playing.map.Map;
import nico.time.game.playing.timer.Timer;

public class EnemyManager {

	private final Random random;
	private final ArrayList<Enemy> enemies;
	
	private int spawnTime;
	private int spawnDelay;
	
	private int randomDelayMin;
	private int randomDelayMax;
	
	private int randomSpeedMin;
	private int randomSpeedMax;
	
	private int randomHealthMin;
	private int randomHealthMax;
	
	public EnemyManager() {
		this.random = new Random();
		this.enemies = new ArrayList<>();
		this.spawnTime = 0;
		this.spawnDelay = 20;
		this.randomDelayMin = 200;
		this.randomDelayMax = 500;
		this.randomSpeedMin = 7;
		this.randomSpeedMax = 12;
		this.randomHealthMin = 7;
		this.randomHealthMax = 12;
	}
	
	public void updateSpawner(Timer timer) {
		if(timer.getTotalSeconds() > 0) {
			this.spawnTime++;
			if(spawnTime == spawnDelay) {
				this.enemies.add(createRandomEnemy());
				this.spawnDelay = random.nextInt(randomDelayMax - randomDelayMin + 1) + randomDelayMin;
				this.spawnTime = 0;
			}
		}
	}
	
	private Enemy createRandomEnemy() {
		Enemy enemy = null;
		float health = (float) random.nextInt(randomHealthMax - randomHealthMin + 1) + randomHealthMin;
		float speed = (random.nextInt(randomSpeedMax - randomSpeedMin + 1) + randomSpeedMin) / 1000.0f;
		
		switch(random.nextInt(5)) {
		case 0:
		case 1:
		case 2:
			enemy = new Enemy("shadow", -3.0f, 0.5f);
			enemy.setEnemyValues(health, speed);
			break;
		case 3:
			enemy = new Enemy("shadow_red", -3.0f, 0.5f);
			enemy.setEnemyValues(health, speed * (12.0f / 10.0f));
			break;
		case 4:
			enemy = new Enemy("shadow_blue", -3.0f, 0.5f);
			enemy.setEnemyValues(health * (12.0f / 10.0f), speed);
			break;
		}
		
		return enemy;
	}
	
	public void moveEnemies(Map map) {
		for(Enemy enemy : enemies) {
			enemy.move();
			enemy.changeDirection(map.getTileAt(enemy.getPosition()));
		}
	}
	
	public void checkEnemyHealth(Timer timer) {
		for(int i = 0; i < enemies.size(); i++) {
			if(enemies.get(i).getHealth() <= 0) {
				timer.increase(enemies.get(i).getTimeIncome());
				this.enemies.remove(i);
				if(randomDelayMax > 50)
					this.randomDelayMin -= 5;
				if(randomDelayMin > 20)
					this.randomDelayMax -= 5;
				if(random.nextBoolean()) {
					this.randomSpeedMin++;
					this.randomSpeedMax++;
				}
				this.randomHealthMin++;
				this.randomHealthMax++;
			}
		}
	}
	
	public void checkEating(Timer timer) {
		for(Enemy enemy : enemies) {
			if(enemy.isEatingTime())
				timer.forceDecrease(5);
		}
	}
	
	public Enemy getEnemyInRange(Vector2f center, float range) {
		for(Enemy enemy : enemies) {
			if(enemy.getPosition().distance(center) <= range)
				return enemy;
		}
		return null;
	}
	
	public void addObjectsToRenderer(MasterRenderer renderer) {
		for(Enemy enemy : enemies) {
			renderer.objects.add(enemy);
		}
	}
}
