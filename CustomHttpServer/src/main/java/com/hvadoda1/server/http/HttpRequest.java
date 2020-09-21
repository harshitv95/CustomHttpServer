package com.hvadoda1.server.http;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.hvadoda1.server.IClientMeta;
import com.hvadoda1.server.util.TcpUtils;

public class HttpRequest implements IHttpRequest {

	protected final Map<String, String> headers;
	protected HttpRequestType requestType;
	protected String queryString, versionString;
	protected final Socket client;
	protected final Function<String, Boolean> terminatorFn = (line) -> line == null || line.trim().isEmpty();

	public HttpRequest(IClientMeta<Socket> client) throws IOException {
		this(client.getClient());
	}
	
	public HttpRequest(Socket client) throws IOException {
		this(client, new HashMap<>());
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
		requestType = HttpRequestType.from(status[0]);
		queryString = status[1];
		versionString = status[2];

		int firstIdx;

		for (int i = 1; i < messageLines.length; ++i) {
			firstIdx = messageLines[i].indexOf(": ");
			if (firstIdx > -1)
				headers.put(messageLines[i].substring(0, firstIdx), messageLines[i].substring(firstIdx + 2));
		}
//		System.out.println("REQUEST TYPE	: " + requestType);
//		System.out.println("QUERY STRING	: " + queryString);
//		System.out.println("VERSION STRING	: " + versionString);

//		System.out.println("HEADERS:\n" + headers);
	}

	@Override
	public Map<String, String> getHeaders() {
		return new HashMap<String, String>(headers);
	}

	@Override
	public String getHeader(String header) {
		return this.headers.get(header);
	}

	@Override
	public String readMessage() throws IOException {
		return "";
	}

	@Override
	public String getQueryString() {
		return this.queryString;
	}

	@Override
	public String getVersionString() {
		return this.versionString;
	}

	@Override
	public HttpRequestType getRequestType() {
		return this.requestType;
	}

}
