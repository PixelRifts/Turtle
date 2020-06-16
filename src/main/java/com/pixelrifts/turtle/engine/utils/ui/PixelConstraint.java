package com.pixelrifts.turtle.engine.utils.ui;

public class PixelConstraint extends UIConstraint {
	public final float pixels;

	public PixelConstraint(float pixels) {
		super(ConstraintType.Pixel);
		this.pixels = pixels;
	}
}
