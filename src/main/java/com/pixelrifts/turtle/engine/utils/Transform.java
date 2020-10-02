package com.pixelrifts.turtle.engine.utils;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

@SuppressWarnings("all")
public class Transform {
	public static final Transform Identity = new Transform();
	public static final Transform ScaleHundred = new Transform(new Vector2f(0, 0), 0, new Vector2f(100, 100));
	public static final Transform ScaleTwoHundred = new Transform(new Vector2f(0, 0), 0, new Vector2f(200, 200));

	public Vector2f position;
	public float rotation;
	public Vector2f scale;

	private final Matrix4f mat;

	public Transform() {
		this(new Vector2f(0, 0), 0, new Vector2f(1, 1));
	}

	public Transform(Vector2f position, float rotation, Vector2f scale) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		mat = new Matrix4f();
		RecalculateMatrix();
	}

	public Transform SetTranslation(Vector2f d) {
		position.set(d);
		RecalculateMatrix();
		return this;
	}

	public Transform SetRotation(float r) {
		rotation = r;
		RecalculateMatrix();
		return this;
	}

	public Transform SetScale(Vector2f s) {
		scale.set(s);
		RecalculateMatrix();
		return this;
	}

	public Transform SetTranslation(float dx, float dy) {
		position.set(dx, dy);
		RecalculateMatrix();
		return this;
	}

	public Transform SetScale(float sx, float sy) {
		scale.set(sx, sy);
		RecalculateMatrix();
		return this;
	}

	public Transform Translate(Vector2f d) {
		position.add(d);
		RecalculateMatrix();
		return this;
	}

	public Transform Rotate(float r) {
		rotation += r;
		RecalculateMatrix();
		return this;
	}

	public Transform Scale(Vector2f s) {
		scale.add(s);
		RecalculateMatrix();
		return this;
	}

	public Transform Translate(float dx, float dy) {
		position.add(dx, dy);
		RecalculateMatrix();
		return this;
	}

	public Transform Scale(float sx, float sy) {
		scale.add(sx, sy);
		RecalculateMatrix();
		return this;
	}

	private void RecalculateMatrix() {
		mat.identity();
		mat.translate(position.x, position.y, 0);
		mat.rotate((float)Math.toRadians(rotation), new Vector3f(0, 0, 1));
		mat.scale(scale.x, scale.y, 1);
	}

	public Matrix4f ToMatrix() {
		return mat;
	}

	public void Set(Transform t) {
		SetTranslation(t.position);
		SetRotation(t.rotation);
		SetScale(t.scale);
	}
}
