package com.pixelrifts.turtle.engine.utils.ui;

import com.pixelrifts.turtle.engine.rendering.renderables.UIComponent;
import com.pixelrifts.turtle.glabs.base.Application;

public class UIConstraints {
	public float x;
	public float y;
	public float width;
	public float height;

	public float baseX;
	public float baseY;
	public float baseWidth;
	public float baseHeight;

	private UIComponent parent;
	private UIConstraint xConstraint;
	private UIConstraint yConstraint;
	private UIConstraint widthConstraint;
	private UIConstraint heightConstraint;

	public void SetParent(UIComponent parent) {
		baseX = parent.position.x;
		baseY = parent.position.y;
		baseWidth = parent.size.x;
		baseHeight = parent.size.y;
		this.parent = parent;
		Apply();
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
		switch (widthConstraint.type) {
			case Pixel: width = ((PixelConstraint)widthConstraint).pixels; break;
			case Center: assert false : "CentreConstraint not allowed for width or height";
			case Relative: width = ((RelativeConstraint)widthConstraint).percentage / 100f * Application.GetWidth(); break;
			case Aspect:
				assert heightConstraint.type != ConstraintType.Aspect : "Width and Height both cannot be Aspect Constrained";
				width = ((AspectConstraint)widthConstraint).aspect * height; break;
		}
		switch (heightConstraint.type) {
			case Pixel: height = ((PixelConstraint)heightConstraint).pixels; break;
			case Center: assert false : "CentreConstraint not allowed for width or height";
			case Relative: height = ((RelativeConstraint)heightConstraint).percentage / 100f * Application.GetHeight(); break;
			case Aspect:
				assert widthConstraint.type != ConstraintType.Aspect : "Width and Height both cannot be Aspect Constrained";
				height = ((AspectConstraint)heightConstraint).aspect * width; break;
		}
		switch (xConstraint.type) {
			case Pixel: x = ((PixelConstraint)xConstraint).pixels; break;
			case Center: x = Application.GetWidth() / 2f - height / 2; break;
			case Relative: x = ((RelativeConstraint)xConstraint).percentage / 100f * Application.GetWidth(); break;
			case Aspect:
				assert yConstraint.type != ConstraintType.Aspect : "X and Y both cannot be Aspect Constrained";
				x = ((AspectConstraint)xConstraint).aspect * y; break;
		}
		switch (yConstraint.type) {
			case Pixel: y = ((PixelConstraint)yConstraint).pixels; break;
			case Center: y = Application.GetHeight() / 2f - width / 2; break;
			case Relative: y = ((RelativeConstraint)yConstraint).percentage / 100f * Application.GetHeight(); break;
			case Aspect:
				assert xConstraint.type != ConstraintType.Aspect : "X and Y both cannot be Aspect Constrained";
				y = ((AspectConstraint)yConstraint).aspect * x; break;
		}
		parent.position.set(x, y);
		parent.size.set(width, height);
	}

	public void Resize() {
		Apply();
	}
}
