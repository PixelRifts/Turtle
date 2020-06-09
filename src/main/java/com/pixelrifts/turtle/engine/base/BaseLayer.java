package com.pixelrifts.turtle.engine.base;

import com.pixelrifts.turtle.engine.managers.SceneManager;
import com.pixelrifts.turtle.glabs.base.Layer;
import com.pixelrifts.turtle.glabs.event.CharTypedEvent;
import com.pixelrifts.turtle.glabs.event.KeyPressedEvent;
import com.pixelrifts.turtle.glabs.event.LayerEvent;

import static org.lwjgl.glfw.GLFW.*;

public class BaseLayer extends Layer {
	private boolean shift_press = false;
	private static final boolean debug = true;

	public BaseLayer() {
		super("Base");
		SceneManager.RegisterScene(0, new GameScene());
	}

	@Override
	public void OnAttach() {
		SceneManager.SetScene(0);
	}

	@Override
	public void OnDetach() {
		SceneManager.EndScenes();
	}

	@Override
	public void OnUpdate(float dt) {
		SceneManager.Update(dt);
	}

	@Override
	public void OnRender() {
		SceneManager.Render();
	}

	@Override
	public boolean OnLayerEvent(LayerEvent e) {
		if (e instanceof KeyPressedEvent) {
			KeyPressedEvent k = (KeyPressedEvent) e;
			if (shift_press) {
				if (k.GetKeyCode() >= GLFW_KEY_0 && k.GetKeyCode() <= GLFW_KEY_9) {
					SceneManager.SetScene(k.GetKeyCode() - GLFW_KEY_0);
				}
			}
			if (k.GetKeyCode() == GLFW_KEY_LEFT_SHIFT)
				shift_press = true;
			return true;
		}
		SceneManager.OnLayerEvent(e);
		return false;
	}

	@Override
	public void OnImGuiRender() {
		if (debug)
			SceneManager.ImGuiRender();
	}
}
