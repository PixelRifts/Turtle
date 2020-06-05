package com.pixelrifts.turtle.glabs.objects;

public class BufferLayout {
	private BufferElement[] m_Elements;
	private int m_Stride;
	
	public BufferLayout(BufferElement... elements) {
		m_Elements = elements;
		
		m_Stride = 0;
		int offset = 0;
		for (BufferElement e : m_Elements) {
			m_Stride += e.GetDatatype().getSize();
			e.SetOffset(offset);
			offset += e.GetDatatype().getSize();
		}
	}
	
	public BufferElement[] GetElements() {
		return m_Elements;
	}
	
	public int GetStride() {
		return m_Stride;
	}
}
