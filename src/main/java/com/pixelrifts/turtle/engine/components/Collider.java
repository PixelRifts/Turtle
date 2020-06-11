package com.pixelrifts.turtle.engine.components;

import com.pixelrifts.turtle.engine.architecture.Component;
import com.pixelrifts.turtle.engine.utils.CollisionData;

enum ColliderType {
	POLYGON
}

public abstract class Collider extends Component {
	private final ColliderType type;

	public Collider(String name, ColliderType type) {
		super(name);
		this.type = type;
	}

	public static boolean IsColliding(Collider a, Collider b) {
		return a.type == ColliderType.POLYGON && b.type == ColliderType.POLYGON && PolygonCollider.IsColliding((PolygonCollider) a, (PolygonCollider) b);
	}

	public static CollisionData GetCollision(Collider a, Collider b) {
		return a.type == ColliderType.POLYGON && b.type == ColliderType.POLYGON ? PolygonCollider.GetCollision((PolygonCollider) a, (PolygonCollider) b) : null;
	}
}
