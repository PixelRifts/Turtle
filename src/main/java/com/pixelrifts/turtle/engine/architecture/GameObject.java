package com.pixelrifts.turtle.engine.architecture;

import com.pixelrifts.turtle.engine.utils.Transform;
import com.pixelrifts.turtle.glabs.event.LayerEvent;

import java.util.ArrayList;
import java.util.List;

public class GameObject {
	public final String name;
	public Transform transform;

	public transient GameObject parent;
	public final List<GameObject> children;
	private final List<Component> components;

	public transient final boolean debug;

	public GameObject(String name) {
		this(null, name);
	}

	public GameObject(GameObject parent, String name) {
		this(parent, name, true);
	}

	public GameObject(GameObject parent, String name, boolean debug) {
		this.parent = parent;
		this.name = name;
		this.children = new ArrayList<>();
		this.components = new ArrayList<>();
		this.debug = debug;
		transform = Transform.Identity;
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
		if (debug)
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
