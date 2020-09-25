package com.hvadoda1.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public interface IResponse {
	void writeMessage(String message);

	OutputStream getOutputStream() throws IOException;

	Writer getOutputStreamWriter() throws IOException;
}
