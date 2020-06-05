package com.pixelrifts.turtle.glabs.base;

import com.pixelrifts.turtle.glabs.event.LayerEvent;

public class Application {
	private final LayerStack m_LayerStack;
	private static int m_Width;
	private static int m_Height;
	private final String m_Title;
	
	public Application() {
		m_Width = 1080;
		m_Height = 720;
		m_Title = "Let's go";
		m_LayerStack = new LayerStack();
	}

	public void OnEvent(LayerEvent e) {
		m_LayerStack.PropagateEvent(e);
	}
	
	public void Update(float dt) {
		m_LayerStack.PropagateUpdate(dt);
	}

	public void Render() {
		m_LayerStack.PropagateRender();
	}

	public void ImGuiRender() {
		m_LayerStack.PropagateImGuiRender();
	}

	public static int getWidth() {
		return m_Width;
	}
	
	public static int getHeight() {
		return m_Height;
	}
	
	public String getTitle() {
		return m_Title;
	}

	public void PushLayer(Layer layer) {
		m_LayerStack.PushLayer(layer);
	}
	
	public void PopLayer() {
		m_LayerStack.PopLayer();
	}
}
