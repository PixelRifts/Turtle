package com.pixelrifts.turtle.engine.utils;

import com.pixelrifts.turtle.engine.events.EventData;
import org.joml.Vector2f;

public class CollisionData extends EventData {
	public final boolean colliding;
	public final Vector2f resolution;

	public CollisionData(boolean colliding, Vector2f resolution) {
		this.colliding = colliding;
		this.resolution = resolution;
	}
}
