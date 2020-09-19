package com.hvadoda1.server.http;

import java.io.IOException;
import java.net.Socket;

import com.hvadoda1.server.AbstractServer;
import com.hvadoda1.server.IClientMeta;
import com.hvadoda1.server.IServerListener;
import com.hvadoda1.server.tcp.TcpClientMeta;
import com.hvadoda1.server.tcp.TcpRequest;
import com.hvadoda1.server.tcp.TcpResponse;
import com.hvadoda1.server.tcp.TcpServer;

public class HttpServer
		extends AbstractServer<Socket, IClientMeta<Socket>, IHttpServerListener, IHttpRequest, IHttpResponse> {

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
		server.registerListener(new IServerListener<Socket, TcpClientMeta, TcpRequest, TcpResponse>() {

			@Override
			public void onStart() {
				HttpServer.this.onStart();
			}

			@Override
			public void onRequest(TcpClientMeta client, TcpRequest request, TcpResponse response)
					throws InterruptedException, IOException {
				HttpServer.this.onRequest(client);
			}

			@Override
			public void beforeClose() {
				HttpServer.this.beforeClose();
			}
		});
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
	protected IClientMeta<Socket> createClientMeta(Socket c) {
		return new TcpClientMeta(c);
	}

	@Override
	protected IHttpRequest createRequest(IClientMeta<Socket> c) throws IOException {
		return new HttpRequest(c);
	}

	@Override
	protected IHttpResponse createResponse(IClientMeta<Socket> c) throws IOException {
		return new HttpResponse(c.getClient());
	}

	@Override
	public void init() {
		new Config();
	}

}
