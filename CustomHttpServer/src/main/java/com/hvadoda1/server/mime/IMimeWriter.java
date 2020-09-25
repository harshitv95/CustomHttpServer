package com.hvadoda1.server.mime;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public interface IMimeWriter {
	void writeToOutput(String str, OutputStream os) throws IOException;

	void writeToOutput(File file, OutputStream os) throws IOException;

//	void writeToOutput(String str, Writer w) throws IOException;
//
//	void writeToOutput(File file, Writer os) throws IOException;
}
