package com.hvadoda1.server.starter;

import java.io.IOException;
import java.net.Socket;

import com.hvadoda1.server.IClientMeta;
import com.hvadoda1.server.http.HttpServer;
import com.hvadoda1.server.http.IHttpRequest;
import com.hvadoda1.server.http.IHttpResponse;
import com.hvadoda1.server.http.IHttpServerListener;
import com.hvadoda1.server.http.simple.SimpleHttpServer;

public class ServerStarter {

	public static void main(String[] args) throws IOException, InterruptedException {

//		HttpServer server = (HttpServer) ServerFactory.factory(ServerType.HTTP, 80);
		int port = 80;
		if (args.length > 0)
			port = Integer.parseInt(args[0]);
		HttpServer server = new SimpleHttpServer(port);
//		if (server == null)
//			return;

		server.registerListener(new IHttpServerListener() {

			@Override
			public void onStart() {
				System.out.println("SERVER STARTED");
			}

			@Override
			public void beforeClose() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onRequest(IClientMeta<Socket> client, IHttpRequest request, IHttpResponse response)
					throws InterruptedException, IOException {
				System.out.println("On Request");
			}

		});

		server.serve();

		server.close();

	}

}
