package com.hvadoda1.server.mime;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class ImageMimeWriter implements IMimeWriter {

	@Override
	public void writeToOutput(String str, OutputStream os) throws IOException {
		throw new RuntimeException("Cannot write text as image to output");
	}

	@Override
	public void writeToOutput(File file, OutputStream os) throws IOException {
		if (!file.exists() || !file.isFile())
			throw new FileNotFoundException(file.getName());
		BufferedImage img = ImageIO.read(file);

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		String[] fnSplit = file.getName().split("\\.");
		ImageIO.write(img, fnSplit[fnSplit.length - 1], bos);
		os.write(bos.toByteArray());
		os.flush();
	}

//	@Override
//	public void writeToOutput(String str, Writer w) throws IOException {
//		throw new RuntimeException("Cannot write text as image to output");
//	}
//
//	@Override
//	public void writeToOutput(File file, Writer w) throws IOException {
//		throw new RuntimeException("Use writeToOutput(File, OutputStream) instead");
//	}

}
