package nico.time.engine.utils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class BufferConverter {

	/**Convert a float array into a float buffer <br>
	 * Data needs to be stored in a VBO as a buffer <br>
	 * Create a float buffer, put the data into it and prepare it to be read from
	 * @param data - The float array that needs to be converted
	 * @return A buffer that can be stored into the VBO
	 */
	public static FloatBuffer toFloat(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

	/**Convert an int array into an int buffer <br>
	 * Data needs to be stored in a VBO as a buffer <br>
	 * Create an int buffer, put the data into it and prepare it to be read from
	 * @param data - The float array that needs to be converted
	 * @return A buffer that can be stored into the VBO
	 */
	public static IntBuffer toInt(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
}
