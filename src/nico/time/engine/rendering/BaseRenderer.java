package nico.time.engine.rendering;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import nico.time.engine.resources.Texture;

public abstract class BaseRenderer<R> {
	
	public static final float FOV = 60.0f;
	public static final float ZNEAR = 0.1f;
	public static final float ZFAR = 100.0f;
	
	/**Perform rendering process for this renderer <br>
	 * Called in master renderer during the rendering process
	 * @param batch - The batch of objects to render
	 * @param camera - Camera is needed in some of the renderers
	 */
	public abstract void render(RenderBatch<R> batch, Camera camera);
	
	/**Bind a model's VAO and enable vertex attrib array 0 containing the vertex's position <br>
	 * Must be called in {@link #render(QuadModel, RenderBatch, Camera)} before rendering the model
	 * @param model - Model cnontaining the VAO to bind
	 */
	protected void bindVertexAttribArray(QuadModel model) {
		GL30.glBindVertexArray(model.getVao());
		GL20.glEnableVertexAttribArray(0);
	}
	
	/**Bind a texture id to tell OpenGL wich texture should be used <br>
	 * Must be called in {@link #render(QuadModel, RenderBatch, Camera)} for every different texture
	 * @param texture - An instance of a texture containing a texture id
	 */
	protected void bindTexture(Texture texture) {
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getId());
	}
	
	/**Render the model <br>
	 * Render all the 4 model's vertices with triangle strip mode,
	 * connecting all vertices to the previous 2
	 */
	protected void drawModel() {
		GL20.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
	}
	
	/**Unbind the model and disable the vertex attrib array <br>
	 * Called at the end of {@link #render(QuadModel, RenderBatch, Camera)} when the model is no longer needed
	 */
	protected void unbindVertexAttribArray() {
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
	
	public abstract void cleanUp();
}
