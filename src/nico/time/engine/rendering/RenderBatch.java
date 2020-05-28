package nico.time.engine.rendering;

import java.util.Collection;
import java.util.Set;

import nico.time.engine.resources.Texture;

public abstract class RenderBatch<R> {

	/**Get all textures in the batch <br>
	 * This way the renderer avoids binding the same texture multiple times
	 * @return A set of textures that can be iterated through
	 */
	public abstract Set<Texture> getTextures();
	
	public abstract Texture getTexture();
	
	/**Get all render instances that use the same texture <br>
	 * This way render instances that used the same texture are all rendered at the same time
	 * @param texture - The texture used obtained from {@link #getTextures()}
	 * @return A collection of render instances
	 */
	public abstract Collection<R> getRenderInstances(Texture texture);
	
	public abstract R getRenderInstance();
	
	/**Add a render instance to the batch <br>
	 * Called in a gamestate to render objects
	 * @param instance - The render instance to add
	 */
	public abstract void add(R instance);
	
	/**Check if this batch is empty <br>
	 * If a batch is empty it should not be used in the rendering process
	 * @return True if the batch is empty
	 */
	public abstract boolean isEmpty();
	
	/**Remove all elements from this batch <br>
	 * Called at the end of rendering process to avoid objects getting added over and over again
	 */
	public abstract void clear();
}
