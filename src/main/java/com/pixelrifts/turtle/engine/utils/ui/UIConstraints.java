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
			case Center: width = Application.GetWidth() / 2f; break;
			case Relative: width = ((RelativeConstraint)widthConstraint).percentage / 100f * Application.GetWidth(); break;
		}
		switch (heightConstraint.type) {
			case Pixel: height = ((PixelConstraint)heightConstraint).pixels; break;
			case Center: height = Application.GetWidth() / 2f; break;
			case Relative: height = ((RelativeConstraint)heightConstraint).percentage / 100f * Application.GetHeight(); break;
		}
		switch (xConstraint.type) {
			case Pixel: x = ((PixelConstraint)xConstraint).pixels; break;
			case Center: x = Application.GetWidth() / 2f; break;
			case Relative: x = ((RelativeConstraint)xConstraint).percentage / 100f * Application.GetWidth(); break;
		}
		switch (yConstraint.type) {
			case Pixel: y = ((PixelConstraint)yConstraint).pixels; break;
			case Center: y = Application.GetWidth() / 2f; break;
			case Relative: y = ((RelativeConstraint)yConstraint).percentage / 100f * Application.GetHeight(); break;
		}
		parent.position.set(x, y);
		parent.size.set(width, height);
	}

	public void Resize() {
		Apply();
	}
}
