package com.pixelrifts.turtle.engine.rendering.renderables.ui;


import com.pixelrifts.turtle.engine.rendering.renderables.UIComponent;
import com.pixelrifts.turtle.engine.utils.Rect;
import com.pixelrifts.turtle.glabs.objects.Texture;
import org.joml.Vector4f;

public class UITexture extends UIComponent {
	public UITexture(Texture texture) {
		this(new Rect(0, 0, 1, 1), texture);
	}
	public UITexture(Texture texture, float rounding) {
		this(new Rect(0, 0, 1, 1), texture, rounding);
	}
	public UITexture(Rect uv, Texture texture) {
		this(uv, texture, 0);
	}
	public UITexture(Rect uv, Texture texture, float rounding) {
		super(new Vector4f(1, 1, 1, 1), uv, texture, rounding);
	}
}
