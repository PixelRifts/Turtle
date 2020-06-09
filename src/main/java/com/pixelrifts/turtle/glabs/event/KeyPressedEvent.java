package com.pixelrifts.turtle.glabs.event;

public class KeyPressedEvent extends KeyEvent {
	private final int m_RepeatCount;
	
	public KeyPressedEvent(int keyCode, int repeatCount) {
		super(keyCode);
		this.m_RepeatCount = repeatCount;
	}

	public int GetRepeatCount() {
		return m_RepeatCount;
	}
	
	@Override
	public String toString() {
		return "KeyPressedEvent: [Key: " + m_KeyCode + ", Repeated: " + (m_RepeatCount == 1) + "]";
	}
}
