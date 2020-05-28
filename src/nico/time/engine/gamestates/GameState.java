package nico.time.engine.gamestates;

import nico.time.engine.rendering.MasterRenderer;

public abstract class GameState {

	protected GameStateManager stateManager;
	
	/**Create game state <br>
	 * Set the game state manager
	 * @param stateManager - Can be used to change states
	 */
	public GameState(GameStateManager stateManager) {
		this.stateManager = stateManager;
	}
	
	/**Initialize game state <br>
	 * Called in game state manager after a new game state is added
	 */
	protected abstract void init();
	
	/**Handle game logic <br>
	 * Called every frame
	 */
	protected abstract void loop();

	/**Render this state
	 * @param renderer - Add objects to the renderer
	 */
	protected abstract void render(MasterRenderer renderer);
}
