package nico.time.engine.rendering.map;

import nico.time.engine.rendering.Shader;
import nico.time.engine.utils.math.Matrix4f;
import nico.time.engine.utils.math.Vector2f;

public class MapShader extends Shader {

	private int transformation_matrix;
	private int projection_matrix;
	private int view_matrix;
	
	private int[] tilemap;
	private int[] tilemap_overlay;
	private int map_size;
	
	protected MapShader() {
		super("map_shader.vert", "map_shader.frag");
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
		
		this.tilemap = super.getUniformArrayLocation("tilemap", 400);
		this.tilemap_overlay = super.getUniformArrayLocation("tilemap_overlay", 400);
		this.map_size = super.getUniformLocation("map_size");
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
	
	protected void loadTilemap(int[] tiles, int[] overlay, Vector2f size) {
		for(int i = 0; i < tiles.length; i++) {
			super.loadInt(tilemap[i], tiles[i]);
			super.loadInt(tilemap_overlay[i], overlay[i]);
		}
		super.loadVector(map_size, size);
	}
}
