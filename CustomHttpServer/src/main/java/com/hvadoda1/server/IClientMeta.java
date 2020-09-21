package com.hvadoda1.server;

import java.io.Closeable;
import java.net.InetAddress;

public interface IClientMeta<CLIENT> extends Closeable {
	String hostname();

	InetAddress getInetAddress();

	CLIENT getClient();
}
