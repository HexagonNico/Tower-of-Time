package nico.time.engine.input;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class Mouse extends GLFWMouseButtonCallback {

	private static final ArrayList<Integer> BUTTONS = new ArrayList<>();
	
	@Override
	public void invoke(long window, int button, int action, int mods) {
		if(action == GLFW.GLFW_PRESS)
			BUTTONS.add(new Integer(button));
		else if(action == GLFW.GLFW_RELEASE)
			BUTTONS.remove(new Integer(button));
	}

	public static boolean isButtonDown(int button) {
		return BUTTONS.contains(new Integer(button));
	}
}
