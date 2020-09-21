package com.hvadoda1.server.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Function;

public class TcpUtils {
	public static String readMessage(Socket client, Function<String, Boolean> terminator) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null && !terminator.apply(line))
			sb.append(line).append(System.lineSeparator());

		return sb.toString();
	}

	public static boolean isPortAvailable(int port) throws IOException {
		try (ServerSocket server = new ServerSocket(port)) {
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static int getAvailablePort() throws IOException {
		return getAvailablePort(8080);
	}

	public static int getAvailablePort(int startingPort) throws IOException {
		while (startingPort <= 65535)
			if (isPortAvailable(startingPort))
				return startingPort;
			else
				startingPort++;
		return -1;
	}
}
