package nico.time.game.playing.projectiles;

import java.util.ArrayList;

import nico.time.engine.rendering.MasterRenderer;
import nico.time.engine.utils.math.Vector2f;
import nico.time.game.playing.enemies.Enemy;
import nico.time.game.playing.enemies.EnemyManager;

public class ProjectilesManager {

	private ArrayList<Projectile> projectiles;
	
	public ProjectilesManager() {
		this.projectiles = new ArrayList<>();
	}
	
	public void moveProjectiles() {
		for(int i = 0; i < projectiles.size(); i++) {
			this.projectiles.get(i).move();
			if(projectiles.get(i).getPosition().distance(0.0f, 0.0f) > 10.0f)
				this.projectiles.remove(i);
		}
	}
	
	public void add(Vector2f defenceTower, Vector2f enemy, int damage) {
		this.projectiles.add(new Projectile(defenceTower.add(0.0f, 0.5f), enemy.subtract(defenceTower).subtract(0.0f, 0.5f).divide(5.0f), damage));
	}
	
	public void checkCollision(EnemyManager enemies) {
		Enemy enemy = null;
		for(int i = 0; i < projectiles.size(); i++) {
			enemy = enemies.getEnemyInRange(projectiles.get(i).getPosition(), 0.25f);
			if(enemy != null) {
				enemy.damage(projectiles.get(i).getDamage());
				this.projectiles.remove(i);
			}
		}
	}
	
	public void addObjectsToRenderer(MasterRenderer renderer) {
		for(Projectile proj : projectiles) {
			renderer.objects.add(proj);
		}
	}
}
