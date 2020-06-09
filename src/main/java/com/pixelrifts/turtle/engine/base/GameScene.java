package com.pixelrifts.turtle.engine.base;

import com.pixelrifts.turtle.engine.architecture.GameObject;
import com.pixelrifts.turtle.engine.architecture.Scene;
import com.pixelrifts.turtle.engine.components.AnimationRenderer;
import com.pixelrifts.turtle.engine.managers.ResourceManager;
import org.joml.Vector2f;

public class GameScene extends Scene {
	public GameScene() {
		super("Game");
	}

	@Override
	public void StartScene() {
		GameObject a = new GameObject("Test");

		a.transform.SetScale(new Vector2f(100, 100));
		a.AttachComponent(new AnimationRenderer(ResourceManager.ImportAnimation("test")));
		AddGameObjectToScene(a);

		super.StartScene();
	}
}
