package com.pixelrifts.turtle.engine.utils;

import com.pixelrifts.turtle.engine.rendering.UIRenderer;
import com.pixelrifts.turtle.engine.rendering.renderables.UIComponent;
import com.pixelrifts.turtle.glabs.base.Application;
import org.joml.Vector2f;


public class SceneUI {
	private final UIComponent master;

	public SceneUI() {
		master = new UIComponent(new Vector2f(0, 0), new Vector2f(Application.GetWidth(), Application.GetHeight()));
	}

	public void RegisterComponent(UIComponent ui) {
		master.AddChild(ui);
	}

	public void RenderAll() {
		for (UIComponent child : master.GetChildren())
			Render(child);
	}

	private void Render(UIComponent component) {
		UIRenderer.Submit(component);
		for (UIComponent child : component.GetChildren())
			Render(child);
	}

	public void Resize() {
		master.size.set(Application.GetWidth(), Application.GetHeight());
		master.WindowResize();
	}

	public void Update() {
		master.Update();
	}
}
