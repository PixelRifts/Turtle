package com.pixelrifts.turtleshell.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Serializer {
	public static boolean prettyPrint = false;
	static GsonBuilder builder = new GsonBuilder();
	static Gson gson = builder.create();

	private final String filepath;

	public Serializer(String filepath) {
		this.filepath = filepath;
	}

	public static void SetPrettyPrint(boolean prettyPrint) {
		if (prettyPrint) {
			builder = new GsonBuilder().setPrettyPrinting();
			gson = builder.create();
		}
		Serializer.prettyPrint = prettyPrint;
	}

	public String SerializeToString(Object o) {
		return gson.toJson(o);
	}

	public void Serialize(Object o) {
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
