package com.hvadoda1.server.http.simple;

import java.io.IOException;
import java.net.Socket;

import com.hvadoda1.server.IClientMeta;
import com.hvadoda1.server.http.Config;
import com.hvadoda1.server.http.HttpServer;
import com.hvadoda1.server.http.IHttpRequest;
import com.hvadoda1.server.http.IHttpResponse;

public class SimpleHttpServer extends HttpServer {

	public SimpleHttpServer(int port, int maxNumThreads) throws IOException {
		super(port, maxNumThreads);
	}

	@Override
	protected void onRequest(IClientMeta<Socket> client, IHttpRequest request, IHttpResponse response)
			throws InterruptedException, IOException {
		processHttpRequest(client, request, response);
		super.onRequest(client, request, response);
	}

	public SimpleHttpServer(int port) throws IOException {
		super(port);
	}

//	@Override
//	protected void onRequest(IClientMeta client, IHttpRequest request, IResponse response) throws InterruptedException {
//
//		super.onRequest(client, request, response);
//	}

	protected void processHttpRequest(IClientMeta<Socket> client, IHttpRequest request, IHttpResponse response)
			throws IOException {
		response.setVersionString(request.getVersionString());
		response.respondWithFile(Config.getServedFile(request.getQueryString()));
	}

}
