package com.hvadoda1.server.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MimeUtils {
	public static String getMimeType(File file) throws IOException {
		return Files.probeContentType(file.toPath());
	}
}
