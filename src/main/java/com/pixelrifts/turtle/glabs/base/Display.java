package com.pixelrifts.turtle.glabs.base;

import static org.lwjgl.glfw.GLFW.*;

import com.pixelrifts.turtle.glabs.event.*;
import org.lwjgl.opengl.GL;

public class Display {
	public static long Window;
	private static Application s_App;

	public static void Init() {
		if (!glfwInit()) {
			System.err.println("Failed to Initialize GLFW");
			System.exit(-1);
		}
	}

	public static void CreateWindow(Application app) {
		Window = glfwCreateWindow(Application.GetWidth(), Application.GetHeight(), app.GetTitle(), 0, 0);
		s_App = app;
		glfwMakeContextCurrent(Window);
		GL.createCapabilities();

		glfwSetWindowSizeCallback(Window, (long window, int width, int height) -> s_App.OnEvent(new WindowResizedEvent(width, height)));

		glfwSetKeyCallback(Window, (long window, int key, int scancode, int action, int mods) -> {
			KeyEvent e = null;

			if (action == GLFW_PRESS) e = new KeyPressedEvent(key, 0);
			else if (action == GLFW_REPEAT) e = new KeyPressedEvent(key, 1);
			else if (action == GLFW_RELEASE) e = new KeyReleasedEvent(key);
			else System.exit(-1);

			s_App.OnEvent(e);
		});

		glfwSetMouseButtonCallback(Window, (long window, int button, int action, int mods) -> {
			MouseButtonEvent e = null;

			if (action == GLFW_PRESS) e = new MouseButtonPressedEvent(button);
			else if (action == GLFW_REPEAT) e = new MouseButtonPressedEvent(button);
			else if (action == GLFW_RELEASE) e = new MouseButtonReleasedEvent(button);

			s_App.OnEvent(e);
		});

		glfwSetCharCallback(Window, (window, character) -> s_App.OnEvent(new CharTypedEvent(character)));

		glfwSetCursorPosCallback(Window, (long window, double x, double y) -> s_App.OnEvent(new MouseMovedEvent((float)x, (float)y)));

		glfwSetScrollCallback(Window, (long window, double xScroll, double yScroll) -> s_App.OnEvent(new MouseScrolledEvent((float)xScroll, (float)yScroll)));
	}

	public static boolean IsOpen() {
		return !glfwWindowShouldClose(Window);
	}

	public static void PollEvents() {
		glfwPollEvents();
	}

	public static void SwapBuffers() {
		glfwSwapBuffers(Window);
	}

	public static void DestroyWindow() {
		glfwDestroyWindow(Window);
	}

	public static void Terminate() {
		glfwTerminate();
	}
}
