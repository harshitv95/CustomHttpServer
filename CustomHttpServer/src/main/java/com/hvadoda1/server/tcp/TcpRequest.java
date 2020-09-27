package com.hvadoda1.server.tcp;

import java.io.IOException;
import java.net.Socket;
import java.util.function.Function;

import com.hvadoda1.server.IRequest;
import com.hvadoda1.server.util.TcpUtils;
import com.hvadoda1.util.Logger;

public class TcpRequest implements IRequest {
	final Socket client;
	protected final Function<String, Boolean> terminator;

	public TcpRequest(final Socket client, String terminator) throws IOException {
		this(client, (msg) -> msg.equals(terminator));
	}

	public TcpRequest(final Socket client, Function<String, Boolean> msgTerminatorFn) {
		Logger.debugHigh("Init TcpRequest", client.getInetAddress().getHostName());
		this.client = client;
		this.terminator = msgTerminatorFn;
	}

	@Override
	public String readMessage() throws IOException {
		String ret = TcpUtils.readMessage(client, terminator);
		Logger.debugHigh("Read TCP message", ret);
		return ret;
	}

}
