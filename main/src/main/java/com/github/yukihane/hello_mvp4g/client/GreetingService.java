package com.github.yukihane.hello_mvp4g.client;

import com.github.yukihane.hello_mvp4g.shared.Information;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
    String greetServer(Information info) throws IllegalArgumentException;
}
