package com.pixelrifts.turtle.engine.utils;

public class Rect {
	public float x;
	public float y;
	public float width;
	public float height;

	public Rect(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Rect(double x, double y, double width, double height) {
		this.x = (float) x;
		this.y = (float) y;
		this.width = (float) width;
		this.height = (float) height;
	}

	public Rect Scale(float sx, float sy) {
		width *= sx;
		height *= sy;
		return this;
	}
}
