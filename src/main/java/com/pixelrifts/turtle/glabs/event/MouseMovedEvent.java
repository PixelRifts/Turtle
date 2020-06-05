package com.pixelrifts.turtle.glabs.event;

public class MouseMovedEvent extends LayerEvent {
	private final float m_CursorX;
	private final float m_CursorY;

	public MouseMovedEvent(float cursorX, float cursorY) {
		m_CursorX = cursorX;
		m_CursorY = cursorY;
	}
	
	public float GetCursorX() {
		return m_CursorX;
	}
	
	public float GetCursorY() {
		return m_CursorY;
	}
	
	@Override
	public String toString() {
		return "MouseMovedEvent: [MouseX: " + m_CursorX + ", MouseY: " + m_CursorY + "]";
	}
}
