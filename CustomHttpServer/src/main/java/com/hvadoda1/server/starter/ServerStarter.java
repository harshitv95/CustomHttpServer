package com.hvadoda1.server.starter;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import com.hvadoda1.server.IClientMeta;
import com.hvadoda1.server.http.Config;
import com.hvadoda1.server.http.HttpServer;
import com.hvadoda1.server.http.IHttpRequest;
import com.hvadoda1.server.http.IHttpResponse;
import com.hvadoda1.server.http.IHttpServerListener;
import com.hvadoda1.server.http.simple.SimpleHttpServer;
import com.hvadoda1.util.CommonUtils;
import com.hvadoda1.util.DateTimeUtils;
import com.hvadoda1.util.Logger;
import com.hvadoda1.util.Logger.Level;

public class ServerStarter {

	public static void main(String[] args) throws IOException, InterruptedException {

		Map<String, String> argMap = CommonUtils.parseArgsMap(args);
		
		new Config();

		new Logger(Level.from(Integer.parseInt(argMap.getOrDefault("level", "3"))),
				"logs" + File.separator + "log_" + (DateTimeUtils.logFileNameDateString()) + ".txt");

//		HttpServer server = (HttpServer) ServerFactory.factory(ServerType.HTTP, 80);
		int port = 80;
		if (argMap.containsKey("port"))
			port = Integer.parseInt(argMap.get("port"));
		
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

//		server.init();

		server.serve();

		server.close();

	}

}
