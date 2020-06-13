package com.pixelrifts.turtle.engine.utils;

import com.pixelrifts.turtle.engine.rendering.UIRenderer;
import com.pixelrifts.turtle.engine.rendering.renderables.UIComponent;

import java.util.ArrayList;
import java.util.List;

public class SceneUI {
	private final List<UIComponent> uiComponents;

	public SceneUI() {
		uiComponents = new ArrayList<>();
	}

	public void Clear() {
		uiComponents.clear();
	}

	public void RegisterComponent(UIComponent ui) {
		uiComponents.add(ui);
	}

	public void RenderAll() {
		UIRenderer.Begin();
		for (UIComponent ui : uiComponents)
			UIRenderer.Submit(ui);
		UIRenderer.End();
	}
}
