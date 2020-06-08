package com.pixelrifts.turtle.engine.base;

import com.pixelrifts.turtle.engine.rendering.Renderer;
import com.pixelrifts.turtle.engine.utils.Rect;
import com.pixelrifts.turtle.engine.utils.Transform;
import com.pixelrifts.turtle.glabs.base.Layer;
import com.pixelrifts.turtle.glabs.event.LayerEvent;
import com.pixelrifts.turtle.glabs.objects.Texture;
import imgui.ImGui;
import org.joml.Vector4f;

public class GameLayer extends Layer {
	private final GameScene gameScene;

	private final float[] value = new float[1];

	public GameLayer() {
		super("Game");
		gameScene = new GameScene();
	}

	@Override
	public void OnAttach() {
		gameScene.StartScene();
	}

	@Override
	public void OnDetach() {
		gameScene.EndScene();
	}

	@Override
	public void OnUpdate(float dt) {
		gameScene.Update(dt);
	}

	@Override
	public void OnRender() {
		gameScene.Render();
	}

	@Override
	public boolean OnLayerEvent(LayerEvent e) {
		gameScene.OnLayerEvent(e);
		return false;
	}

	@Override
	public void OnImGuiRender() {
		gameScene.ImGuiRender();
	}
}