package nico.time.engine.rendering.objects;

import nico.time.engine.rendering.Shader;
import nico.time.engine.utils.math.Matrix4f;
import nico.time.engine.utils.math.Vector2f;

public class ObjectShader extends Shader {

	private int transformation_matrix;
	private int projection_matrix;
	private int view_matrix;
	
	private int atlas_size;
	private int uv;
	
	protected ObjectShader() {
		super("object_shader.vert", "object_shader.frag");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}

	@Override
	protected void getAllUniformLocations() {
		this.transformation_matrix = super.getUniformLocation("transformation_matrix");
		this.projection_matrix = super.getUniformLocation("projection_matrix");
		this.view_matrix = super.getUniformLocation("view_matrix");

		this.atlas_size = super.getUniformLocation("atlas_size");
		this.uv = super.getUniformLocation("uv");
	}
	
	protected void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(transformation_matrix, matrix);
	}
	
	protected void loadProjectionMatrix(Matrix4f matrix) {
		super.loadMatrix(projection_matrix, matrix);
	}
	
	protected void loadViewMatrix(Matrix4f matrix) {
		super.loadMatrix(view_matrix, matrix);
	}
	
	protected void loadAtlas(Vector2f size, Vector2f uvCoords) {
		super.loadVector(atlas_size, size);
		super.loadVector(uv, uvCoords);
	}
}
