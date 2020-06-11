package com.pixelrifts.turtle.engine.utils;

public class Projection {
	public final float min;
	public final float max;

	public Projection(float min, float max) {
		this.min = min;
		this.max = max;
	}

	@SuppressWarnings("all")
	public boolean Overlaps(Projection o) {
		return min < o.max && max > o.min;
	}

	public float GetOverlap(Projection o) {
		if (max > o.min) return max - o.min;
		else if (min > o.max) return min - o.max;
		else assert false : "Bad Overlap case!";
		return 0;
	}
}
