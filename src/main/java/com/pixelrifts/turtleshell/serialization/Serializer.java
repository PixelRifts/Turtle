package com.pixelrifts.turtleshell.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Serializer {
	private final String filepath;
	private final SerializationFormat format;
	static final GsonBuilder builder = new GsonBuilder();
	static final Gson gson = builder.create();

	public Serializer(String filepath, SerializationFormat format) {
		this.filepath = filepath;
		this.format = format;
	}

	public String SerializeToString(Object o) {
		switch (format) {
			case JSON:
				return SerializeToStringJSON(o);
			case Binary:
				System.err.println("Binary Format is not supported yet");
				break;
		}
		return "---";
	}

	private String SerializeToStringJSON(Object o) {
		return gson.toJson(o);
	}

	public void Serialize(Object o) {
		switch (format) {
			case JSON:
				SerializeJSON(o);
				break;
			case Binary:
				System.err.println("Binary Format is not supported yet");
				break;
		}
	}

	private void SerializeJSON(Object o) {
		String s = gson.toJson(o);
		try {
			FileWriter writer = new FileWriter(new File(filepath));
			writer.write(s);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
