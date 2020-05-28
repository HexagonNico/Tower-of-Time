package nico.time.game.playing.map;

import nico.time.engine.resources.Resources;
import nico.time.engine.resources.Tilemap;
import nico.time.engine.utils.math.Vector2f;
import nico.time.game.GameObject;

public class Map extends GameObject {
	
	private Tilemap tileMap;
	
	public Map(String tileset, String tilemap) {
		super(tileset, 0.0f, 0.0f);
		this.tileMap = Resources.getTilemap(tilemap);
		this.scale = new Vector2f(0.5f * tileMap.getColumns(), 0.5f * tileMap.getRows());
		super.zLayer = 5;
	}
	
	public int[] getTiles() {
		return tileMap.getTiles();
	}
	
	public int[] getOverlayTiles() {
		return tileMap.getOverlay();
	}

	public Vector2f getSize() {
		return new Vector2f(tileMap.getColumns(), tileMap.getRows());
	}
	
	public int getTileAt(Vector2f position) {
		int x = (int) (position.x * 2 + 6);
		int y = (int) (4 - position.y * 2);
		if(x >= 0 && x < tileMap.getColumns() && y >= 0 && y < tileMap.getRows())
			return tileMap.getTiles()[y * tileMap.getColumns() + x];
		else
			return -1;
	}
	
	public int getOverlayTileAt(Vector2f position) {
		int x = (int) (position.x * 2 + 6);
		int y = (int) (4 - position.y * 2);
		if(x >= 0 && x < tileMap.getColumns() && y >= 0 && y < tileMap.getRows())
			return tileMap.getOverlay()[y * tileMap.getColumns() + x];
		else
			return -1;
	}
}
