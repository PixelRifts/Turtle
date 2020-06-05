package com.pixelrifts.turtle.glabs.event;

public class MouseButtonEvent extends LayerEvent {
	protected int m_Button;
	protected boolean m_Pressed;

	public MouseButtonEvent(int button, boolean pressed) {
		m_Button = button;
		m_Pressed = pressed;
	}

	public final int GetButton() {
		return m_Button;
	}
	public boolean IsPressed() {
		return m_Pressed;
	}
}
