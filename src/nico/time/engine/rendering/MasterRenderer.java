package nico.time.engine.rendering;

import org.lwjgl.opengl.GL11;

import nico.time.engine.input.WindowSize;
import nico.time.engine.rendering.map.MapBatch;
import nico.time.engine.rendering.map.MapRenderer;
import nico.time.engine.rendering.objects.ObjectBatch;
import nico.time.engine.rendering.objects.ObjectRenderer;
import nico.time.engine.utils.Log;

public class MasterRenderer {
	
	private final ObjectRenderer objectRenderer;
	public final ObjectBatch objects;
	
	private final MapRenderer mapRenderer;
	public final MapBatch map;
	
	private Camera camera;
	
	/**Create master renderer <br>
	 * Initialize the base model and all the renderers
	 */
	public MasterRenderer() {
		Log.info(getClass(), "Creating renderers...");
		this.objectRenderer = new ObjectRenderer();
		this.objects = new ObjectBatch();
		this.mapRenderer = new MapRenderer();
		this.map = new MapBatch();
	}
	
	/**Perform rendering process <br>
	 * Render all objects that were added to the batches in this frame <br>
	 * The batches are cleared and reused every frame
	 */
	public void render() {
		this.prepare();
		
		if(!map.isEmpty())
			this.mapRenderer.render(map, camera);
		
		if(!objects.isEmpty())
			this.objectRenderer.render(objects, camera);
		
		this.objects.clear();
		this.map.clear();
	}
	
	/**Prepare the renderer <br>
	 * Called at the beginning of the rendering process
	 */
	private void prepare() {
		GL11.glViewport(0, 0, WindowSize.width, WindowSize.height);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClearColor(0.0f, 0.1f, 0.3f, 0.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);		
	}
	
	/**Set camera for rendering <br>
	 * The camera is then used in the renderers that need it
	 * @param camera - An instance of a camera
	 */
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	
	/**Clean up the base model <br>
	 * Called in main class after main loop
	 */
	public void cleanUp() {
		Log.info(getClass(), "Cleaning up renderers...");
		this.objectRenderer.cleanUp();
		this.mapRenderer.cleanUp();
	}
}
