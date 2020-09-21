package com.hvadoda1.server;

import java.io.IOException;
import java.net.InetAddress;

public interface IServer<LIS extends IServerListener<?, ?, ?, ?>> {

	/**
	 * @throws IOException
	 * @throws InterruptedException If using Multi-threaded server
	 */
	void serve(int port) throws IOException, InterruptedException;

	void registerListener(LIS listener);

	void unregisterListener(LIS listener);

	void init();

	int port();

	String hostName();

	InetAddress getInetAddress();
}
