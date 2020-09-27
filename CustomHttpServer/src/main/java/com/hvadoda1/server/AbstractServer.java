package com.hvadoda1.server;

import java.io.Closeable;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import com.hvadoda1.util.Logger;
import com.hvadoda1.util.ThreadPool;

public abstract class AbstractServer<CLIENT, CLMETA extends IClientMeta<CLIENT>, LIS extends IServerListener<CLIENT, CLMETA, REQ, RESP>, REQ extends IRequest, RESP extends IResponse>
		implements IServer<LIS>, Closeable {

	protected final Set<LIS> listeners = new LinkedHashSet<>();
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
		Logger.debugLow("Calling registered [onStart] listeners");
		listeners.forEach(l -> l.onStart());
	}

	protected abstract CLMETA createClientMeta(CLIENT c);

	protected abstract REQ createRequest(CLMETA c) throws IOException;

	protected abstract RESP createResponse(CLMETA c) throws IOException;

	protected void onRequest(CLMETA client, REQ request, RESP response) throws InterruptedException, IOException {
		Logger.debugLow("Calling registered [onRequest] listeners with params:", client, request, response);
		Runnable r = () -> {

			try {
				listeners.forEach(l -> {
					try {
						l.onRequest(client, request, response);
						Logger.debugHigh("Called registered [onRequest] listener with params:", client, request,
								response);
					} catch (Exception e) {
						Logger.error("Exception while processing [onRequest] listener", e);
					}
				});
			} finally {
				try {
					Logger.debugLow("Closing client", client.getInetAddress().getHostName());
					client.close();
				} catch (Exception e) {
					throw new RuntimeException("Failed to close client", e);
				}
			}
		};
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
