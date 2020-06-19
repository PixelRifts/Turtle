package com.pixelrifts.turtle.engine.rendering;

import com.pixelrifts.turtle.engine.rendering.renderables.UIComponent;
import com.pixelrifts.turtle.engine.utils.Rect;
import com.pixelrifts.turtle.glabs.objects.Texture;
import org.joml.Vector2f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL45.*;

public class UIRenderBatch {
	private static final int POS_COUNT = 2;
	private static final int UV_COUNT = 2;
	private static final int COLOUR_COUNT = 4;
	private static final int TEX_INDEX_COUNT = 1;
	private static final int ROUNDING_COUNT = 1;
	private static final int DIMENSIONS_COUNT = 2;

	private static final int POS_OFFSET = 0;
	private static final int UV_OFFSET = POS_OFFSET + POS_COUNT * Float.BYTES;
	private static final int COLOUR_OFFSET = UV_OFFSET + UV_COUNT * Float.BYTES;
	private static final int TEX_INDEX_OFFSET = COLOUR_OFFSET + COLOUR_COUNT * Float.BYTES;
	private static final int ROUNDING_OFFSET = TEX_INDEX_OFFSET + TEX_INDEX_COUNT * Float.BYTES;
	private static final int DIMENSIONS_OFFSET = ROUNDING_OFFSET + ROUNDING_COUNT * Float.BYTES;

	private static final int VERTEX_COUNT = POS_COUNT + UV_COUNT + COLOUR_COUNT + TEX_INDEX_COUNT + ROUNDING_COUNT + DIMENSIONS_COUNT;
	private static final int VERTEX_SIZE = VERTEX_COUNT * Float.BYTES;

	private final int vao;
	private final int vbo;

	private float[] data;
	private final List<Texture> textures;

	public boolean hasRoom;

	private int componentCount;
	private int textureIndex;
	private final int maxBatchSize;

	public UIRenderBatch(int maxBatchSize) {
		this.maxBatchSize = maxBatchSize;
		textures = new ArrayList<>();
		vao = glCreateVertexArrays();
		glBindVertexArray(vao);

		hasRoom = true;
		vbo = glCreateBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, maxBatchSize * 6 * VERTEX_SIZE, GL_DYNAMIC_DRAW);

		glVertexAttribPointer(0, POS_COUNT, GL_FLOAT, false, VERTEX_SIZE, POS_OFFSET);
		glEnableVertexArrayAttrib(vao, 0);
		glVertexAttribPointer(1, UV_COUNT, GL_FLOAT, false, VERTEX_SIZE, UV_OFFSET);
		glEnableVertexArrayAttrib(vao, 1);
		glVertexAttribPointer(2, COLOUR_COUNT, GL_FLOAT, false, VERTEX_SIZE, COLOUR_OFFSET);
		glEnableVertexArrayAttrib(vao, 2);
		glVertexAttribPointer(3, TEX_INDEX_COUNT, GL_FLOAT, false, VERTEX_SIZE, TEX_INDEX_OFFSET);
		glEnableVertexArrayAttrib(vao, 3);
		glVertexAttribPointer(4, ROUNDING_COUNT, GL_FLOAT, false, VERTEX_SIZE, ROUNDING_OFFSET);
		glEnableVertexArrayAttrib(vao, 4);
		glVertexAttribPointer(5, DIMENSIONS_COUNT, GL_FLOAT, false, VERTEX_SIZE, DIMENSIONS_OFFSET);
		glEnableVertexArrayAttrib(vao, 5);
	}

	public void Begin() {
		componentCount = 0;
		data = new float[maxBatchSize * 6 * VERTEX_COUNT];
		textureIndex = 0;
	}

	public void AddToBatch(UIComponent ui) {
		if (hasRoom) {
			Texture texture = ui.GetTexture();
			int texIndex;

			if (textures.contains(texture)) {
				texIndex = textures.indexOf(texture);
			} else {
				textures.add(texture);
				texIndex = textureIndex++;
				if (texture.GetBoundSlot() != -1) texIndex = texture.GetBoundSlot();
			}

			int index = componentCount++;
			int offset = index * 6 * VERTEX_COUNT;

			StoreData(offset, ui.GetPosition(), ui.GetSize(), ui.GetUVRect(), ui.GetColour(), texIndex, ui.GetRounding());

			if (textureIndex >= 16) hasRoom = false;
			if (componentCount >= maxBatchSize) hasRoom = false;
		}
	}

	private void StoreData(int offset, Vector2f position, Vector2f size, Rect uvRect, Vector4f colour, int texIndex, float rounding) {
		StoreVertex(offset, position.x, position.y + size.y, uvRect.x, uvRect.y, colour.x, colour.y, colour.z, colour.w, texIndex, rounding, size.x, size.y);
		StoreVertex(offset + VERTEX_COUNT, position.x + size.x, position.y + size.y, uvRect.x + uvRect.width, uvRect.y, colour.x, colour.y, colour.z, colour.w, texIndex, rounding, size.x, size.y);
		StoreVertex(offset + VERTEX_COUNT * 2, position.x + size.x, position.y, uvRect.x + uvRect.width, uvRect.y + uvRect.height, colour.x, colour.y, colour.z, colour.w, texIndex, rounding, size.x, size.y);
		StoreVertex(offset + VERTEX_COUNT * 3, position.x, position.y + size.y, uvRect.x, uvRect.y, colour.x, colour.y, colour.z, colour.w, texIndex, rounding, size.x, size.y);
		StoreVertex(offset + VERTEX_COUNT * 4, position.x + size.x, position.y, uvRect.x + uvRect.width, uvRect.y + uvRect.height, colour.x, colour.y, colour.z, colour.w, texIndex, rounding, size.x, size.y);
		StoreVertex(offset + VERTEX_COUNT * 5, position.x, position.y, uvRect.x, uvRect.y + uvRect.height, colour.x, colour.y, colour.z, colour.w, texIndex, rounding, size.x, size.y);
	}

	private void StoreVertex(int offset, float x, float y, float u, float v, float r, float g, float b, float a, float t, float rn, float sx, float sy) {
		data[offset] = x;
		data[offset + 1] = y;
		data[offset + 2] = u;
		data[offset + 3] = v;
		data[offset + 4] = r;
		data[offset + 5] = g;
		data[offset + 6] = b;
		data[offset + 7] = a;
		data[offset + 8] = t;
		data[offset + 9] = rn;
		data[offset + 10] = sx;
		data[offset + 11] = sy;
	}

	public void UpdateBuffer() {
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferSubData(GL_ARRAY_BUFFER, 0, data);
	}

	public void Bind() {
		glBindVertexArray(vao);
		for (int i = 0; i < textures.size(); i++)
			textures.get(i).BindSlot(i);
	}

	public void Unbind() {
		for (Texture texture : textures)
			texture.Unbind();
		glBindVertexArray(0);
	}

	public void Delete() {
		glDeleteBuffers(vbo);
		glDeleteVertexArrays(vao);
	}

	public int GetVertexCount() {
		return componentCount * 6;
	}
}
