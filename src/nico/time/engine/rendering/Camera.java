package nico.time.engine.rendering;

import org.lwjgl.glfw.GLFW;

import nico.time.engine.input.Keyboard;
import nico.time.engine.utils.math.Vector2f;
import nico.time.engine.utils.math.Vector3f;

public class Camera {

	private Vector3f position;

	/**Create a camera
	 * @param position - Initial position of the camera
	 */
	public Camera(Vector3f position) {
		this.position = position;
	}
	
	/**Create a camera at position (0;0;0) <br>
	 * Position can be changed later with {@link Camera#setPosition(float, float, float)}
	 */
	public Camera() {
		this(new Vector3f(0.0f, 0.0f, 0.0f));
	}

	/**Get camera position <br>
	 * Needed to create view matrix
	 * @return A 3D vector with the camera's position
	 */
	public Vector3f getPosition() {
		return position;
	}
	
	/**Change the camera's x and y position leaving the z axis untouched
	 * @param position - New camera position in a 2D space
	 */
	public void setPosition(Vector2f position) {
		this.setPosition(position.x, position.y);
	}
	
	/**Change the camera position
	 * @param position - New camera position in a 3D space
	 */
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	/**Change the camera's x and y position leaving the z axis untouched
	 * @param x - New camera x position
	 * @param y - New camera y position
	 */
	public void setPosition(float x, float y) {
		this.position.x = x;
		this.position.y = y;
	}
	
	/**Change the camera position <br>
	 * @param x - New camera x position
	 * @param y - New camera y position
	 * @param z - New camera z position
	 */
	public void setPosition(float x, float y, float z) {
		this.position.x = x;
		this.position.y = y;
		this.position.z = z;
	}
	
	/**Move the camera in a 3D space <br>
	 * Add a value to the camera's position
	 * @param x - Direction of camera movement on X axis
	 * @param y - Direction of camera movement on Y axis
	 * @param z - Direction of camera movement on Z axis
	 */
	public void move(float x, float y, float z) {
		this.position.x += x;
		this.position.y += y;
		this.position.z += z;
	}

	/**Move the camera in a 2D space <br>
	 * Add a value to the camera's position
	 * @param x - Direction of camera movement on X axis
	 * @param y - Direction of camera movement on Y axis
	 */
	public void move(float x, float y) {
		this.move(x, y, 0);
	}
	
	public void keyboardMovement(float speed) {
		if(Keyboard.isKeyDown(GLFW.GLFW_KEY_UP))
			this.move(0.0f, speed);
		if(Keyboard.isKeyDown(GLFW.GLFW_KEY_DOWN))
			this.move(0.0f, -speed);
		if(Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT))
			this.move(-speed, 0.0f);
		if(Keyboard.isKeyDown(GLFW.GLFW_KEY_RIGHT))
			this.move(speed, 0.0f);
	}
}
