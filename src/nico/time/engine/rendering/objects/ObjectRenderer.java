package nico.time.engine.rendering.objects;

import nico.time.engine.rendering.BaseRenderer;
import nico.time.engine.rendering.Camera;
import nico.time.engine.rendering.QuadModel;
import nico.time.engine.rendering.RenderBatch;
import nico.time.engine.resources.Texture;
import nico.time.engine.utils.math.Matrix4f;
import nico.time.game.GameObject;

public class ObjectRenderer extends BaseRenderer<GameObject> {

	private final QuadModel model;
	private final ObjectShader shader;
	
	public ObjectRenderer() {
		this.model = new QuadModel(-0.5f, 0.5f, 1.0f, 1.0f);
		this.shader = new ObjectShader();
	}

	@Override
	public void render(RenderBatch<GameObject> batch, Camera camera) {
		this.shader.start();
		this.bindVertexAttribArray(model);
		this.loadSceneData(camera);
		for(Texture texture : batch.getTextures()) {
			this.bindTexture(texture);
			for(GameObject object : batch.getRenderInstances(texture)) {
				this.loadModelData(object);
				this.drawModel();
			}
		}
		this.unbindVertexAttribArray();
		this.shader.stop();
	}
	
	private void loadSceneData(Camera camera) {
		this.shader.loadViewMatrix(Matrix4f.createViewMatrix(camera.getPosition()));
		this.shader.loadProjectionMatrix(Matrix4f.createProjectionMatrix(FOV, ZNEAR, ZFAR));
	}
	
	private void loadModelData(GameObject object) {
		this.shader.loadTransformationMatrix(Matrix4f.createTransformationMatrix(object.get3DPosition(), object.getScale()));
		this.shader.loadAtlas(object.getAtlasSize(), object.getUV());
	}
	
	@Override
	public void cleanUp() {
		this.model.cleanUp();
		this.shader.cleanUp();
	}
}
