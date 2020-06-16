package com.pixelrifts.turtle.engine.utils.ui;

public class RelativeConstraint extends UIConstraint {
	public final float percentage;

	public RelativeConstraint(float percentage) {
		super(ConstraintType.Relative);
		this.percentage = percentage;
	}
}
