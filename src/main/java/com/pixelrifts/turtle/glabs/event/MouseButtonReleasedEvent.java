package com.pixelrifts.turtle.glabs.event;

public class MouseButtonReleasedEvent extends MouseButtonEvent {
	public MouseButtonReleasedEvent(int button) {
		super(button, false);
	}
	
	@Override
	public String toString() {
		return "MouseButtonReleasedEvent: [Button: " + m_Button + "]";
	}
}
