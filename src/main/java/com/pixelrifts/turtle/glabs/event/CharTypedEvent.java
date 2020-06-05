package com.pixelrifts.turtle.glabs.event;

public class CharTypedEvent extends LayerEvent {
	private final int character;

	public CharTypedEvent(int character) {
		this.character = character;
	}

	public int GetCharacter() {
		return character;
	}
}
