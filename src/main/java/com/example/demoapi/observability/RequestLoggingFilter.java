package com.example.demoapi.observability;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.util.OnCommittedResponseWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import java.io.IOException;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override   
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain)
        throws ServletException, IOException {
            
            long startTime = System.currentTimeMillis();

        try{
            filterChain.doFilter(request, response);
        } finally{
            long duration = System.currentTimeMillis() - startTime;
            
            logger.info(
                "HTTP {} {} -> status={} duration={}ms", 
                request.getMethod(),
                request.getRequestURI(),
                response.getStatus(),
                duration
            );
        }
    }
}
