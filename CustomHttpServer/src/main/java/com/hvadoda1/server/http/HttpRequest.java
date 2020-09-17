package com.hvadoda1.server.http;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.hvadoda1.server.tcp.TcpRequest;
import com.hvadoda1.server.utils.TcpUtils;

public class HttpRequest implements IHttpRequest {

	protected final Map<String, String> headers;
	protected RequestType requestType;
	protected String queryString, versionString;
	protected final Socket client;
	protected final Function<String, Boolean> terminatorFn = (line) -> line == null || line.trim().isEmpty();

	public HttpRequest(Socket client) throws IOException {
		this(client, new HashMap<>());
	}

	public HttpRequest(TcpRequest req) throws IOException {
		this(req.getClient());
	}

	public HttpRequest(Socket client, Map<String, String> headers) throws IOException {
		this.client = client;
		this.headers = headers;
		initialize();
	}

	protected void initialize() throws IOException {
		String message = TcpUtils.readMessage(client, terminatorFn);
		if (message == null)
			return;
		String[] messageLines = message.trim().split(System.lineSeparator());
		if (messageLines.length == 0)
			return;

		String status[] = messageLines[0].split("\\s+");
		if (status.length != 3)
			return;
		requestType = RequestType.from(status[0]);
		queryString = status[1];
		versionString = status[2];
		System.out.println(message);
	}

	@Override
	public Map<String, String> getHeaders() {
		return new HashMap<String, String>(headers);
	}

	@Override
	public String getHeader(String header) {
		return this.headers.get(header);
	}

	public static enum RequestType {
		GET, POST, PUT, HEAD, DELETE, OPTION, PATCH;

		public static RequestType from(String s) {
			for (RequestType value : values())
				if (value.equals(s))
					return value;
			return null;
		}
	}

	@Override
	public String readMessage() throws IOException {
		return "";
	}

}
