package com.pixelrifts.turtle.engine.base;

import com.pixelrifts.turtle.engine.architecture.GameObject;
import com.pixelrifts.turtle.engine.architecture.Scene;
import com.pixelrifts.turtle.engine.components.Collider;
import com.pixelrifts.turtle.engine.components.Player;
import com.pixelrifts.turtle.engine.components.PolygonCollider;
import com.pixelrifts.turtle.engine.components.SpriteRenderer;
import com.pixelrifts.turtle.engine.managers.ResourceManager;
import com.pixelrifts.turtle.engine.rendering.renderables.Sprite;
import com.pixelrifts.turtle.engine.rendering.renderables.ui.UIBlock;
import com.pixelrifts.turtle.engine.utils.CollisionData;
import com.pixelrifts.turtle.engine.utils.Transform;
import com.pixelrifts.turtle.engine.utils.ui.PixelConstraint;
import com.pixelrifts.turtle.engine.utils.ui.RelativeConstraint;
import com.pixelrifts.turtle.engine.utils.ui.UIConstraints;
import org.joml.Vector2f;
import org.joml.Vector4f;

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

		b.transform.SetScale(100, 100);
		b.transform.Translate(-200, -200);
		b.AttachComponent(new Player());
		b.AttachComponent(new SpriteRenderer(new Sprite().WithTexture(ResourceManager.ImportTexture("smiley.png"))));
		b.AttachComponent(PolygonCollider.CreateQuad(100, 100));
		AddGameObjectToScene(b);

		UIBlock b = new UIBlock(new Vector4f(0.8f, 0.2f, 0.3f, 1.0f));
		UIConstraints constraints = new UIConstraints();
		constraints.SetXConstraint(new PixelConstraint(0));
		constraints.SetYConstraint(new PixelConstraint(0));
		constraints.SetWidthConstraint(new RelativeConstraint(30));
		constraints.SetHeightConstraint(new RelativeConstraint(100));
		b.ApplyConstraints(constraints);
		uiRegistry.RegisterComponent(b);

		super.StartScene();
	}

	@Override
	public void Update(float dt) {
		super.Update(dt);
		Collider cA = a.GetComponent(Collider.class);
		Collider cB = b.GetComponent(Collider.class);
		if (cA != null && cB != null) {
			CollisionData collisionData = Collider.GetCollision(cA, cB);
			if (collisionData != null)
				if (collisionData.colliding)
					b.transform.Translate(collisionData.resolution);
		}
	}
}
