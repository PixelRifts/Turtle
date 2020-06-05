package com.pixelrifts.turtle.glabs.base;

import com.pixelrifts.turtle.glabs.event.LayerEvent;

import java.util.ArrayList;

@SuppressWarnings("all")
public class LayerStack {
	private final ArrayList<Layer> m_Layers;

	public LayerStack() {
		m_Layers = new ArrayList<>();
	}

	public void PushLayer(Layer layer) {
		m_Layers.add(layer);
		layer.OnAttach();
	}

	public Layer PopLayer() {
		m_Layers.get(m_Layers.size() - 1).OnDetach();
		return m_Layers.remove(m_Layers.size() - 1);
	}

	public void PropagateUpdate(float dt) {
		for (Layer layer : m_Layers) {
			layer.OnUpdate(dt);
		}
	}

	public void PropagateImGuiRender() {
		for (Layer layer : m_Layers) {
			layer.OnImGuiRender();
		}
	}

	public void PropagateEvent(LayerEvent e) {
		for (int i = m_Layers.size() - 1; i >= 0; i--) {
			if (e.IsHandled()) break;
			e.SetHandled(m_Layers.get(i).OnLayerEvent(e));
		}
	}

	public void PropagateRender() {
		for (Layer layer : m_Layers) {
			layer.OnRender();
		}
	}
}
