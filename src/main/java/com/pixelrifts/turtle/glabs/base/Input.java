package com.pixelrifts.turtle.glabs.base;

import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class Input {
	private static final double[] x = new double[1];
	private static final double[] y = new double[1];
	private static final Vector2f mousePos = new Vector2f();

	public static boolean GetKey(int keycode) {
		int b = glfwGetKey(Display.Window, keycode);
		return b == GLFW_PRESS || b == GLFW_REPEAT;
	}

	public static Vector2f GetMousePos() {
		glfwGetCursorPos(Display.Window, x, y);
		return mousePos.set(x[0], y[0]);
	}

	public static boolean GetMouseButton(int button) {
		int b = glfwGetMouseButton(Display.Window, button);
		return b == GLFW_PRESS || b == GLFW_REPEAT;
	}
}
