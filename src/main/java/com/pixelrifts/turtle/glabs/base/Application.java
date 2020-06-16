package com.pixelrifts.turtle.glabs.base;

import com.pixelrifts.turtle.glabs.event.LayerEvent;
import com.pixelrifts.turtle.glabs.event.WindowResizedEvent;

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
		if (e instanceof WindowResizedEvent) {
			WindowResizedEvent r = (WindowResizedEvent) e;
			m_Width = r.GetWidth();
			m_Height = r.GetHeight();
		}
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

	public static int GetWidth() {
		return m_Width;
	}
	
	public static int GetHeight() {
		return m_Height;
	}
	
	public String GetTitle() {
		return m_Title;
	}

	public void PushLayer(Layer layer) {
		m_LayerStack.PushLayer(layer);
	}
	
	public void PopLayer() {
		m_LayerStack.PopLayer();
	}
}
