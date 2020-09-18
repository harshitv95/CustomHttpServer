package com.hvadoda1.server.http;

public enum HttpRequestType {
	GET, POST, PUT, HEAD, DELETE, OPTION, PATCH;

	public static HttpRequestType from(String s) {
		for (HttpRequestType value : values())
			if (value.name().equals(s))
				return value;
		return null;
	}
}
