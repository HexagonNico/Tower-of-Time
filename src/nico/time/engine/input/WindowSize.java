package nico.time.engine.input;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

public class WindowSize {

	public static int width = 800;
	public static int height = 450;
	
	/**Stores the values of height and width of the given window as static variables <br>
	 * These values will then be accessable anywhere in the code
	 * @param windowId - Id of the window
	 */
	public static void handleResize(long windowId) {
		IntBuffer w = BufferUtils.createIntBuffer(4);
		IntBuffer h = BufferUtils.createIntBuffer(4);
		GLFW.glfwGetWindowSize(windowId, w, h);
		width = w.get(0);
		height = h.get(0);
	}
}
