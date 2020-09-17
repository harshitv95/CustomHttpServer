package com.hvadoda1.server;

import java.net.InetAddress;

public interface IClientMeta {
	String clientHostname();

	InetAddress clientIpAddress();
}
