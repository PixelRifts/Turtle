package com.pixelrifts.turtle.engine.utils;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Transform {
	public static final Transform Identity = new Transform();
	public static final Transform ScaleHundred = new Transform(new Vector2f(0, 0), 0, new Vector2f(100, 100));

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

	public void SetTranslation(Vector2f d) {
		position.set(d);
		RecalculateMatrix();
	}

	public void SetRotation(float r) {
		rotation = r;
		RecalculateMatrix();
	}

	public void SetScale(Vector2f s) {
		scale.set(s);
		RecalculateMatrix();
	}

	public void Translate(Vector2f d) {
		position.add(d);
		RecalculateMatrix();
	}

	public void Rotate(float r) {
		rotation += r;
		RecalculateMatrix();
	}

	public void Scale(Vector2f s) {
		scale.add(s);
		RecalculateMatrix();
	}

	public void Translate(float dx, float dy) {
		position.add(dx, dy);
		RecalculateMatrix();
	}

	public void Scale(float sx, float sy) {
		scale.add(sx, sy);
		RecalculateMatrix();
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
}
