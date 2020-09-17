package com.hvadoda1.server;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.hvadoda1.utils.ThreadPool;

public abstract class AbstractServer<CLIENT, CLMETA extends IClientMeta, LIS extends IServerListener<CLMETA, REQ, RESP>, REQ extends IRequest, RESP extends IResponse>
		implements IServer<LIS>, Closeable {

	protected final Set<LIS> listeners = new HashSet<>();
	protected final int port;
	protected final ThreadPool threads;

	public AbstractServer(int port) {
		this(port, 1);
	}

	public AbstractServer(int port, int maxNumThreads) {
		this.port = port;
		this.threads = new ThreadPool(maxNumThreads);
	}

//	protected abstract void initializeServer();

	protected void onStart() {
		listeners.forEach(l -> l.onStart());
	}

	protected abstract CLMETA createClientMeta(CLIENT c);

	protected abstract REQ createRequest(CLIENT c) throws IOException;

	protected abstract RESP createResponse(CLIENT c) throws IOException;

	protected void onRequest(CLMETA client, REQ request, RESP response) throws InterruptedException {
		threads.get(() -> listeners.forEach(l -> {
			try {
				l.onRequest(client, request, response);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}));
	}

	protected void beforeClose() {
		listeners.forEach(l -> l.beforeClose());
	}

	@Override
	public void registerListener(LIS listener) {
		this.listeners.add(listener);
	}

	@Override
	public void unregisterListener(LIS listener) {
		this.listeners.remove(listener);
	}

}
