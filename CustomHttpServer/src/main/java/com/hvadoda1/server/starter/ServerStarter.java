package com.hvadoda1.server.starter;

import java.io.File;
import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.hvadoda1.server.IClientMeta;
import com.hvadoda1.server.http.Config;
import com.hvadoda1.server.http.HttpServer;
import com.hvadoda1.server.http.IHttpRequest;
import com.hvadoda1.server.http.IHttpResponse;
import com.hvadoda1.server.http.IHttpServerListener;
import com.hvadoda1.server.http.simple.SimpleHttpServer;
import com.hvadoda1.server.util.TcpUtils;
import com.hvadoda1.util.CommonUtils;
import com.hvadoda1.util.DateTimeUtils;
import com.hvadoda1.util.Logger;
import com.hvadoda1.util.Logger.Level;

public class ServerStarter {

	public static void main(String[] args) throws IOException, InterruptedException {

		Map<String, String> argMap = CommonUtils.parseArgsMap(args);
		Map<String, Integer> resReqCount = new HashMap<String, Integer>();

		new Config();

		try (HttpServer server = new SimpleHttpServer(argMap.getOrDefault("name", "CustomHttpServer"), -1);
				Logger log = new Logger(Level.from(Integer.parseInt(argMap.getOrDefault("level", "3"))),
						"logs" + File.separator + "log_" + (DateTimeUtils.getLogFileNameDateString()) + ".txt");) {

			server.registerListener(new IHttpServerListener() {

				@Override
				public void onStart() {
					try {
						Logger.info("SERVER STARTED AT FOLLOWING ADDRESS:\n"
								+ InetAddress.getLocalHost().getCanonicalHostName() + ":" + server.port());
					} catch (UnknownHostException e) {
						throw new RuntimeException("Error while getting current hostname", e);
					}
				}

				@Override
				public void beforeClose() {
				}

				@Override
				public void onRequest(IClientMeta<Socket> client, IHttpRequest request, IHttpResponse response)
						throws InterruptedException, IOException {
					resReqCount.put(request.getQueryString(),
							resReqCount.getOrDefault(request.getQueryString(), 0) + 1);
					int count = resReqCount.get(request.getQueryString());

					Logger.info(request.getQueryString() + "|" + client.getInetAddress().getHostAddress() + "|"
							+ client.getClient().getPort() + "|" + count);
				}

			});

			int port = 80;
			if (argMap.containsKey("port")) {
				port = Integer.parseInt(argMap.get("port"));
				if (!TcpUtils.isPortAvailable(port))
					throw new BindException(
							"Input port number [" + port + "] is already in use, please input a different port number");
			} else if (!TcpUtils.isPortAvailable(port))
				port = TcpUtils.getAvailablePort(8080);

			server.serve(port);
		} catch (Exception e) {
			Logger.error("Exception while serving", e);
		}

	}

}
