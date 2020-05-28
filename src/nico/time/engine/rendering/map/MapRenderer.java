package nico.time.engine.rendering.map;

import nico.time.engine.rendering.BaseRenderer;
import nico.time.engine.rendering.Camera;
import nico.time.engine.rendering.QuadModel;
import nico.time.engine.rendering.RenderBatch;
import nico.time.engine.utils.math.Matrix4f;
import nico.time.game.playing.map.Map;

public class MapRenderer extends BaseRenderer<Map> {

	private final QuadModel model;
	private final MapShader shader;
	
	public MapRenderer() {
		this.model = new QuadModel(-0.5f, 0.5f, 1.0f, 1.0f);
		this.shader = new MapShader();
	}
	
	@Override
	public void render(RenderBatch<Map> batch, Camera camera) {
		this.shader.start();
		this.bindVertexAttribArray(model);
		this.shader.loadViewMatrix(Matrix4f.createViewMatrix(camera.getPosition()));
		this.shader.loadProjectionMatrix(Matrix4f.createProjectionMatrix(FOV, ZNEAR, ZFAR));
		this.bindTexture(batch.getTexture());
		this.loadModelData(batch.getRenderInstance());
		this.drawModel();
		this.unbindVertexAttribArray();
		this.shader.stop();
	}

	private void loadModelData(Map map) {
		this.shader.loadTransformationMatrix(Matrix4f.createTransformationMatrix(map.get3DPosition(), map.getScale()));
		this.shader.loadTilemap(map.getTiles(), map.getOverlayTiles(), map.getSize());
	}
	
	@Override
	public void cleanUp() {
		this.model.cleanUp();
		this.shader.cleanUp();
	}
}
