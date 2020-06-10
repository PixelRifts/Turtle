package com.pixelrifts.turtle.engine.components;

import com.pixelrifts.turtle.engine.architecture.Component;
import com.pixelrifts.turtle.engine.rendering.renderables.Animation;
import imgui.ImGui;

public class AnimationRenderer extends Component {
	public Animation animation;

	public AnimationRenderer() {
		this(null);
	}

	public AnimationRenderer(Animation animation) {
		super("AnimationRenderer");
		this.animation = animation;
	}

	public AnimationRenderer WithAnimation(Animation sprite) {
		this.animation = sprite;
		return this;
	}

	@Override
	public void Update(float dt) {
		animation.Update(dt);
	}

	@Override
	public void Render() {
		animation.Render(gameObject.transform);
	}

	@Override
	public void ImGuiRender() {
		super.ImGuiRender();
		ImGui.begin(gameObject.name + " >> Animation Renderer");
		ImGui.textColored(0.8f, 0.2f, 0.3f, 1.0f, "ColouredText");
		ImGui.end();
	}
}
