package com.pixelrifts.turtle.engine.utils.ui;

import com.pixelrifts.turtle.engine.rendering.renderables.UIComponent;

public class UIConstraints {
	public float x;
	public float y;
	public float width;
	public float height;

	private float baseWidth;
	private float baseHeight;

	private UIComponent parent;
	private UIConstraint xConstraint;
	private UIConstraint yConstraint;
	private UIConstraint widthConstraint;
	private UIConstraint heightConstraint;

	private boolean yCalculated;
	private boolean heightCalculated;

	public void SetParent(UIComponent parent) {
		baseWidth = parent.size.x;
		baseHeight = parent.size.y;
		this.parent = parent;
	}

	public void SetXConstraint(UIConstraint xConstraint) {
		this.xConstraint = xConstraint;
	}
	public void SetYConstraint(UIConstraint yConstraint) {
		this.yConstraint = yConstraint;
	}
	public void SetWidthConstraint(UIConstraint widthConstraint) {
		this.widthConstraint = widthConstraint;
	}
	public void SetHeightConstraint(UIConstraint heightConstraint) {
		this.heightConstraint = heightConstraint;
	}

	public void Apply() {
		yCalculated = false;
		heightCalculated = false;

		baseWidth = parent.parent.size.x;
		baseHeight = parent.parent.size.y;

		if (widthConstraint.type == ConstraintType.Aspect)
			CalculateHeight();
		CalculateWidth();
		if (!heightCalculated)
			CalculateHeight();

		if (xConstraint.type == ConstraintType.Aspect) {
			CalculateY();
		}
		CalculateX();
		if (!yCalculated) {
			CalculateY();
		}

		parent.position.set(x, y);
		parent.size.set(width, height);
	}

	private void CalculateX() {
		switch (xConstraint.type) {
			case Pixel: x = ((PixelConstraint)xConstraint).pixels; break;
			case Center: x = baseWidth / 2f - width / 2f; break;
			case Relative: x = ((RelativeConstraint)xConstraint).percentage / 100f * baseWidth; break;
			case Aspect:
				assert yConstraint.type != ConstraintType.Aspect : "X and Y both cannot be Aspect Constrained";
				x = ((AspectConstraint)xConstraint).aspect * y; break;
		}
	}

	private void CalculateY() {
		switch (yConstraint.type) {
			case Pixel: y = ((PixelConstraint)yConstraint).pixels; break;
			case Center: y = baseHeight / 2f - height / 2f; break;
			case Relative: y = ((RelativeConstraint)yConstraint).percentage / 100f * baseHeight; break;
			case Aspect:
				assert xConstraint.type != ConstraintType.Aspect : "X and Y both cannot be Aspect Constrained";
				y = ((AspectConstraint)yConstraint).aspect * x; break;
		}
		yCalculated = true;
	}

	private void CalculateWidth() {
		switch (widthConstraint.type) {
			case Pixel: width = ((PixelConstraint)widthConstraint).pixels; break;
			case Center: assert false : "CenterConstraint not allowed for width or height";
			case Relative: width = ((RelativeConstraint)widthConstraint).percentage / 100f * baseWidth; break;
			case Aspect:
				assert heightConstraint.type != ConstraintType.Aspect : "Width and Height both cannot be Aspect Constrained";
				width = ((AspectConstraint)widthConstraint).aspect * height; break;
		}
	}

	private void CalculateHeight() {
		switch (heightConstraint.type) {
			case Pixel: height = ((PixelConstraint)heightConstraint).pixels; break;
			case Center: assert false : "CenterConstraint not allowed for width or height";
			case Relative: height = ((RelativeConstraint)heightConstraint).percentage / 100f * baseHeight; break;
			case Aspect:
				assert widthConstraint.type != ConstraintType.Aspect : "Width and Height both cannot be Aspect Constrained";
				height = ((AspectConstraint)heightConstraint).aspect * width; break;
		}
		heightCalculated = true;
	}

	public void Resize() {
		Apply();
	}
}
