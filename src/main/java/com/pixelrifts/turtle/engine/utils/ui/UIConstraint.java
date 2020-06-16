package com.pixelrifts.turtle.engine.utils.ui;

enum ConstraintType {
	Pixel, Center, Relative
}
public abstract class UIConstraint {
	protected ConstraintType type;

	public UIConstraint(ConstraintType type) {
		this.type = type;
	}
}
