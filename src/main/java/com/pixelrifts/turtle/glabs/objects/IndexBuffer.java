package com.pixelrifts.turtle.glabs.objects;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL45.*;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class IndexBuffer {
	private final int m_RendererID;
	
	public IndexBuffer() {
		m_RendererID = glCreateBuffers();
	}
	
	public void Bind() {
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, m_RendererID);
	}
	public void Unbind() {
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, m_RendererID);
	}
	public void Delete() {
		glDeleteBuffers(m_RendererID);
	}
	
	public void SetData(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
	}
}
