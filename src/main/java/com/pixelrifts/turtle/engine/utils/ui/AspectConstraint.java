package com.pixelrifts.turtle.engine.utils.ui;

public class AspectConstraint extends UIConstraint {
	public final float aspect;

	public AspectConstraint(float aspect) {
		super(ConstraintType.Aspect);
		this.aspect = aspect;
	}
}
