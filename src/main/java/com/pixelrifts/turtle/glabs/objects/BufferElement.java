package com.pixelrifts.turtle.glabs.objects;

public class BufferElement {
	private String m_Name;
	private ShaderDataType m_Datatype;
	private int m_Offset;
	private boolean m_Normalized;

	public BufferElement(String name, ShaderDataType datatype) {
		m_Name = name;
		m_Datatype = datatype;
		m_Normalized = false;
	}

	public BufferElement(String name, ShaderDataType datatype, boolean normalized) {
		m_Name = name;
		m_Datatype = datatype;
		m_Normalized = normalized;
	}
	
	public ShaderDataType GetDatatype() {
		return m_Datatype;
	}
	
	public String GetName() {
		return m_Name;
	}
	
	public int GetOffset() {
		return m_Offset;
	}
	
	public void SetOffset(int offset) {
		this.m_Offset = offset;
	}
	
	public boolean IsNormalized() {
		return m_Normalized;
	}
}
