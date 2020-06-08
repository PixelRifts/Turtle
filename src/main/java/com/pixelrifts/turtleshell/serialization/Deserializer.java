package com.pixelrifts.turtleshell.serialization;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Deserializer {
	private final String filepath;
	private final SerializationFormat format;

	public Deserializer(String filepath, SerializationFormat format) {
		this.filepath = filepath;
		this.format = format;
	}

	public <T> T Deserialize(Class<T> type) {
		switch (format) {
			case JSON:
				return DeserializeJSON(type);
			case Binary:
				System.err.println("Binary Format is not supported yet");
				break;
		}
		return null;
	}

	private <T> T DeserializeJSON(Class<T> type) {
		Gson gson = Serializer.gson;
		try {
			FileReader reader = new FileReader(new File(filepath));
			return gson.fromJson(reader, type);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
