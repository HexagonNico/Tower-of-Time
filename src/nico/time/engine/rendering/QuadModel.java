package nico.time.engine.rendering;

import java.nio.FloatBuffer;
import java.util.Stack;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import nico.time.engine.utils.BufferConverter;
import nico.time.engine.utils.Log;

public class QuadModel {

	private int vao;
	private final Stack<Integer> vbos = new Stack<>();
	
	/**Create a quad model <br>
	 * Will load the model's data into a VAO
	 * @param x - Position x of top left corner
	 * @param y - Position y of top left corner
	 * @param w - Model's width size
	 * @param h - Model's height size
	 */
	public QuadModel(float x, float y, float w, float h) {
		this.createVAO();
		this.storeDataInAttributeList(new float[] {x,y, x,y-h, x+w,y, x+w,y-h}, 2, 0);
		this.unbindVAO();
	}
	
	/**Get VAO id needed in rendering process
	 * @return VAO id of this model
	 */
	public int getVao() {
		return vao;
	}
	
	/**Create and activate a VAO <br>
	 * Store the VAO id in this object
	 */
	private void createVAO() {
		this.vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);
		Log.info(getClass(), "Created model with VAO " + vao);
	}

	/**Store data into a VBO <br>
	 * Create a VBO, create a float buffer, store the buffer into the VBO <br>
	 * The VBO is then put into the VAO and unbinded
	 * @param data - The data to store into the VBO
	 * @param coordinateSize - 2 for 2D coordinates, 3 for 3D coordinates
	 * @param list - Number of attribute list
	 */
	private void storeDataInAttributeList(float[] data, int coordinateSize, int list) {
		this.vbos.add(GL15.glGenBuffers());
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbos.peek());
		FloatBuffer buffer = BufferConverter.toFloat(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(list, coordinateSize, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}

	/**Unbind the current VAO <br>
	 * Called after creating the model
	 */
	private void unbindVAO() {
		GL30.glBindVertexArray(0);
	}
	
	/**When the game is closed this method deletes all the VAOs and VBOs <br>
	 * Called when a renderer is being cleaned up
	 */
	public void cleanUp() {
		GL30.glDeleteVertexArrays(vao);
		for(int vbo : vbos) {
			GL15.glDeleteBuffers(vbo);
		}
		Log.info(getClass(), "Deleted VAO " + vao);
	}
}
