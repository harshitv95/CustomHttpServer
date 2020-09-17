package com.hvadoda1.server.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
}
