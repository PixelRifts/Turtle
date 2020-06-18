package com.pixelrifts.turtle.engine.rendering.renderables.ui;


import com.pixelrifts.turtle.engine.rendering.renderables.UIComponent;
import com.pixelrifts.turtle.engine.utils.Rect;
import com.pixelrifts.turtle.glabs.objects.Texture;
import org.joml.Vector4f;

public class UIBlock extends UIComponent {
	public UIBlock(Vector4f colour) {
		super(colour, new Rect(0, 0, 1, 1), Texture.White, 0);
	}

	public UIBlock(Vector4f colour, float rounding) {
		super(colour, new Rect(0, 0, 1, 1), Texture.White, rounding);
	}
}
