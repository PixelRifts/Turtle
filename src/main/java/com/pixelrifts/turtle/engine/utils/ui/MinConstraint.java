package com.pixelrifts.turtle.engine.utils.ui;

public class MinConstraint extends UIConstraint {
	public final float pixels;

	public MinConstraint(float pixels) {
		super(ConstraintType.Pixel);
		this.pixels = pixels;
	}
}
