package com.hvadoda1.server;

import java.io.Closeable;
import java.net.InetAddress;

public interface IClientMeta<CLIENT> extends Closeable {
	String clientHostname();

	InetAddress clientIpAddress();

	CLIENT getClient();
}
