package com.pixelrifts.turtle.engine.rendering.renderables;

import com.pixelrifts.turtle.engine.rendering.Renderer;
import com.pixelrifts.turtle.engine.utils.Rect;
import com.pixelrifts.turtle.engine.utils.Transform;
import com.pixelrifts.turtle.glabs.objects.Texture;
import org.joml.Vector4f;

public class Sprite {
	private transient Texture texture;
	private Rect uvRect;
	private Vector4f mixColour;

	public Sprite() {
		this(Texture.White, new Rect(0, 0, 1, 1));
	}

	public Sprite(Texture texture, Rect uvRect) {
		this(texture, uvRect, new Vector4f(1, 1, 1, 1));
	}

	public Sprite(Texture texture, Rect uvRect, Vector4f mixColour) {
		this.texture = texture;
		this.uvRect = uvRect;
		this.mixColour = mixColour;
	}

	public Sprite WithTexture(Texture texture) {
		this.texture = texture;
		return this;
	}
	public Sprite WithUVRect(Rect uvRect) {
		this.uvRect = uvRect;
		return this;
	}
	public Sprite WithMixColour(Vector4f mixColour) {
		this.mixColour = mixColour;
		return this;
	}

	public void Render(Transform transform) {
		Renderer.Submit(texture, uvRect, mixColour, transform);
	}

	@Override
	public String toString() {
		return "Sprite(" +
				"texture = " + texture +
				", uvRect = " + uvRect +
				", mixColour = " + mixColour +
				')';
	}
}
