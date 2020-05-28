package nico.time.engine.rendering.map;

import java.util.Collection;
import java.util.Set;

import nico.time.engine.rendering.RenderBatch;
import nico.time.engine.resources.Texture;
import nico.time.game.playing.map.Map;

public class MapBatch extends RenderBatch<Map> {

	private Map map;
	
	@Override
	public Set<Texture> getTextures() {
		return null;
	}
	
	@Override
	public Texture getTexture() {
		return map.getTexture();
	}

	@Override
	public Collection<Map> getRenderInstances(Texture texture) {
		return null;
	}
	
	@Override
	public Map getRenderInstance() {
		return map;
	}

	@Override
	public void add(Map instance) {
		this.map = instance;
	}

	@Override
	public boolean isEmpty() {
		return map == null;
	}

	@Override
	public void clear() {
		this.map = null;
	}

}
