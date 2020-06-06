package com.pixelrifts.turtle.glabs.objects;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

public class Shader {
	private final int programID;
	
	public Shader(String name) {
		programID = glCreateProgram();
		int vsID = LoadShader("src/main/resources/shaders/" + name + "V.glsl", GL_VERTEX_SHADER);
		int fsID = LoadShader("src/main/resources/shaders/" + name + "F.glsl", GL_FRAGMENT_SHADER);
		glAttachShader(programID, vsID);
		glAttachShader(programID, fsID);
		glLinkProgram(programID);
		if (glGetProgrami(programID, GL_LINK_STATUS) != GL_TRUE) {
			System.err.println(glGetProgramInfoLog(programID));
			System.exit(-1);
		}
		glValidateProgram(programID);
		if (glGetProgrami(programID, GL_VALIDATE_STATUS) != GL_TRUE) {
			System.err.println(glGetProgramInfoLog(programID));
			System.exit(-1);
		}
		glDetachShader(programID, vsID);
		glDetachShader(programID, fsID);
		glDeleteShader(vsID);
		glDeleteShader(fsID);
	}
	
	public void Delete() {
		glDeleteProgram(programID);
	}
	
	public void Bind() {
		glUseProgram(programID);
	}
	
	public void Unbind() {
		glUseProgram(0);
	}
	
	public void UploadFloat(String name, float val) {
		int loc = glGetUniformLocation(programID, name);
		glUniform1f(loc, val);
	}

	public void UploadInt(String name, int val) {
		int loc = glGetUniformLocation(programID, name);
		glUniform1i(loc, val);
	}

	public void UploadMatrix(String name, Matrix4f mat) {
		int loc = glGetUniformLocation(programID, name);
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		mat.get(buffer);
		glUniformMatrix4fv(loc, false, buffer);
	}

	public void UploadIntArray(String name, int[] array) {
		int loc = glGetUniformLocation(programID, name);
		glUniform1iv(loc, array);
	}

	private int LoadShader(String path, int type) {
		StringBuilder builder = new StringBuilder();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(new File(path)));
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
				builder.append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		int shaderID = glCreateShader(type);
		glShaderSource(shaderID, builder.toString());
		glCompileShader(shaderID);
		if (glGetShaderi(shaderID, GL_COMPILE_STATUS) != GL_TRUE) {
			System.err.println(glGetShaderInfoLog(shaderID));
			System.exit(-1);
		}
		return shaderID;
	}
}
