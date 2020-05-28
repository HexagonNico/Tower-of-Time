package nico.time.engine.utils.math;

public class Vector3f extends Vector2f {

	public float z;

	public Vector3f(float x, float y, float z) {
		super(x, y);
		this.z = z;
	}
	
	public Vector3f add(Vector3f v) {
		return new Vector3f(x + v.x, y + v.y, z + v.z);
	}
	
	public Vector3f add(float vx, float vy, float vz) {
		return new Vector3f(x + vx, y + vy, z + vz);
	}
	
	public Vector3f subtract(Vector3f v) {
		return new Vector3f(x - v.x, y - v.y, z - v.z);
	}
	
	public Vector3f subtract(float vx, float vy, float vz) {
		return new Vector3f(x - vx, y - vy, z - vz);
	}
	
	public Vector3f multiply(float m) {
		return new Vector3f(x * m, y * m, z * m);
	}
	
	public Vector3f multiply(Vector3f v) {
		return multiply(v.x, v.y, v.z);
	}
	
	private Vector3f multiply(float mx, float my, float mz) {
		return new Vector3f(x * mx, y * my, z * mz);
	}

	public Vector3f divide(float d) {
		return new Vector3f(x / d, y / d, z / d);
	}
	
	public Vector3f divide(Vector3f v) {
		return divide(v.x, v.y, v.z);
	}
	
	public Vector3f divide(float dx, float dy, float dz) {
		return new Vector3f(x / dx, y / dy, z / dz);
	}
	
	public Vector3f negative() {
		return new Vector3f(-x, -y, -z);
	}
	
	public Vector3f absoluteValue() {
		return new Vector3f(Math.abs(x), Math.abs(y), Math.abs(z));
	}
	
	public float dot(Vector3f v) {
		return x * v.x + y * v.y + z * v.z;
	}
	
	@Override
	public String toString() {
		return "Vector3f(" + x + ";" + y + ";" + z + ")";
	}
}
