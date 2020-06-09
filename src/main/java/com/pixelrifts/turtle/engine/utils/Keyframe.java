package com.pixelrifts.turtle.engine.utils;

import com.pixelrifts.turtle.engine.rendering.renderables.Sprite;

public class Keyframe {
	public Sprite sprite;
	public String actuatedTag;

	public Keyframe(Sprite sprite, String actuatedTag) {
		this.sprite = sprite;
		this.actuatedTag = actuatedTag;
	}

	public Keyframe(Sprite sprite) {
		this.sprite = sprite;
		this.actuatedTag = "---";
	}
}
