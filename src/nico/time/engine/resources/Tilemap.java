package nico.time.engine.resources;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import nico.time.engine.utils.Log;

public class Tilemap {
	
	private int[] tiles;
	private int[] overlay;
	private int rows;
	private int columns;
	
	public Tilemap(String fileName) {
		try {
			this.loadTilemap("res/map/"+fileName);
		} catch (FileNotFoundException e) {
			Log.error(getClass(), "Could not find file res/map/" + fileName);
		} catch (IOException e) {
			Log.error(getClass(), "Could not read file res/map/" + fileName);
		} catch (ParseException e) {
			Log.error(getClass(), "Malformed JSON in file res/map/" + fileName);
		}
	}
	
	public int[] getTiles() {
		return tiles;
	}
	
	public int[] getOverlay() {
		return overlay;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getColumns() {
		return columns;
	}
	
	private void loadTilemap(String filePath) throws FileNotFoundException, IOException, ParseException {
		//Parse the root JSON object
		JSONParser parser = new JSONParser();
		JSONObject root = (JSONObject) parser.parse(new FileReader(filePath));
		
		//Get the array containing the tiles
		JSONArray tilesJsonArray = (JSONArray) root.get("tiles");
		ArrayList<Integer> tiles = new ArrayList<>();
		
		//Iterate through the outer array
		this.rows = tilesJsonArray.size();
		for(int i = 0; i < rows; i++) {
			//Get the inner array
			JSONArray innerTilesJsonArray = (JSONArray) tilesJsonArray.get(i);
			this.columns = innerTilesJsonArray.size();
			for(int j = 0; j < columns; j++) {
				//Iterate through the inner array to get tiles values
				tiles.add(((Long) innerTilesJsonArray.get(j)).intValue());
			}
		}

		//Get the array containing the tiles
		JSONArray overlayJsonArray = (JSONArray) root.get("overlay");
		ArrayList<Integer> overlay = new ArrayList<>();
		
		//Iterate through the outer array
		for(int i = 0; i < rows; i++) {
			//Get the inner array
			JSONArray innerTilesJsonArray = (JSONArray) overlayJsonArray.get(i);
			for(int j = 0; j < columns; j++) {
				//Iterate through the inner array to get tiles values
				overlay.add(((Long) innerTilesJsonArray.get(j)).intValue());
			}
		}
		
		this.tiles = tiles.stream().mapToInt(Integer::intValue).toArray();
		this.overlay = overlay.stream().mapToInt(Integer::intValue).toArray();
	}
}
