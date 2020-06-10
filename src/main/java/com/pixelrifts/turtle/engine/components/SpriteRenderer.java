package com.pixelrifts.turtle.engine.components;

import com.pixelrifts.turtle.engine.architecture.Component;
import com.pixelrifts.turtle.engine.rendering.Renderer;
import com.pixelrifts.turtle.engine.rendering.renderables.Sprite;
import com.pixelrifts.turtle.engine.utils.Rect;
import com.pixelrifts.turtle.glabs.objects.Texture;
import imgui.ImGui;
import imgui.enums.ImGuiWindowFlags;
import org.joml.Vector4f;

public class SpriteRenderer extends Component {
	private Sprite sprite;

	public SpriteRenderer() {
		this(null);
	}

	public SpriteRenderer(Sprite sprite) {
		super("SpriteRenderer");
		this.sprite = sprite;
	}

	public SpriteRenderer WithSprite(Sprite sprite) {
		this.sprite = sprite;
		return this;
	}

	@Override
	public void Render() {
		sprite.Render(gameObject.transform);
	}
}
