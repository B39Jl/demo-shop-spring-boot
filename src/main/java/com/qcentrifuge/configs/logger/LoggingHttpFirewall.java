package com.qcentrifuge.configs.logger;


import org.springframework.security.web.firewall.FirewalledRequest;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class LoggingHttpFirewall extends StrictHttpFirewall {

    public LoggingHttpFirewall()
    {
        super();
    }

    @Override
    public FirewalledRequest getFirewalledRequest(final HttpServletRequest request) throws RequestRejectedException {
        try {
            request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, "404");
            return super.getFirewalledRequest(request);
        } catch (RequestRejectedException ex) {
           throw new RequestRejectedException(ex.getMessage()){
               @Override
               public synchronized Throwable fillInStackTrace() {
                   return this;
               }
           };

        }
    }

    @Override
    public HttpServletResponse getFirewalledResponse(final HttpServletResponse response) {
        return super.getFirewalledResponse(response);
    }
}