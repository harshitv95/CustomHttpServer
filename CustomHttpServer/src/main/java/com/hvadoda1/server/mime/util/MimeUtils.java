package com.hvadoda1.server.mime.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import com.hvadoda1.server.mime.MimeWriterFactory;

public class MimeUtils {
	public static String getMimeType(File file) throws IOException {
		return Files.probeContentType(file.toPath());
	}

	public static void writeMimeToOutput(File file, OutputStream out) throws IOException {
		String mimeType = getMimeType(file);
		MimeWriterFactory.factory(mimeType).writeToOutput(file, out);
	}
}
