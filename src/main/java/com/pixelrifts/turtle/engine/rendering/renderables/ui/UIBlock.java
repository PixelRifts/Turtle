package com.pixelrifts.turtle.engine.rendering.renderables.ui;


import com.pixelrifts.turtle.engine.rendering.renderables.UIComponent;
import com.pixelrifts.turtle.engine.utils.Rect;
import com.pixelrifts.turtle.glabs.objects.Texture;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class UIBlock extends UIComponent {
	public UIBlock(Vector2f position, Vector2f size, Vector4f colour) {
		super(position, size, colour, new Rect(0, 0, 1, 1), Texture.White);
	}
}
