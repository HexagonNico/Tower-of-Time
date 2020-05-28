package nico.time.engine.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

import nico.time.engine.rendering.BaseRenderer;
import nico.time.engine.utils.math.Matrix4f;
import nico.time.engine.utils.math.Vector3f;
import nico.time.engine.utils.math.Vector4f;

public class MousePosition extends GLFWCursorPosCallback {

	public static float xPos;
	public static float yPos;
	
	@Override
	public void invoke(long window, double xpos, double ypos) {
		xPos = (float) (2.0f * xpos / WindowSize.width - 1.0f);
		yPos = (float) -(2.0f * ypos / WindowSize.height - 1.0f);
	}
	
	public static Vector4f getEyeCoords() {
		Matrix4f projectionMatrix = Matrix4f.createProjectionMatrix(BaseRenderer.FOV, BaseRenderer.ZNEAR, BaseRenderer.ZFAR).invert();
		Vector4f clipCoords = new Vector4f(xPos, yPos, -1.0f, 1.0f);
		return projectionMatrix.transform(clipCoords);
	}
	
	public static Vector3f getWorldCoords(Vector3f cameraPosition) {
//		Matrix4f viewMatrix = Matrix4f.createViewMatrix(cameraPosition).invert();
//		return viewMatrix.transform(getEyeCoords());
		return getEyeCoords().multiply(cameraPosition.z).add(cameraPosition);
	}
}
