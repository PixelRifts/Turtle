package com.pixelrifts.turtle.glabs.event;

public class MouseScrolledEvent extends LayerEvent {
	private final float m_XScroll;
	private final float m_YScroll;

	public MouseScrolledEvent(float xScroll, float yScroll) {
		m_XScroll = xScroll;
		m_YScroll = yScroll;
	}

	public float getXScroll() {
		return m_XScroll;
	}
	public float getYScroll() {
		return m_YScroll;
	}
	
	@Override
	public String toString() {
		return "MouseScrolledEvent: [XScroll: " + m_XScroll + ", YScroll: " + m_YScroll + "]";
	}
}
