package com.pixelrifts.turtle.engine.events;

@FunctionalInterface
public interface EventListener {
	void OnEvent(EventData data);
}
