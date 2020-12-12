package com.bootproj.pmcweb.Common.Intercepter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@RequiredArgsConstructor
@Component
public class LoggingInterceptor extends HandlerInterceptorAdapter {
    private final ObjectMapper objectMapper;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (request.getClass().getName().contains("SecurityContextHolderAwareRequestWrapper")) return;
        final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
        final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;

        if (cachingRequest.getContentType() != null && cachingRequest.getContentType().contains("application/json")) {
            if (cachingRequest.getContentAsByteArray() != null && cachingRequest.getContentAsByteArray().length != 0){
                log.info("Request Body : {}", objectMapper.readTree(cachingRequest.getContentAsByteArray()));
            }
        }
        if (cachingResponse.getContentType() != null && cachingResponse.getContentType().contains("application/json")) {
            if (cachingResponse.getContentAsByteArray() != null && cachingResponse.getContentAsByteArray().length != 0) {
                log.info("Response Body : {}", objectMapper.readTree(cachingResponse.getContentAsByteArray()));
            }
        }
    }
}
