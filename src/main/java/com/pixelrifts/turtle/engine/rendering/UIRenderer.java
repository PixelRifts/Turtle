package com.pixelrifts.turtle.engine.rendering;

import com.pixelrifts.turtle.engine.rendering.renderables.UIComponent;
import com.pixelrifts.turtle.glabs.base.Application;
import com.pixelrifts.turtle.glabs.objects.Shader;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;

public class UIRenderer {
	public static final int BATCH_SIZE = 50;

	private static final int[] textureSlots = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
	private static final Shader shader;

	private static final Matrix4f projection;
	private static final List<UIRenderBatch> batches;

	static {
		shader = new Shader("Ui");
		projection = new Matrix4f().identity();
		projection.ortho(0, Application.GetWidth(), Application.GetHeight(), 0, -1, 1);

		batches = new ArrayList<>();
		batches.add(new UIRenderBatch(BATCH_SIZE));

		shader.Bind();
		shader.UploadMatrix("u_Projection", projection);
		shader.UploadIntArray("u_TextureSlots", textureSlots);
		shader.Unbind();
	}

	public static void Begin() {
		for (UIRenderBatch b : batches) b.Delete();
		batches.clear();
		batches.add(new UIRenderBatch(BATCH_SIZE));
		batches.get(batches.size() - 1).Begin();
	}

	public static void Submit(UIComponent ui) {
		if (batches.get(batches.size() - 1).hasRoom) {
			UIRenderBatch batch = batches.get(batches.size() - 1);
			batch.AddToBatch(ui);
		} else {
			UIRenderBatch batch = new UIRenderBatch(BATCH_SIZE);
			batch.Begin();
			batch.AddToBatch(ui);
			batches.add(batch);
		}
	}

	public static void Resize() {
		projection.identity();
		projection.ortho(0, Application.GetWidth(), Application.GetHeight(), 0, -1, 1);
	}

	public static void End() {
		Flush();
	}

	public static void Flush() {
		shader.Bind();
		for (UIRenderBatch batch : batches) {
			batch.Bind();
			batch.UpdateBuffer();
			glDrawArrays(GL_TRIANGLES, 0, batch.GetVertexCount());
			batch.Unbind();
		}
		shader.Unbind();
	}

	public static void Clean() {
		shader.Delete();
		for (UIRenderBatch b : batches) b.Delete();
	}
}
