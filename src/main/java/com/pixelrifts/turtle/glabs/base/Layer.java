package com.pixelrifts.turtle.glabs.base;

import com.pixelrifts.turtle.glabs.event.LayerEvent;

public abstract class Layer {
	protected String m_Name;
	
	public Layer(String name) {
		m_Name = name;
	}
	
	public void OnAttach() {}
	public void OnUpdate(float dt) {}
	public void OnRender() {}
	public void OnImGuiRender() {}
	public void OnDetach() {}
	public boolean OnLayerEvent(LayerEvent e) { return false; }
}
