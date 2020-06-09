package com.pixelrifts.turtle.engine.managers;

import com.pixelrifts.turtle.glabs.objects.Texture;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager {
	private static final Map<String, Texture> textures;

	static {
		textures = new HashMap<>();
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

	public static void Clean() {
		for (Texture t : textures.values()) {
			t.Delete();
		}
	}
}
