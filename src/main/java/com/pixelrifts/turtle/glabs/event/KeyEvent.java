package com.pixelrifts.turtle.glabs.event;

public class KeyEvent extends LayerEvent {
	protected final int m_KeyCode;
	
	public KeyEvent(int keycode) {
		m_KeyCode = keycode;
	}

	public final int GetKeyCode() {
		return m_KeyCode;
	}
}
