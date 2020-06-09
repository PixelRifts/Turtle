package com.pixelrifts.turtle.engine.base;

import com.pixelrifts.turtle.engine.architecture.GameObject;
import com.pixelrifts.turtle.engine.architecture.Scene;
import com.pixelrifts.turtle.engine.components.SpriteRenderer;
import com.pixelrifts.turtle.engine.managers.ResourceManager;
import com.pixelrifts.turtle.glabs.objects.Texture;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class GameScene extends Scene {
	public GameScene() {
		super("Game");
	}

	@Override
	public void StartScene() {
		Texture texture = ResourceManager.ImportTexture("src/main/resources/smiley.png");
		GameObject a = new GameObject("Test");

		a.transform.SetScale(new Vector2f(100, 100));
		a.AttachComponent (
				new SpriteRenderer()
					.WithTexture(texture)
					.WithMixColour(new Vector4f(0.2f, 0.3f, 0.8f, 1.0f))
		);
		AddGameObjectToScene(a);

		super.StartScene();
	}
}
