package com.pixelrifts.turtle.engine.utils.ui;

enum ConstraintType {
	Min, Max, Center, Relative, Aspect, Pixel
}
public abstract class UIConstraint {
	protected ConstraintType type;

	public UIConstraint(ConstraintType type) {
		this.type = type;
	}
}
