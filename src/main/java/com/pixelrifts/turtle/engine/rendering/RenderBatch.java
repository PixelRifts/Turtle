package com.pixelrifts.turtle.engine.rendering;

import com.pixelrifts.turtle.engine.utils.Rect;
import com.pixelrifts.turtle.engine.utils.Transform;
import com.pixelrifts.turtle.glabs.objects.Texture;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL45.*;

public class RenderBatch {
	private static final Vector4f[] BASE_VERTEX_POSITIONS = new Vector4f[] {
			new Vector4f(-0.5f, -0.5f, 0.0f, 1.0f),
			new Vector4f( 0.5f, -0.5f, 0.0f, 1.0f),
			new Vector4f( 0.5f,  0.5f, 0.0f, 1.0f),
			new Vector4f(-0.5f, -0.5f, 0.0f, 1.0f),
			new Vector4f( 0.5f,  0.5f, 0.0f, 1.0f),
			new Vector4f(-0.5f,  0.5f, 0.0f, 1.0f),
	};

	private static final int POS_SIZE = 2;
	private static final int UV_SIZE = 2;
	private static final int COLOUR_SIZE = 4;
	private static final int TEXINDEX_SIZE = 1;

	private static final int POS_OFFSET = 0;
	private static final int UV_OFFSET = POS_OFFSET + POS_SIZE * Float.BYTES;
	private static final int COLOUR_OFFSET = UV_OFFSET + UV_SIZE * Float.BYTES;
	private static final int TEXINDEX_OFFSET = COLOUR_OFFSET + COLOUR_SIZE * Float.BYTES;

	private static final int VERTEX_COUNT = 9;
	private static final int VERTEX_SIZE = VERTEX_COUNT * Float.BYTES;
	private static List<Texture> textures;

	public boolean hasRoom;

	private float[] data;
	private int spriteCount;
	private int textureIndex;
	private final int maxBatchSize;

	private final int vbo;
	private final int vao;

	public RenderBatch(int maxBatchSize) {
		this.maxBatchSize = maxBatchSize;
		spriteCount = 0;
		hasRoom = true;
		textureIndex = 0;
		textures = new ArrayList<>();

		vao = glCreateVertexArrays();
		glBindVertexArray(vao);
		vbo = glCreateBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, maxBatchSize * 6 * VERTEX_SIZE, GL_DYNAMIC_DRAW);

		glVertexAttribPointer(0, POS_SIZE, GL_FLOAT, false, VERTEX_SIZE, POS_OFFSET);
		glEnableVertexArrayAttrib(vao, 0);
		glVertexAttribPointer(1, UV_SIZE, GL_FLOAT, false, VERTEX_SIZE, UV_OFFSET);
		glEnableVertexArrayAttrib(vao, 1);
		glVertexAttribPointer(2, COLOUR_SIZE, GL_FLOAT, false, VERTEX_SIZE, COLOUR_OFFSET);
		glEnableVertexArrayAttrib(vao, 2);
		glVertexAttribPointer(3, TEXINDEX_SIZE, GL_FLOAT, false, VERTEX_SIZE, TEXINDEX_OFFSET);
		glEnableVertexArrayAttrib(vao, 3);
	}

	public void Begin() {
		spriteCount = 0;
		data = new float[maxBatchSize * 6 * VERTEX_COUNT];
		textureIndex = 0;
	}

	public void AddToBatch(Texture texture, Rect uvRect, Vector4f colour, Transform t) {
		if (hasRoom) {
			int texIndex;

			if (textures.contains(texture)) {
				texIndex = textures.indexOf(texture);
			} else {
				textures.add(texture);
				texIndex = textureIndex++;
				if (texture.GetBoundSlot() != -1) texIndex = texture.GetBoundSlot();
			}

			Vector4f[] vertices = new Vector4f[] {
					BASE_VERTEX_POSITIONS[0].mul(t.ToMatrix(), new Vector4f()),
					BASE_VERTEX_POSITIONS[1].mul(t.ToMatrix(), new Vector4f()),
					BASE_VERTEX_POSITIONS[2].mul(t.ToMatrix(), new Vector4f()),
					BASE_VERTEX_POSITIONS[3].mul(t.ToMatrix(), new Vector4f()),
					BASE_VERTEX_POSITIONS[4].mul(t.ToMatrix(), new Vector4f()),
					BASE_VERTEX_POSITIONS[5].mul(t.ToMatrix(), new Vector4f()),
			};

			int index = spriteCount++;
			int offset = index * 6 * VERTEX_COUNT;

			StoreData(offset, vertices, texIndex, uvRect, colour);

			if (textureIndex >= 16) hasRoom = false;
			if (spriteCount >= maxBatchSize) hasRoom = false;
		}
	}

	private void StoreData(int offset, Vector4f[] vertexPositions, int textureIndex, Rect uvRect, Vector4f colour) {
		StoreVertex(offset, vertexPositions[0].x, vertexPositions[0].y, uvRect.x, uvRect.y, colour.x, colour.y, colour.z, colour.w, textureIndex);
		StoreVertex(offset + VERTEX_COUNT, vertexPositions[1].x, vertexPositions[1].y, uvRect.x + uvRect.width, uvRect.y, colour.x, colour.y, colour.z, colour.w, textureIndex);
		StoreVertex(offset + VERTEX_COUNT * 2, vertexPositions[2].x, vertexPositions[2].y, uvRect.x + uvRect.width, uvRect.y + uvRect.height, colour.x, colour.y, colour.z, colour.w, textureIndex);
		StoreVertex(offset + VERTEX_COUNT * 3, vertexPositions[3].x, vertexPositions[3].y, uvRect.x, uvRect.y, colour.x, colour.y, colour.z, colour.w, textureIndex);
		StoreVertex(offset + VERTEX_COUNT * 4, vertexPositions[4].x, vertexPositions[4].y, uvRect.x + uvRect.width, uvRect.y + uvRect.height, colour.x, colour.y, colour.z, colour.w, textureIndex);
		StoreVertex(offset + VERTEX_COUNT * 5, vertexPositions[5].x, vertexPositions[5].y, uvRect.x, uvRect.y + uvRect.height, colour.x, colour.y, colour.z, colour.w, textureIndex);
	}

	private void StoreVertex(int offset, float x, float y, float u, float v, float r, float g, float b, float a, int t) {
		data[offset] = x;
		data[offset + 1] = y;
		data[offset + 2] = u;
		data[offset + 3] = v;
		data[offset + 4] = r;
		data[offset + 5] = g;
		data[offset + 6] = b;
		data[offset + 7] = a;
		data[offset + 8] = t;
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
		return spriteCount * 6;
	}
}
