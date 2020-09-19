package com.hvadoda1.server;

import java.io.IOException;

public interface IServer<LIS extends IServerListener<?, ?, ?, ?>> {

	/**
	 * @throws IOException
	 * @throws InterruptedException If using Multi-threaded server
	 */
	void serve() throws IOException, InterruptedException;

	void registerListener(LIS listener);

	void unregisterListener(LIS listener);

	void init();
}
