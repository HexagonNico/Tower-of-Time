package nico.time.engine.input;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Keyboard extends GLFWKeyCallback {
	
	private static final ArrayList<Integer> KEYS = new ArrayList<>();
	
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		if(action == GLFW.GLFW_PRESS)
			KEYS.add(new Integer(key));
		else if(action == GLFW.GLFW_RELEASE)
			KEYS.remove(new Integer(key));
	}

	/**Check if a key is currently being held down
	 * @param key - The key to check
	 * @return True if the key is being held down, false if not
	 */
	public static boolean isKeyDown(int key) {
		return KEYS.contains(new Integer(key));
	}
}
