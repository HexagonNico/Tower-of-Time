package nico.time.engine.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import de.matthiasmann.twl.utils.PNGDecoder;
import nico.time.engine.utils.Log;

public class Texture {

	private int textureId;
	
	protected Texture(String fileName) {
		try {
			this.loadTexture("res/textures/"+fileName);
		} catch(IOException e) {
			Log.error(getClass(), "Couldn't load texture " + fileName);
		}
	}
	
	/**Get texture id <br>
	 * A texture is stored as an iteger value
	 * @return The texture id
	 */
	public int getId() {
		return textureId;
	}
	
	/**Delete this texture <br>
	 * Textures need to be deleted when closing the game
	 */
	protected void delete() {
		GL15.glDeleteTextures(textureId);
	}
	
	private void loadTexture(String filePath) throws IOException {
		//Load file
		PNGDecoder decoder = new PNGDecoder(new FileInputStream(filePath));
		
		//Create a byte buffer big enough to store RGBA values
		ByteBuffer buffer = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
		
		//Decode the texture
		decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
		buffer.flip();
		
		//Create and bind texture
		this.textureId = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);
		GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

		//Set the texture parameters, can be GL_LINEAR or GL_NEAREST
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		
		//Upload texture
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
	}
}
