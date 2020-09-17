package com.hvadoda1.server.starter;

import java.io.IOException;

import com.hvadoda1.server.IClientMeta;
import com.hvadoda1.server.IResponse;
import com.hvadoda1.server.ServerFactory;
import com.hvadoda1.server.ServerType;
import com.hvadoda1.server.http.HttpServer;
import com.hvadoda1.server.http.IHttpRequest;
import com.hvadoda1.server.http.IHttpServerListener;

public class ServerStarter {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		HttpServer server = (HttpServer) ServerFactory.factory(ServerType.HTTP, 80);
//		HttpServer server = new HttpServer(80);
		if (server == null) return;
		
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
			public void onRequest(IClientMeta client, IHttpRequest request, IResponse response) {
				System.out.println("On Request");
			}

		});
		
		server.serve();
		
		server.close();
		
	}

}
