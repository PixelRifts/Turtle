package com.pixelrifts.turtle.engine.rendering.renderables;

import com.pixelrifts.turtle.engine.utils.Rect;
import com.pixelrifts.turtle.engine.utils.ui.UIConstraints;
import com.pixelrifts.turtle.glabs.event.WindowResizedEvent;
import com.pixelrifts.turtle.glabs.objects.Texture;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.joml.Vector2f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

public class UIComponent {
	private UIComponent parent;
	private final List<UIComponent> children;

	public Vector2f position;
	public Vector2f size;
	protected Vector4f colour;
	protected Rect uv;
	protected Texture texture;
	protected UIConstraints constraints;
	protected float rounding;

	protected UIComponent(Vector2f position, Vector2f size, Vector4f colour, Rect uv, Texture texture, float rounding) {
		this.position = position;
		this.size = size;
		this.colour = colour;
		this.uv = uv;
		this.texture = texture;
		this.rounding = rounding;
		children = new ArrayList<>();
	}

	public UIComponent(Vector2f position, Vector2f size) {
		this.position = position;
		this.size = size;
		children = new ArrayList<>();
	}

	public void ApplyConstraints(UIConstraints constraints) {
		this.constraints = constraints;
		constraints.SetParent(this);
	}

	public void AddChild(UIComponent child) {
		child.parent = this;
		children.add(child);
		child.WindowResize();
	}

	public List<UIComponent> GetChildren() {
		return children;
	}

	public Vector2f GetPosition() {
		if (parent != null) return parent.position.add(position, new Vector2f());
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
	public float GetRounding() {
		return rounding;
	}
	public void WindowResize() {
		if (constraints != null) constraints.Resize();
		for (UIComponent child : children) child.WindowResize();
	}

	public void SetPosition(Vector2f position) {
		this.position = position;
	}
	public void SetSize(Vector2f size) {
		this.size = size;
	}
}
