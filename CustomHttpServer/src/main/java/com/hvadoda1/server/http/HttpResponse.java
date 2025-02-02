package com.hvadoda1.server.http;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.hvadoda1.server.mime.util.MimeUtils;
import com.hvadoda1.server.tcp.TcpResponse;
import com.hvadoda1.server.util.HttpUtils;
import com.hvadoda1.util.DateTimeUtils;

public class HttpResponse extends TcpResponse implements IHttpResponse {

	protected final Map<String, String> headers = new HashMap<>();

	{
		headers.put("Server", Config.getServerName());
	}

	protected HttpStatus status = HttpStatus.HTTP_200;

	protected String versionString;

	protected boolean headersSent = false;

	public HttpResponse(Socket client) throws IOException {
		super(client);
	}

	@Override
	public String setHeader(String headerName, String headerValue) {
		if (!HttpUtils.isHeaderForbidden(headerName))
			return headers.put(headerName, headerValue);
		return getHeader(headerName);
	}

	@Override
	public String getHeader(String headerName) {
		return headers.get(headerName);
	}

	@Override
	public Map<String, String> getHeaders() {
		return new HashMap<String, String>(headers);
	}

	@Override
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	@Override
	public void sendResponse(String responseType, String response) {
		setHeader("Content-Type", responseType);
		sendResponse(response);
	}

	@Override
	public void sendResponse(String response) {
//		setHeader("Content-Length", response.getBytes("UTF-8").length);
		if (response != null && !response.trim().isEmpty())
			setHeader("Content-Length", response.getBytes().length + "");
		sendHeaders();
		sendResponseLn(response);
	}

	@Override
	public String responseHeaderString() {
		StringBuilder sb = new StringBuilder();
		sb.append(versionString).append(' ').append(status.toString()).append(System.lineSeparator());
		headers.forEach((k, v) -> sb.append(k).append(": ").append(v).append(System.lineSeparator()));
		return sb.append(System.lineSeparator()).toString();
	}

	@Override
	public void sendHeaders() {
		if (this.headersSent)
			throw new RuntimeException("Headers already sent, cannot send again");
		headers.put("Date", DateTimeUtils.getServerTimeString(new Date()));
		writeMessage(responseHeaderString());
		this.headersSent = true;
	}

	@Override
	public void sendResponseLn(String responseLine) {
		writeMessage(responseLine + System.lineSeparator());
	}

	@Override
	public void setVersionString(String versionString) {
		this.versionString = versionString;
	}

	@Override
	public void respondWithFile(String servedFileName) throws IOException {
		File servedFile = Config.getServedFile(servedFileName);
		if (!servedFile.exists() || !servedFile.isFile()) {
			setStatus(HttpStatus.HTTP_404);
			setHeader("Content-Type", "text/html");
			sendResponse(HttpUtils.error404ResponseHTML(servedFileName));
			return;
		}
		if (!servedFile.canRead()) {
			setStatus(HttpStatus.HTTP_403);
			sendResponse(HttpUtils.error403ResponseHTML(servedFileName));
			return;
		}

		setStatus(HttpStatus.HTTP_200);
		setHeader("Content-Type", MimeUtils.getMimeType(servedFile));
		headers.put("Content-Length", servedFile.length() + "");
		headers.put("Last-Modified", DateTimeUtils.getServerTimeString(new Date(servedFile.lastModified())));
		sendHeaders();

		MimeUtils.writeMimeToOutput(servedFile, getOutputStream());

	}

}
