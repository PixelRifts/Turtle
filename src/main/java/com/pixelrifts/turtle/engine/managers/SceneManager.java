package com.pixelrifts.turtle.engine.managers;

import com.pixelrifts.turtle.engine.architecture.Scene;
import com.pixelrifts.turtle.glabs.event.LayerEvent;

import java.util.HashMap;
import java.util.Map;

public class SceneManager {
	private static final Map<Integer, Scene> scenes;
	private static Scene currentScene;

	static {
		scenes = new HashMap<>();
	}

	public static void RegisterScene(int id, Scene scene) {
		scenes.put(id, scene);
	}

	public static void SetScene(int id) {
		if (currentScene != null)
			currentScene.EndScene();
		currentScene = scenes.get(id);
		currentScene.StartScene();
	}

	public static void Update(float dt) {
		currentScene.Update(dt);
	}

	public static void Render() {
		currentScene.Render();
	}

	public static void RenderUI() {
		currentScene.RenderUI();
	}

	public static void ImGuiRender() {
		currentScene.ImGuiRender();
	}

	public static void OnLayerEvent(LayerEvent e) {
		currentScene.OnLayerEvent(e);
	}

	public static void EndScenes() {
		currentScene.EndScene();
	}
}
