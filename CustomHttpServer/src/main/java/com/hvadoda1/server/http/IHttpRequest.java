package com.hvadoda1.server.http;

import java.util.Map;

import com.hvadoda1.server.IRequest;

public interface IHttpRequest extends IRequest {
	Map<String, String> getHeaders();

	String getHeader(String header);

	String getQueryString();

	String getVersionString();

	HttpRequestType getRequestType();
}
