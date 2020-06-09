package com.pixelrifts.turtle.engine.managers;

import com.pixelrifts.turtle.engine.rendering.renderables.Animation;
import com.pixelrifts.turtle.glabs.objects.Texture;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager {
	private static final Map<String, Texture> textures;
	private static final Map<String, Animation> animations;

	static {
		textures = new HashMap<>();
		animations = new HashMap<>();
	}

	public static Texture ImportTexture(String filename) {
		if (textures.containsKey(filename)) {
			return textures.get(filename);
		} else {
			Texture texture = new Texture("src/main/resources/" + filename);
			textures.put(filename, texture);
			return texture;
		}
	}

	public static Animation ImportAnimation(String filename) {
		if (animations.containsKey(filename)) {
			return animations.get(filename);
		} else {
			Animation animation = new Animation("src/main/resources/animations/" + filename + ".tanim");
			animations.put(filename, animation);
			return animation;
		}
	}

	public static void Clean() {
		for (Texture t : textures.values()) {
			t.Delete();
		}
	}
}
