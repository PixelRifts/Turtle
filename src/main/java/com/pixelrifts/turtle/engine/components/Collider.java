package com.pixelrifts.turtle.engine.components;

import com.pixelrifts.turtle.engine.architecture.Component;

enum ColliderType {
	POLYGON
}

public abstract class Collider extends Component {
	private ColliderType type;

	public Collider(String name, ColliderType type) {
		super(name);
		this.type = type;
	}

	public static boolean IsColliding(Collider a, Collider b) {
		if (a.type == ColliderType.POLYGON && b.type == ColliderType.POLYGON) {
			return PolygonCollider.IsColliding((PolygonCollider)a, (PolygonCollider)b);
		}
		return false;
	}
}
