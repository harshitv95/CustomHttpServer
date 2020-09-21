package com.hvadoda1.server;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.hvadoda1.util.ThreadPool;

public abstract class AbstractServer<CLIENT, CLMETA extends IClientMeta<CLIENT>, LIS extends IServerListener<CLIENT, CLMETA, REQ, RESP>, REQ extends IRequest, RESP extends IResponse>
		implements IServer<LIS>, Closeable {

	protected final Set<LIS> listeners = new HashSet<>();
	protected int port = 0;
	protected final ThreadPool threads;
	protected final boolean isMultiThreaded;

	public AbstractServer() {
		this(0);
	}

	public AbstractServer(int maxNumThreads) {
		this.isMultiThreaded = maxNumThreads == 0 ? false : true;
		this.threads = !isMultiThreaded ? null : new ThreadPool(maxNumThreads);
	}

//	protected abstract void initializeServer();

	protected void onStart() {
		listeners.forEach(l -> l.onStart());
	}

	protected abstract CLMETA createClientMeta(CLIENT c);

	protected abstract REQ createRequest(CLMETA c) throws IOException;

	protected abstract RESP createResponse(CLMETA c) throws IOException;

	protected void onRequest(CLMETA client, REQ request, RESP response) throws InterruptedException, IOException {
		Runnable r = () -> listeners.forEach(l -> {
			try {
				l.onRequest(client, request, response);
				client.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
		if (isMultiThreaded)
			threads.get(r);
		else
			r.run();
	}

	protected void onRequest(CLMETA client) throws InterruptedException, IOException {
		onRequest(client, createRequest(client), createResponse(client));
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
