package com.hvadoda1.server.http.simple;

import java.io.IOException;
import java.net.Socket;

import com.hvadoda1.server.IClientMeta;
import com.hvadoda1.server.http.HttpServer;
import com.hvadoda1.server.http.IHttpRequest;
import com.hvadoda1.server.http.IHttpResponse;
import com.hvadoda1.server.http.IHttpServerListener;

public class SimpleHttpServer extends HttpServer {

	protected long waitMillis = 0;

	public SimpleHttpServer(String serverName, int maxNumThreads) throws IOException {
		super(serverName, maxNumThreads);
		registerListener();
	}

	public SimpleHttpServer(String serverName) throws IOException {
		super(serverName);
		registerListener();
	}

	public void waitBeforeResponse(long millis) {
		this.waitMillis = millis < 1 ? 0 : millis;
	}

	protected void registerListener() {
		registerListener(new IHttpServerListener() {

			@Override
			public void onStart() {
			}

			@Override
			public void onRequest(IClientMeta<Socket> client, IHttpRequest request, IHttpResponse response)
					throws InterruptedException, IOException {
				processHttpRequest(client, request, response);
			}

			@Override
			public void beforeClose() {
			}
		});
	}

	protected void processHttpRequest(IClientMeta<Socket> client, IHttpRequest request, IHttpResponse response)
			throws IOException {
		response.setVersionString(request.getVersionString());
		response.respondWithFile(request.getRequestedResource());
	}

	@Override
	protected void requestPreprocess(IClientMeta<Socket> client, IHttpRequest request, IHttpResponse response) {
		if (waitMillis > 0)
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		super.requestPreprocess(client, request, response);
	}

}
