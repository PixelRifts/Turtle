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

	private final float[] x = new float[1];
	private final float[] y = new float[1];
	private final float[] colour = new float[3];

	public SpriteRenderer() {
		texture = Texture.White;
		uvRect = new Rect(0, 0, 1, 1);
		mixColour = new Vector4f(1, 1, 1, 1);
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
	public void Start() {
	}

	@Override
	public void Render() {
		Renderer.Submit(texture, uvRect, mixColour, gameObject.transform);
	}

	@Override
	public void ImGuiRender() {
		ImGui.begin("Transform");
		if (ImGui.dragFloat("x", x)) gameObject.transform.SetTranslation(x[0], 0);
		if (ImGui.dragFloat("y", y)) gameObject.transform.SetTranslation(0, y[0]);
		if (ImGui.colorPicker3("colour:", colour)) mixColour.set(colour[0], colour[1], colour[2], 1.0f);
		ImGui.end();
	}
}
