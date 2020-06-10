package com.pixelrifts.turtle.engine.architecture;

import com.pixelrifts.turtle.glabs.event.LayerEvent;

public abstract class Component {
	public final String name;
	public transient GameObject gameObject;

	public Component(String name) {
		this.name = name;
	}

	public void Start() {}
	public void Update(float dt) {}
	public void Render() {}
	public void OnLayerEvent(LayerEvent e) {}
	public void Destroy() {}

	public void ImGuiRender() {}
}
