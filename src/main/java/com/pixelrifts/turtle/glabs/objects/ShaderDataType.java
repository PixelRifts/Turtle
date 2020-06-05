package com.pixelrifts.turtle.glabs.objects;

import static org.lwjgl.opengl.GL11.*;

public enum ShaderDataType {
	Float(GL_FLOAT, 4, 1), Float2(GL_FLOAT, 4 * 2, 2), Float3(GL_FLOAT, 4 * 3, 3), Float4(GL_FLOAT, 4 * 4, 4),
	Int(GL_UNSIGNED_INT, 4, 1), Int2(GL_UNSIGNED_INT, 4 * 2, 2), Int3(GL_UNSIGNED_INT, 4 * 3, 3), Int4(GL_UNSIGNED_INT, 4 * 4, 4),
	Mat3(GL_FLOAT, 4 * 3 * 3, 9), Mat4(GL_FLOAT, 4 * 4 * 4, 16);

	private int glDataType;
	private int size;
	private int componentCount;

	private ShaderDataType(int glDataType, int size, int componentCount) {
		this.glDataType = glDataType;
		this.size = size;
		this.componentCount = componentCount;
	}
	
	public int getGlDataType() {
		return glDataType;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getComponentCount() {
		return componentCount;
	}
}
