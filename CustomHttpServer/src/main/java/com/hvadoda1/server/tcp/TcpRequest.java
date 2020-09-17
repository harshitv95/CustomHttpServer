package com.hvadoda1.server.tcp;

import java.io.IOException;
import java.net.Socket;
import java.util.function.Function;

import com.hvadoda1.server.IRequest;
import com.hvadoda1.server.utils.TcpUtils;

public class TcpRequest implements IRequest {
	final Socket client;
	protected final Function<String, Boolean> terminator;
	public TcpRequest(final Socket client, String terminator) throws IOException {
//		this.terminator = terminator;	
		this(client, (msg) -> msg.equals(terminator));
	}
	
	public TcpRequest(final Socket client, Function<String, Boolean> msgTerminatorFn) {
		this.client = client;
		this.terminator = msgTerminatorFn;
	}

	@Override
	public String readMessage() throws IOException {
		return TcpUtils.readMessage(client, terminator);
	}
	
	public Socket getClient() {
		return client;
	}

}
