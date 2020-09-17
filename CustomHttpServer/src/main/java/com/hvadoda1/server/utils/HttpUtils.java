package com.hvadoda1.server.utils;

import java.util.HashSet;
import java.util.Set;

public class HttpUtils {
	public static final Set<String> forbiddenHeaderNames = new HashSet<>() {
		private static final long serialVersionUID = -1397201590486810358L;

		{
			add("accept-charset");
			add("accept-encoding");
			add("access-control-request-headers");
			add("access-control-request-method");
			add("connection");
			add("content-length");
			add("cookie");
			add("cookie2");
			add("date");
			add("dnt");
			add("expect");
			add("feature-policy");
			add("host");
			add("keep-alive");
			add("origin");
			add("proxy-");
			add("sec-");
			add("referer");
			add("te");
			add("trailer");
			add("transfer-encoding");
			add("upgrade");
			add("via");

			add("content-length");
		}
	};

	public static final boolean isHeaderForbidden(String name) {
		name = name.toLowerCase();
		return name.startsWith("proxy-") || name.startsWith("sec-") || forbiddenHeaderNames.contains(name);
	};
}
