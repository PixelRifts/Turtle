package com.pixelrifts.turtle.engine.rendering.renderables;

import com.pixelrifts.turtle.engine.architecture.Component;
import com.pixelrifts.turtle.engine.utils.Rect;
import com.pixelrifts.turtle.glabs.objects.Texture;
import org.joml.Vector2f;
import org.joml.Vector4f;

public abstract class UIComponent {
	protected Vector2f position;
	protected Vector2f size;
	protected Vector4f colour;
	protected Rect uv;
	protected Texture texture;

	public UIComponent(Vector2f position, Vector2f size, Vector4f colour, Rect uv, Texture texture) {
		this.position = position;
		this.size = size;
		this.colour = colour;
		this.uv = uv;
		this.texture = texture;
	}

	public Vector2f GetPosition() {
		return position;
	}
	public Vector2f GetSize() {
		return size;
	}
	public Vector4f GetColour() {
		return colour;
	}
	public Rect GetUVRect() {
		return uv;
	}
	public Texture GetTexture() {
		return texture;
	}
}
