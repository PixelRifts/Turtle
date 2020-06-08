package com.pixelrifts.turtle.engine.components;

import com.pixelrifts.turtle.engine.architecture.Component;
import com.pixelrifts.turtle.engine.rendering.Renderer;
import com.pixelrifts.turtle.engine.utils.Rect;
import com.pixelrifts.turtle.glabs.objects.Texture;
import imgui.ImGui;
import imgui.enums.ImGuiWindowFlags;
import org.joml.Vector4f;

public class SpriteRenderer extends Component {
	private Texture texture;
	private Rect uvRect;
	private Vector4f mixColour;

	public SpriteRenderer() {
		texture = Texture.White;
		uvRect = new Rect(0, 0, 1, 1);
		mixColour = new Vector4f(1, 1, 1, 1);
	}

	public SpriteRenderer(Texture texture, Rect uvRect, Vector4f mixColour) {
		this.texture = texture;
		this.uvRect = uvRect;
		this.mixColour = mixColour;
	}

	public SpriteRenderer WithTexture(Texture texture) {
		this.texture = texture;
		return this;
	}
	public SpriteRenderer WithUVRect(Rect uvRect) {
		this.uvRect = uvRect;
		return this;
	}
	public SpriteRenderer WithMixColour(Vector4f mixColour) {
		this.mixColour = mixColour;
		return this;
	}

	@Override
	public void Render() {
		Renderer.Submit(texture, uvRect, mixColour, gameObject.transform);
	}

	@Override
	public void ImGuiRender() {

	}

	@Override
	public String toString() {
		return "SpriteRenderer(" +
				"texture = " + texture +
				", uvRect = " + uvRect +
				", mixColour = " + mixColour +
		')';
	}
}
