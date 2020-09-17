package com.hvadoda1.server;

import com.hvadoda1.server.http.HttpServer;
import com.hvadoda1.server.tcp.TcpServer;

public enum ServerType {
	TCP(TcpServer.class), HTTP(HttpServer.class),;

	private final Class<? extends IServer<?>> clazz;

	private ServerType(Class<? extends IServer<?>> clazz) {
		this.clazz = clazz;
	}

	public Class<? extends IServer<?>> getServerClass() {
		return clazz;
	}
}
