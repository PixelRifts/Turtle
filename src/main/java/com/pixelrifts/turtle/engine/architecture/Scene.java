package com.pixelrifts.turtle.engine.architecture;

import com.pixelrifts.turtle.engine.utils.SceneUI;
import com.pixelrifts.turtle.glabs.event.LayerEvent;
import com.pixelrifts.turtle.glabs.event.WindowResizedEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
	public final String name;

	protected final List<GameObject> objects;
	protected boolean running;
	protected SceneUI uiRegistry;

	public Scene(String name) {
		this.name = name;
		this.objects = new ArrayList<>();
		running = false;
		uiRegistry = new SceneUI();
	}

	public void AddGameObjectToScene(GameObject o) {
		objects.add(o);
		o.added = true;
		if (running) o.Init();
	}

	public void StartScene() {
		if (running) return;
		running = true;
		for (GameObject o : objects) {
			o.Init();
		}
	}

	public void Update(float dt) {
		for (GameObject o : objects) {
			o.Update(dt);
		}
		uiRegistry.Update();
	}

	public void Render() {
		for (GameObject o : objects) {
			o.Render();
		}
	}

	public void RenderUI() {
		uiRegistry.RenderAll();
	}

	public void ImGuiRender() {
		for (GameObject o : objects) {
			o.ImGuiRender();
		}
	}

	public void OnLayerEvent(LayerEvent e) {
		if (e instanceof WindowResizedEvent)
			uiRegistry.Resize();
		for (GameObject o : objects) {
			o.OnLayerEvent(e);
		}
	}

	public void EndScene() {
		if (!running) return;
		running = false;
		for (GameObject o : objects) {
			o.Destroy();
		}
	}
}
