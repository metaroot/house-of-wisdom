package com.houseofwisdom.houseofwisdom.filters;

import org.jboss.logging.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class Slf4jMDCFilter extends OncePerRequestFilter {
    private final String responseHeader;
    private final String mdcTokenKey;
    private final String requestHeader;

    public Slf4jMDCFilter(String responseHeader, String mdcTokenKey, String requestHeader) {
        this.responseHeader = responseHeader;
        this.mdcTokenKey = mdcTokenKey;
        this.requestHeader = requestHeader;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain chain) {
        try {
            final String token;
            if(requestHeader != null
            && request.getHeader(requestHeader) != null) {
                token = request.getHeader(requestHeader);
            } else {
                token = UUID.randomUUID().toString().toUpperCase().replace("-", "");
            }
            MDC.put(mdcTokenKey, "REQ_ID::"+token);

            if(!StringUtils.hasLength(responseHeader)) {
                response.addHeader(responseHeader, token);
            }
            chain.doFilter(request, response);
        } catch (IOException | ServletException exception) {
            exception.printStackTrace();
        } finally {
            org.slf4j.MDC.remove(mdcTokenKey);
        }
    }
}
