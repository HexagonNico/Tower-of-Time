package nico.time.game.playing.enemies;

import nico.time.engine.utils.Direction;
import nico.time.engine.utils.math.Vector2f;
import nico.time.game.GameObject;

public class Enemy extends GameObject {

	private Direction moving;
	private int animationTime;
	private int animationDelay;
	
	private boolean stop;
	
	private float maxHealth;
	private float health;
	private float speed;
	
	public Enemy(String name, float x, float y) {
		super(name, x, y);
		this.moving = Direction.RIGHT;
		super.atlasSize = new Vector2f(2, 4);
		this.animationTime = 0;
		this.animationDelay = 30;
		this.stop = false;
		this.maxHealth = 10;
		this.health = 10;
		this.speed = 0.01f;
		super.zLayer = 2;
	}

	public void move() {
		this.updateAnimation();
		
		if(moving == Direction.UP && position.y > 0.0f) {
			this.moving = Direction.RIGHT;
			this.stop = true;
		}
		
		if(!stop) {
			super.position.x += this.speed * this.moving.x;
			super.position.y += this.speed * this.moving.y;
		}
	}
	
	private void updateAnimation() {
		switch(moving) {
		case UP:
			super.uv.y = 3;
			break;
		case DOWN:
			super.uv.y = 0;
			break;
		case LEFT:
			super.uv.y = 2;
			break;
		case RIGHT:
			super.uv.y = 1;
			break;
		}
		
		this.animationTime++;
		if(animationTime == animationDelay) {
			super.uv.x = 1 - super.uv.x;
			this.animationTime = 0;
		}
	}
	
	public boolean isEatingTime() {
		return stop && animationTime == animationDelay - 1;
	}
	
	public void changeDirection(int tile) {
		if(tile == 3) this.moving = Direction.DOWN;
		else if(tile == 6) this.moving = Direction.RIGHT;
		else if(tile == 7) this.moving = Direction.UP;
	}
	
	public void damage(int damage) {
		this.health -= damage;
	}
	
	public float getHealth() {
		return health;
	}
	
	public void setEnemyValues(float health, float speed) {
		this.maxHealth = health;
		this.health = health;
		this.speed = speed;
		this.animationDelay = (int) (0.3f / speed);
	}
	
	public int getTimeIncome() {
		return (int) (maxHealth * (speed * 100));
	}
}
