package nico.time.game.playing.building;

import nico.time.engine.utils.math.Vector2f;
import nico.time.game.GameObject;
import nico.time.game.playing.enemies.Enemy;
import nico.time.game.playing.enemies.EnemyManager;
import nico.time.game.playing.projectiles.ProjectilesManager;

public class DefenceTower extends GameObject {

	private int shootTime;
	private int shootDelay;
	
	private int damage;
	
	public DefenceTower(String name, Vector2f position, int damage, int delay) {
		super(name, position.x, position.y);
		this.shootTime = 0;
		this.shootDelay = delay;
		this.damage = damage;
		super.zLayer = 3;
	}
	
	public void shoot(EnemyManager enemies, ProjectilesManager projectiles) {
		Enemy enemyInRange = enemies.getEnemyInRange(position, 1.0f);
		if(enemyInRange != null) {
			this.shootTime++;
			if(shootTime == shootDelay) {
				projectiles.add(position, enemyInRange.getPosition(), damage);
				this.shootTime = 0;
			}
		} else {
			this.shootTime = 0;
		}
	}
}
