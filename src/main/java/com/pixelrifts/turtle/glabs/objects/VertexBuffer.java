package com.pixelrifts.turtle.glabs.objects;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL45.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class VertexBuffer {
	private int m_RendererID;
	private BufferLayout m_Layout;
	
	public VertexBuffer() {
		m_RendererID = glCreateBuffers();
	}
	
	public void Bind() {
		glBindBuffer(GL_ARRAY_BUFFER, m_RendererID);
	}
	
	public void Unbind() {
		glBindBuffer(GL_ARRAY_BUFFER, m_RendererID);
	}
	
	public void Delete() {
		glDeleteBuffers(m_RendererID);
	}
	
	public void SetLayout(BufferLayout layout) {
		m_Layout = layout;
	}
	
	public BufferLayout GetLayout() {
		return m_Layout;
	}
	
	public void SetData(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
	}
}
