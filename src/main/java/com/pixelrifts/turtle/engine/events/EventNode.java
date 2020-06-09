package com.pixelrifts.turtle.engine.events;

import java.util.ArrayList;
import java.util.List;

public class EventNode {
	private final List<String> tags;
	private final List<EventListener> listeners;

	public EventNode() {
		this.tags = new ArrayList<>();
		this.listeners = new ArrayList<>();
	}

	public void Dispatch(String tag, EventData data) {
		for (int i = 0; i < tags.size(); i++) {
			if (tags.get(i).equals(tag)) {
				listeners.get(i).OnEvent(data);
			}
		}
	}

	public void AddListener(String tag, EventListener listener) {
		tags.add(tag);
		listeners.add(listener);
	}
}
