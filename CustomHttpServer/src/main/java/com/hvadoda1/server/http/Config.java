package com.hvadoda1.server.http;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Config extends com.hvadoda1.server.Config {

	private final static String SERVED_DIR_PREFIX = com.hvadoda1.server.Config.CURENT_DIR + "www" + File.separator;

	private static String serverName;

	public Config() {
		super();
	}

	@Override
	protected void initialize() throws FileNotFoundException {
		if (Files.notExists(Paths.get(SERVED_DIR_PREFIX)))
			throw new FileNotFoundException("MISSING SERVED DIRECTORY: [" + SERVED_DIR_PREFIX + ']');
		super.initialize();
	}

	public static String getServedFilePath(String filePath) {
		return SERVED_DIR_PREFIX + filePath;
	}

	public static File getServedFile(String filePath) {
		return new File(getServedFilePath(filePath));
	}

	public static String getServerName() {
		return serverName;
	}

	public static void setServerName(String serverName) {
		Config.serverName = serverName;
	}

}
