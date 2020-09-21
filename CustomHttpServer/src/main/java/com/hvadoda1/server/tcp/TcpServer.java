package com.hvadoda1.server.tcp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.hvadoda1.server.AbstractServer;
import com.hvadoda1.server.Config;
import com.hvadoda1.server.IServerListener;

public class TcpServer extends
		AbstractServer<Socket, TcpClientMeta, IServerListener<Socket, TcpClientMeta, TcpRequest, TcpResponse>, TcpRequest, TcpResponse> {

	protected ServerSocket server;

	public TcpServer() throws IOException {
		super();
	}

	public TcpServer(int maxNumThreads) throws IOException {
		super(maxNumThreads);
	}

	protected ServerSocket initialize() throws IOException {
		return new ServerSocket(this.port);
	}

	@Override
	public void serve(int port) throws IOException, InterruptedException {
		this.port = port;
		server = initialize();
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

	@Override
	public int port() {
		return this.port;
	}

	@Override
	public String hostName() {
		return getInetAddress().getHostName();
	}

	@Override
	public InetAddress getInetAddress() {
		return server.getInetAddress();
	}
}
