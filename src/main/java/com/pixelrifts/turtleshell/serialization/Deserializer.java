package com.pixelrifts.turtleshell.serialization;

import com.google.gson.Gson;

import java.io.*;

public class Deserializer {
	private final String filepath;

	public Deserializer(String filepath) {
		this.filepath = filepath;
	}

	public <T> T Deserialize(Class<T> type) {
		Gson gson = Serializer.gson;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filepath)));
			String line;
			StringBuilder txt = new StringBuilder();
			while ((line = reader.readLine()) != null)
				txt.append(line);
			return gson.fromJson(txt.toString(), type);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
