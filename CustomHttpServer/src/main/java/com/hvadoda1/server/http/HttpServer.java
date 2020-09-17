package com.hvadoda1.server.http;

import java.io.IOException;

import com.hvadoda1.server.AbstractServer;
import com.hvadoda1.server.IClientMeta;
import com.hvadoda1.server.IResponse;
import com.hvadoda1.server.IServerListener;
import com.hvadoda1.server.tcp.TcpClientMeta;
import com.hvadoda1.server.tcp.TcpRequest;
import com.hvadoda1.server.tcp.TcpResponse;
import com.hvadoda1.server.tcp.TcpServer;

public class HttpServer extends AbstractServer<TcpRequest, IClientMeta, IHttpServerListener, IHttpRequest, IResponse> {

	protected final TcpServer server;

	public HttpServer(int port, int maxNumThreads) throws IOException {
		super(port, 1);
		server = new TcpServer(port, maxNumThreads);
		registerTcpListeners(server);
	}

	public HttpServer(int port) throws IOException {
		super(port, 1);
		server = new TcpServer(port);
		registerTcpListeners(server);
	}

	protected void registerTcpListeners(TcpServer server) {
		server.registerListener(new IServerListener<TcpClientMeta, TcpRequest, TcpResponse>() {

			@Override
			public void onStart() {
				HttpServer.this.onStart();
			}

			@Override
			public void onRequest(TcpClientMeta client, TcpRequest request, TcpResponse response)
					throws InterruptedException, IOException {
				HttpServer.this.onRequest(client, createRequest(request), response);
			}

			@Override
			public void beforeClose() {
				HttpServer.this.beforeClose();
			}
		});
	}

	@Override
	protected IHttpRequest createRequest(TcpRequest c) throws IOException {
		return new HttpRequest(c);
	}

	@Override
	public void serve() throws IOException, InterruptedException {
		server.serve();
	}

	@Override
	public void close() throws IOException {
		beforeClose();
		server.close();
	}

	@Override
	protected IClientMeta createClientMeta(TcpRequest c) {
		return new TcpClientMeta(c.getClient());
	}

	@Override
	protected IResponse createResponse(TcpRequest c) throws IOException {
//		return new TcpRE
		return null;
	}

}
