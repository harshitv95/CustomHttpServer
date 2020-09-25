package com.hvadoda1.server.mime;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class DataMimeWriter implements IMimeWriter {

	@Override
	public void writeToOutput(String str, OutputStream os) throws IOException {
		PrintWriter pw = new PrintWriter(os);
		pw.print(str);
		pw.flush();
	}

	@Override
	public void writeToOutput(File file, OutputStream os) throws IOException {
		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(os));
		try (FileInputStream fis = new FileInputStream(file)) {
			byte[] buf = new byte[4096];
			int bytesRead;
			while ((bytesRead = fis.read(buf)) != -1) {
				dos.write(buf, 0, bytesRead);
				dos.flush();
			}

		}
	}

}
