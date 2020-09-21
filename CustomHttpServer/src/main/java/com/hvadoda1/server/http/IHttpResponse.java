package com.hvadoda1.server.http;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.hvadoda1.server.IResponse;

public interface IHttpResponse extends IResponse {

	String setHeader(String headerName, String headerValue);

	String getHeader(String headerName);

	Map<String, String> getHeaders();

	void setStatus(HttpStatus status);

	void setVersionString(String versionString);

	void sendResponse(String responseType, String response);

	void sendResponse(String response);

	void sendHeaders();

	void sendResponseLn(String responseLine);

	String responseHeaderString();

	void respondWithFile(String sendFileName) throws IOException;

}
