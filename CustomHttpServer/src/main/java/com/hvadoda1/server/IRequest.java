package com.hvadoda1.server;

import java.io.IOException;

public interface IRequest {
	String readMessage() throws IOException;
}
