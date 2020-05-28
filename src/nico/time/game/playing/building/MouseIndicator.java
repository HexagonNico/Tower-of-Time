package nico.time.game.playing.building;

import java.util.ArrayList;

import nico.time.engine.input.MousePosition;
import nico.time.engine.utils.math.Vector2f;
import nico.time.engine.utils.math.Vector3f;
import nico.time.game.GameObject;
import nico.time.game.playing.map.Map;

public class MouseIndicator extends GameObject {

	private int animationTime;
	private int animationDelay;
	
	public MouseIndicator() {
		super("indicator", 0.0f, 0.0f);
		super.atlasSize = new Vector2f(2, 2);
		this.animationTime = 0;
		this.animationDelay = 30;
		super.zLayer = 4;
	}

	public void listenToMouseMovement(Vector3f camera) {
		Vector3f mouseWorldPos = MousePosition.getWorldCoords(camera);
		super.position.x = 0.5f * Math.round(mouseWorldPos.x / 0.5f);
		super.position.y = 0.5f * Math.round(mouseWorldPos.y / 0.5f);
	}
	
	private void updateAnimation() {
		this.animationTime++;
		if(animationTime == animationDelay) {
			super.uv.x = 1 - super.uv.x;
			this.animationTime = 0;
		}
	}
	
	public void updateTexture(Map map, ArrayList<DefenceTower> defences) {
		this.updateAnimation();
		if(map.getTileAt(position) != 0 || map.getOverlayTileAt(position) >= 11) {
			super.uv.y = 1;
		} else {
			super.uv.y = 0;
		}
		for(DefenceTower obj : defences) {
			if(obj.getPosition().distance(position) <= 0.5f) {
				super.uv.y = 1;
			}
		}
	}
	
	public boolean allowsBuilding() {
		return uv.y == 0;
	}
	
	public boolean isInPlace(Vector3f camera) {
		Vector3f mouseWorldPos = MousePosition.getWorldCoords(camera);
		boolean x = super.position.x == 0.5f * Math.round(mouseWorldPos.x / 0.5f);
		boolean y = super.position.y == 0.5f * Math.round(mouseWorldPos.y / 0.5f);
		return x && y;
	}
}
