package com.pixelrifts.turtle.glabs.objects;

import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBTTBakedChar;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL45.*;
import static org.lwjgl.stb.STBImage.*;

public class Texture {
	public static final Texture White = new Texture("src/main/resources/White.png");

	private final int m_RendererID;
	private final int m_Width;
	private final int m_Height;

	private int m_TexSlot;

	static {
		stbi_set_flip_vertically_on_load(true);
	}

	public Texture(String filepath) {
		try (MemoryStack stack = MemoryStack.stackPush()) {
			IntBuffer w = stack.mallocInt(1);
			IntBuffer h = stack.mallocInt(1);
			IntBuffer comp = stack.mallocInt(1);

			ByteBuffer data = stbi_load(filepath, w, h, comp, 4);
			m_RendererID = glGenTextures();

			m_Width = w.get();
			m_Height = h.get();

			glBindTexture(GL_TEXTURE_2D, m_RendererID);

			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, m_Width, m_Height, 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
			assert data != null : "Image " + filepath + " doesn't exist";
			stbi_image_free(data);
			m_TexSlot = 0;
		}
	}

	public void BindSlot(int textureUnit) {
		glBindTextureUnit(textureUnit, m_RendererID);
		m_TexSlot = textureUnit;
	}
	public void Unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
		m_TexSlot = -1;
	}
	public void Delete() {
		glDeleteTextures(m_RendererID);
	}

	public int GetID() {
		return m_RendererID;
	}
	public int GetHeight() {
		return m_Height;
	}
	public int GetWidth() {
		return m_Width;
	}
	public int GetBoundSlot() {
		return m_TexSlot;
	}
}
