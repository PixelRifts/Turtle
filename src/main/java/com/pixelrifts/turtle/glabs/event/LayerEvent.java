package com.pixelrifts.turtle.glabs.event;

public class LayerEvent {
	protected boolean m_Handled;

	public boolean IsHandled() {
		return m_Handled;
	}
	
	public void SetHandled(boolean handled) {
		this.m_Handled = handled;
	}
}
