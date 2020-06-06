package com.pixelrifts.turtle.engine.rendering;

import com.pixelrifts.turtle.glabs.base.Application;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {
	private final Matrix4f viewMatrix;
	private final Matrix4f projMatrix;
	private Matrix4f viewProjMatrix;

	private final Vector2f position;
	private float rotation;

	public static Camera Get() {
		return Renderer.camera;
	}

	public Camera() {
		this(new Vector2f(0, 0));
	}

	public Camera(Vector2f position) {
		this(position, 0);
	}

	public Camera(Vector2f position, float rotation) {
		this.position = position;
		this.rotation = rotation;
		viewMatrix = new Matrix4f().identity();
		projMatrix = new Matrix4f().identity();
		viewProjMatrix = new Matrix4f().identity();
		CalculateViewMatrix();
		CalculateProjMatrix();
	}

	public void SetRotation(float rotation) {
		this.rotation = rotation;
		CalculateViewMatrix();
	}
	public void SetPosition(float x, float y) {
		this.position.set(x, y);
		CalculateViewMatrix();
	}

	private void CalculateViewMatrix() {
		viewMatrix.identity();
		viewMatrix.rotate((float) Math.toRadians(rotation) ,new Vector3f(1, 0, 0));
		viewMatrix.translate(new Vector3f(position, 0.0f).mul(-1));
		viewProjMatrix = projMatrix.mul(viewMatrix, new Matrix4f());
	}

	private void CalculateProjMatrix() {
		projMatrix.identity();
		float hw = Application.GetWidth();
		float hh = Application.GetHeight();
		projMatrix.ortho(-hw, hw, -hh, hh, -1, 10000);
		viewProjMatrix = projMatrix.mul(viewMatrix, new Matrix4f());
	}

	public void WindowResized() {
		CalculateProjMatrix();
	}

	public Matrix4f getViewProjMatrix() {
		return viewProjMatrix;
	}
}
