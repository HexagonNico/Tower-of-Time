package nico.time.game.playing.projectiles;

import nico.time.engine.utils.math.Vector2f;
import nico.time.game.GameObject;

public class Projectile extends GameObject {

	private Vector2f motion;
	private int damage;
	
	public Projectile(Vector2f position, Vector2f motion, int damage) {
		super("projectile", position.x, position.y);
		this.motion = motion;
		this.damage = damage;
		super.zLayer = 2;
	}

	public void move() {
		super.position = super.position.add(motion);
	}
	
	public int getDamage() {
		return damage;
	}
}
