package com.hvadoda1.server.tcp;

import java.net.InetAddress;
import java.net.Socket;

import com.hvadoda1.server.IClientMeta;

public class TcpClientMeta implements IClientMeta {

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

}
