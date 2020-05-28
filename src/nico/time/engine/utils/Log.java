package nico.time.engine.utils;

public class Log {

	public static void info(Class<?> caller, String message) {
		System.out.println("[" + caller.getSimpleName() + "] [Info]: " + message);
	}
	
	public static void error(Class<?> caller, String message) {
		System.err.println("[" + caller.getSimpleName() + "] [Error]: " + message);
	}
}
