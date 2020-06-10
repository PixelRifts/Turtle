package com.pixelrifts.turtle.engine.rendering.renderables;

import com.pixelrifts.turtle.engine.events.EventNode;
import com.pixelrifts.turtle.engine.managers.ResourceManager;
import com.pixelrifts.turtle.engine.utils.Keyframe;
import com.pixelrifts.turtle.engine.utils.Rect;
import com.pixelrifts.turtle.engine.utils.Transform;
import com.pixelrifts.turtle.glabs.objects.Texture;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class Animation {
	private final List<Keyframe> keyframes;
	public transient EventNode frameActuator;

	private String name;
	private double speed;
	private boolean loop;
	private boolean running;
	private Sprite current;
	private int pointer;
	private float timeAcc;

	public Animation(String filepath) {
		frameActuator = new EventNode();
		keyframes = new ArrayList<>();
		name = "";
		speed = 0;
		loop = false;
		running = true;
		pointer = 0;
		timeAcc = 0;
		try {
			Object o = new JSONParser().parse(new FileReader(filepath));
			JSONObject parent = (JSONObject) o;
			this.name = (String) parent.get("name");
			this.loop = parent.get("loop") != null && (boolean) parent.get("loop");
			this.speed = (parent.get("speed") != null ? (double) parent.get("speed") : 0) * 64;
			JSONArray sprites = (JSONArray) parent.get("sprites");

			for (JSONObject spriteDetails : (Iterable<JSONObject>) sprites) {
				Texture texture = ResourceManager.ImportTexture((String) spriteDetails.get("image"));
				Rect uvRect = new Rect((double) spriteDetails.get("x"), (double)spriteDetails.get("y"),
						(double)spriteDetails.get("width"), (double)spriteDetails.get("height"));
				String actuatedTag = spriteDetails.get("actuate") != null ? (String) spriteDetails.get("actuate") : "---";
				keyframes.add(new Keyframe(new Sprite(texture, uvRect), actuatedTag));
			}
		} catch (IOException | ParseException e) {
			assert false : "Exception \n";
			e.printStackTrace();
		}
		current = keyframes.get(0).sprite;
	}

	public void Update(float dt) {
		if (running) {
			timeAcc += dt;

			if (timeAcc >= speed) {
				timeAcc = 0;
				Keyframe frame = keyframes.get(pointer++);
				current = frame.sprite;
				if (!frame.actuatedTag.equals("---")) {
					frameActuator.Dispatch(frame.actuatedTag, null);
				}

				if (pointer >= keyframes.size()) {
					if (loop) {
						pointer = 0;
					} else {
						running = false;
					}
				}
			}
		}
	}

	public void Render(Transform transform) {
		current.Render(transform);
	}
}
