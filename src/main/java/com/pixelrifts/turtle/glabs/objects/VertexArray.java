package com.pixelrifts.turtle.glabs.objects;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL45.*;

import java.util.ArrayList;
import java.util.List;

public class VertexArray {
	private final int m_RendererID;
	private final List<VertexBuffer> m_Buffers;
	private IndexBuffer m_IndexBuffer;

	public VertexArray() {
		m_RendererID = glCreateVertexArrays();
		m_Buffers = new ArrayList<>();
	}
	
	public void Bind() {
		glBindVertexArray(m_RendererID);
	}
	public void Unbind() {
		glBindVertexArray(0);
	}
	
	public void Delete() {
		for (VertexBuffer vbo : m_Buffers)
			vbo.Delete();
		glDeleteVertexArrays(m_RendererID);
	}

	public void SetIBO(IndexBuffer ibo) {
		m_IndexBuffer = ibo;
	}

	public IndexBuffer GetIndexBuffer() {
		return m_IndexBuffer;
	}

	public void AddBuffer(int startIndex, VertexBuffer buffer) {
		buffer.Bind();
		glBindVertexArray(m_RendererID);
		
		m_Buffers.add(buffer);
		
		int index = startIndex;
		BufferLayout layout = buffer.GetLayout();
		for (BufferElement element : layout.GetElements()) {
			glEnableVertexArrayAttrib(m_RendererID, index);
			glVertexAttribPointer (
					index++,
					element.GetDatatype().getComponentCount(),
					element.GetDatatype().getGlDataType(),
					element.IsNormalized(),
					layout.GetStride(),
					element.GetOffset()
			);
		}
	}
}
