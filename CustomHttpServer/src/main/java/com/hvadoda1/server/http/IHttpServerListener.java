package com.hvadoda1.server.http;

import java.net.Socket;

import com.hvadoda1.server.IClientMeta;
import com.hvadoda1.server.IServerListener;

public interface IHttpServerListener extends IServerListener<Socket, IClientMeta<Socket>, IHttpRequest, IHttpResponse> {

}
