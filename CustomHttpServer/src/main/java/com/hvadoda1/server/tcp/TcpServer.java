package com.hvadoda1.server.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.hvadoda1.server.AbstractServer;
import com.hvadoda1.server.Config;
import com.hvadoda1.server.IServerListener;

public class TcpServer extends
		AbstractServer<Socket, TcpClientMeta, IServerListener<TcpClientMeta, TcpRequest, TcpResponse>, TcpRequest, TcpResponse> {

	protected final ServerSocket server;

	public TcpServer(int port) throws IOException {
		super(port);
		server = initialize();
	}

	public TcpServer(int port, int maxNumThreads) throws IOException {
		super(port, maxNumThreads);
		server = initialize();
	}

	protected ServerSocket initialize() throws IOException {
		return new ServerSocket(this.port);
	}

	@Override
	public void serve() throws IOException, InterruptedException {
		Socket client;
		onStart();
		while (true) {
			client = server.accept();
			onRequest(createClientMeta(client));
		}
	}

	@Override
	public void close() throws IOException {
		beforeClose();
		server.close();
	}

	@Override
	protected TcpClientMeta createClientMeta(Socket c) {
		return new TcpClientMeta(c);
	}

	@Override
	protected TcpRequest createRequest(TcpClientMeta c) throws IOException {
		return new TcpRequest(c.getClient(), (String) null);
	}

	@Override
	protected TcpResponse createResponse(TcpClientMeta c) throws IOException {
		return new TcpResponse(c.getClient());
	}

	@Override
	public void init() {
		new Config();
	}
}
