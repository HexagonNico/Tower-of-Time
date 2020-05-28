package nico.time.engine.utils.math;

public class Vector2f {

	public float x, y;

	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2f add(Vector2f v) {
		return add(v.x, v.y);
	}
	
	public Vector2f add(float vx, float vy) {
		return new Vector2f(x + vx, y + vy);
	}
	
	public Vector2f subtract(Vector2f v) {
		return subtract(v.x, v.y);
	}
	
	public Vector2f subtract(float vx, float vy) {
		return new Vector2f(x - vx, y - vy);
	}
	
	public Vector2f multiply(float m) {
		return multiply(m, m);
	}
	
	public Vector2f multiply(Vector2f v) {
		return multiply(v.x, v.y);
	}
	
	public Vector2f multiply(float mx, float my) {
		return new Vector2f(x * mx, y * my);
	}
	
	public Vector2f divide(float d) {
		return divide(d, d);
	}
	
	public Vector2f divide(Vector2f v) {
		return divide(v.x, v.y);
	}
	
	public Vector2f divide(float dx, float dy) {
		return new Vector2f(x / dx, y / dy);
	}
	
	public Vector2f negative() {
		return new Vector2f(-x, -y);
	}
	
	public Vector2f absoluteValue() {
		return new Vector2f(Math.abs(x), Math.abs(y));
	}
	
	public float dot(Vector2f v) {
		return x * v.x + y * v.y;
	}
	
	public float distance(Vector2f v) {
		return distance(v.x, v.y);
	}
	
	public float distance(float vx, float vy) {
		return (float) Math.sqrt(Math.pow(vx - x, 2) + Math.pow(vy - y, 2));
	}
	
	@Override
	public String toString() {
		return "Vector2f(" + x + ";" + y + ")";
	}
}
