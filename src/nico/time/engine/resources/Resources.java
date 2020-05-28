package nico.time.engine.resources;

import java.io.File;
import java.util.HashMap;

import nico.time.engine.utils.Log;

public class Resources {

	private static final HashMap<String, Texture> TEXTURES = new HashMap<>();
	private static final HashMap<String, Tilemap> TILEMAPS = new HashMap<>();
	
	/**Load and store all needed resources <br>
	 * All resources are loaded on startup <br>
	 * Called in Engine init
	 */
	public static void load() {
		Log.info(Resources.class, "Loading textures...");
		File folder = new File("res/textures");
		for(File file : folder.listFiles()) {
			TEXTURES.put(file.getName(), new Texture(file.getName()));
		}
		Log.info(Resources.class, "Loading tilemaps...");
		folder = new File("res/map");
		for(File file : folder.listFiles()) {
			TILEMAPS.put(file.getName(), new Tilemap(file.getName()));
		}
	}
	
	/**Get one of the loaded textures <br>
	 * Get the texture of the specified name, if the texture isn't found, an error texture will be returned
	 * @param name - Name of the texture file without extension
	 * @return An instance of a Texture
	 */
	public static Texture getTexture(String name) {
		return TEXTURES.get(name+".png");
	}
	
	public static Tilemap getTilemap(String name) {
		return TILEMAPS.get(name+".json");
	}
	
	/**Delete all loaded textures <br>
	 * Called in Engine terminate
	 */
	public static void cleanUp() {
		Log.info(Resources.class, "Deleting textures...");
		for(Texture texture : TEXTURES.values()) {
			texture.delete();
		}
	}
}
