package com.hvadoda1.server.tcp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import com.hvadoda1.server.IClientMeta;
import com.hvadoda1.util.Logger;

public class TcpClientMeta implements IClientMeta<Socket> {

	protected final Socket client;

	public TcpClientMeta(Socket client) {
		Logger.debugHigh("Init Client Meta", client.getInetAddress().getHostAddress());
		this.client = client;
	}

	@Override
	public String hostname() {
		return client.getInetAddress().getCanonicalHostName();
	}

	@Override
	public InetAddress getInetAddress() {
		return client.getInetAddress();
	}

	@Override
	public Socket getClient() {
		return client;
	}

	@Override
	public void close() throws IOException {
		Logger.debugLow("Closing Client Meta", client.getInetAddress().getHostAddress());
		client.close();
	}

}
