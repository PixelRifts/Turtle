package com.pixelrifts.turtle.engine.rendering.renderables.ui;


import com.pixelrifts.turtle.engine.rendering.renderables.UIComponent;
import com.pixelrifts.turtle.engine.utils.Rect;
import com.pixelrifts.turtle.glabs.objects.Texture;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class UITexture extends UIComponent {
	public UITexture(Vector2f position, Vector2f size, Texture texture) {
		this(position, size, new Rect(0, 0, 1, 1), texture);
	}

	public UITexture(Vector2f position, Vector2f size, Texture texture, float rounding) {
		this(position, size, new Rect(0, 0, 1, 1), texture, rounding);
	}

	public UITexture(Vector2f position, Vector2f size, Rect uv, Texture texture) {
		this(position, size, uv, texture, 0);
	}

	public UITexture(Vector2f position, Vector2f size, Rect uv, Texture texture, float rounding) {
		super(position, size, new Vector4f(1, 1, 1, 1), uv, texture, rounding);
	}
}
