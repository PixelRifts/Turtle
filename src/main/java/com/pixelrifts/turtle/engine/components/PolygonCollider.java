package com.pixelrifts.turtle.engine.components;

import com.pixelrifts.turtle.engine.events.EventNode;
import com.pixelrifts.turtle.engine.utils.CollisionData;
import com.pixelrifts.turtle.engine.utils.Projection;
import org.joml.Vector2f;

public class PolygonCollider extends Collider {
	public EventNode collisionEvent = new EventNode();

	public final Vector2f[] localVertices;

	private final Vector2f[] transformedVertices;

	private PolygonCollider(Vector2f[] localVertices) {
		super("PolygonCollider", ColliderType.POLYGON);
		this.localVertices = localVertices;
		this.transformedVertices = new Vector2f[localVertices.length];
	}

	@Override
	public void Start() {
		RecalculateTransformedVertices();
	}

	public static PolygonCollider CreateQuad(float width, float height) {
		float hw = width / 2;
		float hh = height / 2;
		return new PolygonCollider(new Vector2f[]{
				new Vector2f(-hw, -hh),
				new Vector2f(hw, -hh),
				new Vector2f(hw, hh),
				new Vector2f(-hw, hh)
		});
	}

	private void RecalculateTransformedVertices() {
		for (int i = 0; i < localVertices.length; i++) {
			transformedVertices[i] = localVertices[i].add(gameObject.transform.position, new Vector2f());
		}
	}

	@SuppressWarnings("all")
	private Vector2f[] GetAxes() {
		RecalculateTransformedVertices();
		Vector2f[] axes = new Vector2f[transformedVertices.length];
		for (int i = 0; i < axes.length; i++) {
			int n = i == axes.length - 1 ? 0 : i + 1;
			Vector2f edge = transformedVertices[i].sub(transformedVertices[n], new Vector2f());
			axes[i] = new Vector2f(-edge.y, edge.x).normalize();
		}
		return axes;
	}

	private Projection Project(Vector2f axis) {
		float min = transformedVertices[0].dot(axis);
		float max = min;
		for (int i = 1; i < transformedVertices.length; i++) {
			float v = transformedVertices[i].dot(axis);
			if (v < min) min = v;
			else if (v > max) max = v;
		}
		return new Projection(min, max);
	}

	public static CollisionData GetCollision(PolygonCollider a, PolygonCollider b) {
		Vector2f minAxis = new Vector2f();
		float minOverlap = Float.MAX_VALUE;
		Vector2f[] axesA = a.GetAxes();
		Vector2f[] axesB = b.GetAxes();
		for (Vector2f axisA : axesA) {
			Projection projectionA = a.Project(axisA);
			Projection projectionB = b.Project(axisA);
			if (!projectionA.Overlaps(projectionB)) {
				return new CollisionData(false, new Vector2f());
			} else {
				float overlap = projectionA.GetOverlap(projectionB);
				if (overlap < minOverlap) {
					minOverlap = overlap;
					minAxis = axisA;
				}
			}
		}
		for (Vector2f axisB : axesB) {
			Projection projectionA = a.Project(axisB);
			Projection projectionB = b.Project(axisB);
			if (!projectionA.Overlaps(projectionB)) {
				return new CollisionData(false, new Vector2f());
			} else {
				float overlap = projectionA.GetOverlap(projectionB);
				if (overlap < minOverlap) {
					minOverlap = overlap;
					minAxis = axisB;
				}
			}
		}
		minAxis.normalize().mul(minOverlap);
		CollisionData data = new CollisionData(true, minAxis);
		a.collisionEvent.Dispatch("---", data);
		return data;
	}

	public static boolean IsColliding(PolygonCollider a, PolygonCollider b) {
		Vector2f[] axesA = a.GetAxes();
		Vector2f[] axesB = b.GetAxes();
		for (Vector2f axisA : axesA) {
			Projection projectionA = a.Project(axisA);
			Projection projectionB = b.Project(axisA);
			if (!projectionA.Overlaps(projectionB)) return false;
		}
		for (Vector2f axisB : axesB) {
			Projection projectionA = a.Project(axisB);
			Projection projectionB = b.Project(axisB);
			if (!projectionA.Overlaps(projectionB)) return false;
		}
		return true;
	}
}
