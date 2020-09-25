package com.hvadoda1.server.mime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class TextMimeWriter implements IMimeWriter {

	@Override
	public void writeToOutput(String str, OutputStream os) throws IOException {
		PrintWriter w = new PrintWriter(os);
		w.write(str);
		w.flush();
	}

	@Override
	public void writeToOutput(File file, OutputStream os) throws IOException {
		PrintWriter w = new PrintWriter(os);
		if (!file.exists() || !file.isFile())
			throw new FileNotFoundException(file.getName());

		try (BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));) {
			String line;
			while ((line = fr.readLine()) != null) {
				w.write(line);
				w.write('\n');
			}
			w.flush();
		}
	}

//	@Override
//	public void writeToOutput(String str, Writer w) throws IOException {
//		w.write(str);
//		w.flush();
//	}
//
//	@Override
//	public void writeToOutput(File file, Writer w) throws IOException {
//		if (!file.exists() || !file.isFile())
//			throw new FileNotFoundException(file.getName());
//
//		try (BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));) {
//			String line;
//			while ((line = fr.readLine()) != null) {
//				w.write(line);
//				w.write('\n');
//			}
//			w.flush();
//		}
//	}

}
