package nico.time.engine;

import java.nio.IntBuffer;

import org.lwjgl.Version;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import nico.time.engine.input.Keyboard;
import nico.time.engine.input.Mouse;
import nico.time.engine.input.MousePosition;
import nico.time.engine.input.WindowSize;
import nico.time.engine.resources.Resources;
import nico.time.engine.utils.Log;

public class Engine {
	
	private long window;
	
	public Engine() {
		Log.info(getClass(), "Hello LWJGL " + Version.getVersion() + "! Initializing engine...");
		
		//Setup an error callback.
		//The default implementation will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();
		
		//Initialize GLFW. Most GLFW functions will not work before doing this.
		if(!GLFW.glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");
		
		//Configure GLFW
		GLFW.glfwDefaultWindowHints(); //Optional, the current window hints are already the default
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE); //The window will stay hidden after creation
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE); //The window will be resizable
		
		//Create the window
		this.window = GLFW.glfwCreateWindow(WindowSize.width, WindowSize.height, "A game about TIME", MemoryUtil.NULL, MemoryUtil.NULL);
		if(window == MemoryUtil.NULL) throw new RuntimeException("Failed to create the GLFW window");
		
		//Setup key callback
		GLFW.glfwSetKeyCallback(window, new Keyboard());
		GLFW.glfwSetCursorPosCallback(window, new MousePosition());
		GLFW.glfwSetMouseButtonCallback(window, new Mouse());
		
		//Get the thread stack and push a new frame
		try (MemoryStack stack = MemoryStack.stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);
			
			//Get the window size passed to glfwCreateWindow
			GLFW.glfwGetWindowSize(window, pWidth, pHeight);
			
			//Get the resolution of the primary monitor
			GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
			
			//Center the window
			GLFW.glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
		} //The stack frame is popped automatically
		
		//Make the OpenGL context current
		GLFW.glfwMakeContextCurrent(window);
		
		//Enable v-sync
		GLFW.glfwSwapInterval(1);
		
		//Make the window visible
		GLFW.glfwShowWindow(window);

		//This line is critical for LWJGL's interoperation with GLFW's
		//OpenGL context, or any context that is managed externally.
		//LWJGL detects the context that is current in the current thread,
		//creates the GLCapabilities instance and makes the OpenGL
		//bindings available for use.
		GL.createCapabilities();
		
		//Load resources
		Resources.load();
	}
	
	public boolean isRunning() {
		//Run the rendering loop until the user has attempted to close the window
		return !GLFW.glfwWindowShouldClose(window);
	}
	
	public void update() {
		//Swap the color buffers
		GLFW.glfwSwapBuffers(window);
		
		//Poll for window events
		GLFW.glfwPollEvents();

		//Handle window resize
		WindowSize.handleResize(window);
//		WindowManager.sync(60);
	}
	
	public void terminate() {
		//Delete used textures
		Resources.cleanUp();
		
		//Free the window callbacks and destroy the window
		Callbacks.glfwFreeCallbacks(window);
		GLFW.glfwDestroyWindow(window);
		
		//Terminate GLFW and free the error callback
		GLFW.glfwTerminate();
		GLFW.glfwSetErrorCallback(null).free();
		
		Log.info(getClass(), "Terminated");
	}
}
