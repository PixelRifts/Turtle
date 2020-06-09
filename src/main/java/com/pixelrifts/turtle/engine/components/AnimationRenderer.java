package com.pixelrifts.turtle.engine.components;

import com.pixelrifts.turtle.engine.architecture.Component;
import com.pixelrifts.turtle.engine.rendering.renderables.Animation;

public class AnimationRenderer extends Component {
	private Animation animation;

	public AnimationRenderer() {
		this(null);
	}

	public AnimationRenderer(Animation animation) {
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
}
