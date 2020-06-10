package com.pixelrifts.turtle.engine.utils;

public class Projection {
	public final float min;
	public final float max;

	public Projection(float min, float max) {
		this.min = min;
		this.max = max;
	}

	public boolean Overlaps(Projection o) {
		return min < o.max && max > o.min;
	}
}
