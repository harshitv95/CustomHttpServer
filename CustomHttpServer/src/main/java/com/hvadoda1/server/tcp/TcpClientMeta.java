package com.hvadoda1.server.tcp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import com.hvadoda1.server.IClientMeta;

public class TcpClientMeta implements IClientMeta<Socket> {

	protected final Socket client;

	public TcpClientMeta(Socket client) {
		this.client = client;
	}

	@Override
	public String clientHostname() {
		return client.getInetAddress().getCanonicalHostName();
	}

	@Override
	public InetAddress clientIpAddress() {
		return client.getInetAddress();
	}

	@Override
	public Socket getClient() {
		return client;
	}

	@Override
	public void close() throws IOException {
		client.close();
	}

}
