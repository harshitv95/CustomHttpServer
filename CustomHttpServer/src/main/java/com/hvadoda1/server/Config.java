package com.hvadoda1.server;

import java.io.File;
import java.io.FileNotFoundException;

public class Config {

	private static Config instance;

	public static final String CURENT_DIR = System.getProperty("user.dir") + File.separator;

	public static final String LOG_DIR = CURENT_DIR + "logs" + File.separator;

	public Config() {
		if (instance != null)
			throw new RuntimeException("Config already initialized");
		try {
			initialize();
		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize Config", e);
		}
		instance = this;
	}

	protected void initialize() throws FileNotFoundException {
		File logDir = new File(LOG_DIR);
		if (!logDir.exists() || !logDir.isDirectory())
			logDir.mkdir();
	}

	public static Config getInstance() {
		if (instance == null)
			new Config();
		return instance;
	}

}
