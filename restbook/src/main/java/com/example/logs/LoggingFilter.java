package com.example.logs;

import jakarta.ws.rs.container.*;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Provider //use to make custom components
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final Logger LOG = Logger.getLogger(LoggingFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String method = requestContext.getMethod();
        String path = requestContext.getUriInfo().getPath();
//        System.out.println("time ================== " + getCurrentTimestamp());
        LOG.infof("Incoming request: %s %s", method, path);
        requestContext.setProperty("start-time", System.currentTimeMillis());
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        long startTime = (Long) requestContext.getProperty("start-time");
        long duration = System.currentTimeMillis() - startTime;
        int status = responseContext.getStatus();
//        System.out.println("time ================== " + getCurrentTimestamp());
        LOG.infof("Completed request: %s %s -> Status: %d | Time Taken: %d ms",
                requestContext.getMethod(), requestContext.getUriInfo().getPath(), status, duration);
    }

//    private String getCurrentTimestamp() {
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
//        return now.format(formatter);
//    }
}

