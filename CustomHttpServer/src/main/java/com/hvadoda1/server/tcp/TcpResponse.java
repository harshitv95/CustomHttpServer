package com.hvadoda1.server.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;

import com.hvadoda1.server.IResponse;

public class TcpResponse implements IResponse {
	protected final Socket client;
	protected final PrintWriter pw;

	public TcpResponse(final Socket client) throws IOException {
		this.client = client;
		this.pw = new PrintWriter(getOutputStream());
	}

	@Override
	public void writeMessage(String message) {
		pw.print(message);
		pw.flush();
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		return client.getOutputStream();
	}

	@Override
	public Writer getOutputStreamWriter() throws IOException {
		return pw;
	}

	
	
}
