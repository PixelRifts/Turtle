package com.pixelrifts.turtle.glabs.event;

public class WindowResizedEvent extends LayerEvent {
	private final int m_Width;
	private final int m_Height;

	public WindowResizedEvent(int width, int height) {
		this.m_Width = width;
		this.m_Height = height;
	}

	public final int GetWidth() {
		return m_Width;
	}
	public final int GetHeight() {
		return m_Height;
	}
	
	@Override
	public String toString() {
		return "WindowResizedEvent: [Width: " + m_Width + ", Height: " + m_Height + "]";
	}
}
