package com.pixelrifts.turtle.engine.architecture;

import com.pixelrifts.turtle.glabs.event.LayerEvent;

public abstract class Component {
	public transient GameObject gameObject;

	public void Start() {}
	public void Update(float dt) {}
	public void Render() {}
	public void OnLayerEvent(LayerEvent e) {}
	public void ImGuiRender() {}
	public void Destroy() {}
}
