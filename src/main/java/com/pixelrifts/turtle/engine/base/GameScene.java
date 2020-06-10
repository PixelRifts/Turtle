package com.pixelrifts.turtle.engine.base;

import com.pixelrifts.turtle.engine.architecture.GameObject;
import com.pixelrifts.turtle.engine.architecture.Scene;
import com.pixelrifts.turtle.engine.components.AnimationRenderer;
import com.pixelrifts.turtle.engine.components.Collider;
import com.pixelrifts.turtle.engine.components.PolygonCollider;
import com.pixelrifts.turtle.engine.components.SpriteRenderer;
import com.pixelrifts.turtle.engine.managers.ResourceManager;
import com.pixelrifts.turtle.engine.rendering.renderables.Sprite;
import com.pixelrifts.turtle.engine.utils.Transform;
import org.joml.Vector2f;

public class GameScene extends Scene {
	GameObject a;
	GameObject b;

	public GameScene() {
		super("Game");
	}

	@Override
	public void StartScene() {
		a = new GameObject("Test");
		b = new GameObject("Test2");

		a.transform = Transform.ScaleHundred;
		a.AttachComponent(new SpriteRenderer(new Sprite().WithTexture(ResourceManager.ImportTexture("sadey.png"))));
		a.AttachComponent(PolygonCollider.CreateQuad(100, 100));
		AddGameObjectToScene(a);

		b.transform.SetScale(new Vector2f(100, 100));
		b.transform.SetTranslation(-200, -200);
		b.AttachComponent(new SpriteRenderer(new Sprite().WithTexture(ResourceManager.ImportTexture("smiley.png"))));
		b.AttachComponent(PolygonCollider.CreateQuad(100, 100));
		AddGameObjectToScene(b);

		super.StartScene();
	}

	@Override
	public void Update(float dt) {
		b.transform.Translate(0.1f * dt, 0.1f * dt);
		if (Collider.IsColliding(a.GetComponent(Collider.class), b.GetComponent(Collider.class)))
			System.out.println("COLLISION");
		super.Update(dt);
	}
}
