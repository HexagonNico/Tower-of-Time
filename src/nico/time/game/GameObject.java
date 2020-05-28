package nico.time.game;

import nico.time.engine.resources.Resources;
import nico.time.engine.resources.Texture;
import nico.time.engine.utils.math.Vector2f;
import nico.time.engine.utils.math.Vector3f;

public class GameObject {

	private Texture texture;
	
	protected Vector2f position;
	protected int zLayer;
	
	protected Vector2f scale;
	
	protected Vector2f atlasSize;
	protected Vector2f uv;
	
	public GameObject(String name, float x, float y) {
		this.texture = Resources.getTexture(name);
		this.position = new Vector2f(x, y);
		this.zLayer = 1;
		this.scale = new Vector2f(1.0f, 1.0f);
		this.atlasSize = new Vector2f(1, 1);
		this.uv = new Vector2f(0.0f, 0.0f);
	}
	
	/**Get this object's texture
	 * @return An instance of a texture
	 */
	public Texture getTexture() {
		return texture;
	}
	
	/**Get this object's position <br>
	 * Used to create transformation matrix
	 * @return The object's position in a 2D space
	 */
	public Vector2f getPosition() {
		return position;
	}
	
	public Vector3f get3DPosition() {
		return new Vector3f(position.x, position.y, zLayer * -0.001f);
	}
	
	/**Get this object's scale <br>
	 * Used to create transformation matrix
	 * @return Scale of the object
	 */
	public Vector2f getScale() {
		return scale;
	}
	
	public void setScale(float x, float y) {
		this.scale = new Vector2f(x, y);
	}
	
	public Vector2f getAtlasSize() {
		return atlasSize;
	}
	
	public Vector2f getUV() {
		return uv;
	}
}
