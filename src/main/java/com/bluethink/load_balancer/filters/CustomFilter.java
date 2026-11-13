package com.bluethink.load_balancer.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomFilter extends OncePerRequestFilter {

    private final Logger logger =LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        logger.info(
                "Incoming request | Instance Port: {} | URI: {} | Time: {}",
                request.getLocalPort(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        filterChain.doFilter(request, response);

        logger.info(
                "Outgoing response | Instance Port: {} | Status: {}",
                request.getLocalPort(),
                response.getStatus()
        );
    }
}
