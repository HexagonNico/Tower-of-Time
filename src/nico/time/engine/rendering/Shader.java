package nico.time.engine.rendering;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import nico.time.engine.utils.Log;
import nico.time.engine.utils.math.Matrix4f;
import nico.time.engine.utils.math.Vector2f;
import nico.time.engine.utils.math.Vector3f;

public abstract class Shader {
	
	private int programID;
	private int vertexID;
	private int fragmentID;
	
	/**Create shader program
	 * @param vertexFile
	 * @param fragmentFile
	 */
	protected Shader(String vertexFile, String fragmentFile) {
		vertexID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
		fragmentID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
		programID = GL20.glCreateProgram();
		GL20.glAttachShader(programID, vertexID);
		GL20.glAttachShader(programID, fragmentID);
		bindAttributes();
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
		getAllUniformLocations();
	}
	
	/**Load 'in' variables into the shader <br>
	 * Bind an attribute list to a variable in the shader
	 */
	protected abstract void bindAttributes();
	
	/**Get all uniform variables locations <br>
	 * Uniform variables will be loaded after this call
	 */
	protected abstract void getAllUniformLocations();
	
	/**Load the "in" variables in the shader
	 * @param attribute - Number of attribute list (VBO)
	 * @param variableName - Name of the variable in the shader program
	 */
	protected void bindAttribute(int attribute, String variableName) {
		GL20.glBindAttribLocation(programID, attribute, variableName);
	}
	
	/**Start program <br>
	 * Needs to be called before rendering anything
	 */
	public void start() {
		GL20.glUseProgram(programID);
	}
	
	/**Stop program<br>
	 * Called in main game loop after rendering everything
	 */
	public void stop() {
		GL20.glUseProgram(0);
	}
	
	/**Manage memory <br>
	 * Called after main game loop
	 */
	public void cleanUp() {
		stop();
		GL20.glDetachShader(programID, vertexID);
		GL20.glDetachShader(programID, fragmentID);
		GL20.glDeleteShader(fragmentID);
		GL20.glDeleteShader(vertexID);
		GL20.glDeleteProgram(programID);
	}
	
	/**Get the uniform variable location at any time
	 * @param unformName - Name of the uniform variable
	 * @return An int that represents the location of that uniform variable
	 */
	protected int getUniformLocation(String uniformName) {
		return GL20.glGetUniformLocation(programID, uniformName);
	}
	
	/**Get the uniform array location at any time
	 * @param unformName - Name of the uniform array without the []
	 * @param length - Length of the array
	 * @return An int that represents the location of that uniform array
	 */
	protected int[] getUniformArrayLocation(String uniformName, int length) {
		int[] array = new int[length];
		for(int i=0;i<length;i++) {
			array[i] = this.getUniformLocation(uniformName + "[" + i + "]");
		}
		return array;
	}
	
	/**Load a float uniform variable*/
	protected void loadFloat(int location, float value) {
		GL20.glUniform1f(location, value);
	}
	
	/**Load a vector uniform variable*/
	protected void loadVector(int location, Vector3f value) {
		GL20.glUniform3f(location, value.x, value.y, value.z);
	}
	
	/**Load a vector uniform variable*/
	protected void loadVector(int location, Vector2f value) {
		GL20.glUniform2f(location, value.x, value.y);
	}
	
	/**Load a matrix uniform variable*/
	protected void loadMatrix(int location, Matrix4f matrix) {
		FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
		matrix.store(matrixBuffer);
		matrixBuffer.flip();
		GL20.glUniformMatrix4fv(location, false, matrixBuffer);
	}
	
	/**Load a boolean uniform variable*/
	protected void loadBoolean(int location, boolean value) {
		GL20.glUniform1i(location, value ? 1 : 0);
	}
	
	protected void loadInt(int location, int value) {
		GL20.glUniform1i(location, value);
	}
	
	/**A method that can load up shader programs source code files
	 * @param file - The name of the file
	 * @param type - Whenever it's a vertex or a fragment shader
	 */
	private static int loadShader(String file, int type) {
		StringBuilder shaderSource = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("res/shaders/"+file));
			String line;
			while((line = reader.readLine()) != null) {
				shaderSource.append(line).append("\n");
			}
			reader.close();
		}catch(IOException e) {
			Log.error(Shader.class, "Could not read shader file res/shaders/" + file);
			System.exit(-1);
		}
		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, shaderSource);
		GL20.glCompileShader(shaderID);
		if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			Log.error(Shader.class, "Could not complie shader " + file);
			Log.info(Shader.class, "Shader compilation info\n"+GL20.glGetShaderInfoLog(shaderID, 500));
			System.exit(-1);
		}
		Log.info(Shader.class, "Loaded shader " + file + " with id " + shaderID);
		return shaderID;
	}
}