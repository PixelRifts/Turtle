package com.pixelrifts.turtle.engine.utils.ui;

public class MaxConstraint extends UIConstraint {
	public final float pixels;

	public MaxConstraint(float pixels) {
		super(ConstraintType.Max);
		this.pixels = pixels;
	}
}
