package com.pixelrifts.turtle.engine.rendering;

import com.pixelrifts.turtle.engine.utils.Rect;
import com.pixelrifts.turtle.engine.utils.Transform;
import com.pixelrifts.turtle.glabs.objects.Shader;
import com.pixelrifts.turtle.glabs.objects.Texture;
import org.joml.Vector2f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL45.*;

public class Renderer {
	public static final int BATCH_SIZE = 1000;

	private static final int[] textureSlots = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
	private static final Shader shader;
	static final Camera camera;

	private static final List<RenderBatch> batches;

	static {
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		batches = new ArrayList<>();
		camera = new Camera();

		shader = new Shader("Basic");
		shader.Bind();
		shader.UploadIntArray("u_TextureSlots", textureSlots);
		shader.Unbind();
	}

	public static void Begin() {
		for (RenderBatch b : batches) b.Delete();
		batches.clear();
		batches.add(new RenderBatch(BATCH_SIZE));
		batches.get(batches.size() - 1).Begin();
	}

	public static void DrawTexture(Texture texture, Rect uvRect, Vector4f colour, Transform t) {
		if (batches.get(batches.size() - 1).hasRoom) {
			RenderBatch batch = batches.get(batches.size() - 1);
			batch.AddToBatch(texture, uvRect, colour, t);
		} else {
			RenderBatch batch = new RenderBatch(BATCH_SIZE);
			batch.Begin();
			batch.AddToBatch(texture, uvRect, colour, t);
			batches.add(batch);
		}
	}

	public static void DrawLine(Vector2f start, Vector2f end, float thickness, Vector4f colour) {
		if (batches.get(batches.size() - 1).hasRoom) {
			RenderBatch batch = batches.get(batches.size() - 1);
			batch.AddToBatch(start, end, thickness, colour);
		} else {
			RenderBatch batch = new RenderBatch(BATCH_SIZE);
			batch.Begin();
			batch.AddToBatch(start, end, thickness, colour);
			batches.add(batch);
		}
	}

	public static void End() {
		Flush();
	}

	public static void Flush() {
		shader.Bind();
		shader.UploadMatrix("u_ViewProjection", camera.getViewProjMatrix());
		for (RenderBatch batch : batches) {
			batch.UpdateBuffer();
			batch.Bind();
			glDrawArrays(GL_TRIANGLES, 0, batch.GetVertexCount());
			batch.Unbind();
		}
		shader.Unbind();
	}

	public static void Clean() {
		shader.Delete();
		for (RenderBatch b : batches) b.Delete();
	}
}
