package com.pixelrifts.turtle.glabs.event;

public class KeyReleasedEvent extends KeyEvent {
	public KeyReleasedEvent(int keycode) {
		super(keycode);
	}
	
	@Override
	public String toString() {
		return "KeyReleasedEvent: [Key: " + m_KeyCode + "]";
	}
}
