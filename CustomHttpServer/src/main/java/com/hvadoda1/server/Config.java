package com.hvadoda1.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Config {

	private static Config instance;

	public Config() {
		if (instance != null)
			throw new RuntimeException("Config already initialized");
		try {
			initialize();
		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize Config", e);
		}
	}

	protected void initialize() throws FileNotFoundException {
		
	}

	public static Config getInstance() {
		if (instance == null)
			new Config();
		return instance;
	}

}
