package nico.time.engine.gamestates;

import java.util.Stack;

import nico.time.engine.rendering.MasterRenderer;
import nico.time.engine.utils.Log;

public class GameStateManager {
	
	private final Stack<GameState> states;
	
	/**Create game state manager <br>
	 * Immediately pushes an {@link EngineTester} gamestate to avoid empty stack
	 */
	public GameStateManager() {
		this.states = new Stack<>();
		this.changeState(new EngineTester(this));
		Log.info(getClass(), "Game started!");
	}
	
	/**Push a new gamestate onto the stack <br>
	 * Will call {@link GameState#init()} after pushing it
	 * @param state - The gamestate to be pushed onto the stack
	 */
	public void changeState(GameState state) {
		Log.info(getClass(), "Changing state to " + state.getClass().getSimpleName());
		this.states.push(state);
		this.states.peek().init();
	}
	
	/**Go back to previous state by popping the state on top of the stack <br>
	 * If the stack only contains 1 state nothing will happen
	 */
	public void backToPrevious() {
		if(states.size() > 1)
			this.states.pop();
	}
	
	/**Remove all gamestates from the stack before pushing a new one onto it <br>
	 * This can be used to avoid adding too many states and save memory
	 * @param state - The gamestate to be pushed onto the stack
	 */
	public void clearStack(GameState state) {
		this.states.clear();
		this.changeState(state);
	}
	
	public void clearStack() {
		this.states.clear();
	}
	
	/**Update the state on top of the stack <br>
	 * Called in main game loop <br>
	 * This is how game logic is handled
	 */
	public void loop() {
		if(!states.isEmpty())
			this.states.peek().loop();
	}
	
	/**Render the state on top of the stack <br>
	 * Called in main game loop <br>
	 * This is how rendering system works
	 * @param renderer - The master renderer contained in the main class
	 */
	public void render(MasterRenderer renderer) {
		if(!states.isEmpty())
			this.states.peek().render(renderer);
	}
	
	public boolean shouldClose() {
		return states.isEmpty();
	}
}
