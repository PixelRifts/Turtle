package com.pixelrifts.turtle.engine.architecture;

import com.pixelrifts.turtle.glabs.event.LayerEvent;

import java.util.ArrayList;
import java.util.List;

public class GameObject {
	private final String name;

	private GameObject parent;
	private final List<Component> components;
	private final List<GameObject> children;

	public GameObject(String name) {
		this(null, name);
	}

	public GameObject(GameObject parent, String name) {
		this.parent = parent;
		this.name = name;
		components = new ArrayList<>();
		children = new ArrayList<>();
	}

	public void AttachComponent(Component c) {
		components.add(c);
		c.gameObject = this;
	}

	public <T extends Component> T GetComponent(Class<T> clazz) {
		for (Component c : components) {
			if (clazz.isAssignableFrom(c.getClass())) return clazz.cast(c);
		}
		return null;
	}

	public <T extends Component> void DetachComponent(Class<T> componentClass) {
		for (int i = 0; i < components.size(); i++) {
			Component c = components.get(i);
			if (componentClass.isAssignableFrom(c.getClass())) {
				c.Destroy();
				components.remove(c);
				return;
			}
		}
	}

	public void Init() {
		for (Component component : components) {
			component.Start();
		}
	}

	public void OnLayerEvent(LayerEvent e) {
		for (Component component : components) {
			component.OnLayerEvent(e);
		}
	}

	public void Update(float dt) {
		for (Component component : components) {
			component.Update(dt);
		}
	}

	public void Render() {
		for (Component component : components) {
			component.Render();
		}
	}

	public void ImGuiRender() {
		for (Component component : components) {
			component.ImGuiRender();
		}
	}

	public void Destroy() {
		for (Component component : components) {
			component.Destroy();
		}
	}
}
