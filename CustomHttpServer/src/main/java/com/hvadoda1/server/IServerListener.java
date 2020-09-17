package com.hvadoda1.server;

import java.io.IOException;

public interface IServerListener<CLMETA extends IClientMeta, REQ extends IRequest, RESP extends IResponse> {
	void onStart();

	void beforeClose();

	void onRequest(CLMETA client, REQ request, RESP response) throws InterruptedException, IOException;

//	void beforeResponse(IClientMeta client, IRequest request);
}
