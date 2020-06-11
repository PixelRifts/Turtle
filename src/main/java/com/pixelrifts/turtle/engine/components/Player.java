package com.pixelrifts.turtle.engine.components;

import com.pixelrifts.turtle.engine.architecture.Component;
import com.pixelrifts.turtle.glabs.base.Input;

import static org.lwjgl.glfw.GLFW.*;

public class Player extends Component {
	public Player() {
		super("Player");
	}

	@Override
	public void Update(float dt) {
		if (Input.GetKey(GLFW_KEY_UP)) {
			gameObject.transform.Translate(0, 20 * dt);
		} else if (Input.GetKey(GLFW_KEY_DOWN)) {
			gameObject.transform.Translate(0, -20 * dt);
		}
		if (Input.GetKey(GLFW_KEY_LEFT)) {
			gameObject.transform.Translate(-20 * dt, 0);
		} else if (Input.GetKey(GLFW_KEY_RIGHT)) {
			gameObject.transform.Translate(20 * dt, 0);
		}
	}
}
