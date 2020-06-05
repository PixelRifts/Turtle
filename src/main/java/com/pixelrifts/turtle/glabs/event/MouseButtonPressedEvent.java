package com.pixelrifts.turtle.glabs.event;

public class MouseButtonPressedEvent extends MouseButtonEvent {
	public MouseButtonPressedEvent(int button) {
		super(button, true);
	}
	
	@Override
	public String toString() {
		return "MouseButtonPressedEvent: [Button: " + m_Button + "]";
	}
}
