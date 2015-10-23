package com.github.yukihane.hello_mvp4g.server;

import com.github.yukihane.hello_mvp4g.client.GreetingService;
import com.github.yukihane.hello_mvp4g.shared.FieldVerifier;
import com.github.yukihane.hello_mvp4g.shared.Information;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

    public String greetServer(Information info) throws IllegalArgumentException {
        // Verify that the input is valid.
        final String input = info.getName();
        if (!FieldVerifier.isValidName(input)) {
            // If the input is not valid, throw an IllegalArgumentException back
            // to
            // the client.
            throw new IllegalArgumentException("Name must be at least 4 characters long");
        }

        String serverInfo = getServletContext().getServerInfo();
        String userAgent = getThreadLocalRequest().getHeader("User-Agent");

        // Escape data from the client to avoid cross-site script
        // vulnerabilities.
        final String name = escapeHtml(input);
        userAgent = escapeHtml(userAgent);

        return "Good " + info.getTiming() + ", " + name + "!<br><br>I am running " + serverInfo
                + ".<br><br>It looks like you are using:<br>" + userAgent;
    }

    /**
     * Escape an html string. Escaping data received from the client helps to
     * prevent cross-site script vulnerabilities.
     *
     * @param html
     *            the html string to escape
     * @return the escaped string
     */
    private String escapeHtml(String html) {
        if (html == null) {
            return null;
        }
        return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }
}
