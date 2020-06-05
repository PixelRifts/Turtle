package com.pixelrifts.turtle.glabs.base;

import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;

public class Input {
	private static final boolean[] keysDown;
	private static final boolean[] keysUp;

	private static final boolean[] lastKeysDown;
	private static final boolean[] lastKeysUp;

	static {
		lastKeysDown = new boolean[256];
		lastKeysUp = new boolean[256];
		keysDown = new boolean[256];
		keysUp = new boolean[256];
	}

	public static void Update() {
		System.arraycopy(keysDown, 0, lastKeysDown, 0, keysDown.length);
		System.arraycopy(keysUp, 0, lastKeysUp, 0, keysUp.length);

		Arrays.fill(keysDown, false);
		Arrays.fill(keysUp, false);

		for (int i = 0; i < keysDown.length; i++) keysDown[i] = GetKey(i) && !lastKeysDown[i];
		for (int i = 0; i < keysUp.length; i++) keysUp[i] = !GetKey(i) && !lastKeysUp[i];
	}

	public static boolean GetKey(int keycode) {
		int b = glfwGetKey(Display.Window, keycode);
		return b == GLFW_PRESS || b == GLFW_REPEAT;
	}
	public static boolean GetKeyDown(int keycode) {
		return keysDown[keycode];
	}
	public static boolean GetKeyUp(int keycode) {
		return keysUp[keycode];
	}
	public static boolean GetMouseButton(int button) {
		int b = glfwGetMouseButton(Display.Window, button);
		return b == GLFW_PRESS || b == GLFW_REPEAT;
	}
}
