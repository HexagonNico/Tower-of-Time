package nico.time.engine.rendering.objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import nico.time.engine.rendering.RenderBatch;
import nico.time.engine.resources.Texture;
import nico.time.game.GameObject;

public class ObjectBatch extends RenderBatch<GameObject> {

	private final HashMap<Texture, ArrayList<GameObject>> objects;
	
	public ObjectBatch() {
		this.objects = new HashMap<>();
	}
	
	@Override
	public Set<Texture> getTextures() {
		return objects.keySet();
	}
	
	@Override
	public Texture getTexture() {
		return null;
	}
	
	@Override
	public Collection<GameObject> getRenderInstances(Texture texture) {
		return objects.get(texture);
	}
	
	@Override
	public GameObject getRenderInstance() {
		return null;
	}

	@Override
	public void add(GameObject instance) {
		Texture texture = instance.getTexture();
		ArrayList<GameObject> batch = this.objects.get(texture);
		if(batch!=null) {
			batch.add(instance);
		} else {
			ArrayList<GameObject> newBatch = new ArrayList<>();
			newBatch.add(instance);
			this.objects.put(texture, newBatch);
		}
	}

	@Override
	public boolean isEmpty() {
		return objects.isEmpty();
	}

	@Override
	public void clear() {
		this.objects.clear();
	}

}
