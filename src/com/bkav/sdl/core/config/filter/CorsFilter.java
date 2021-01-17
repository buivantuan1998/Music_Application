package com.bkav.sdl.core.config.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CorsFilter implements ContainerRequestFilter, ContainerResponseFilter {

	protected final Log logger = LogFactory.getLog(this.getClass());
	protected String origin;

	private static boolean isPreflightRequest(ContainerRequestContext request) {
		return request.getHeaderString("Origin") != null && request.getMethod().equalsIgnoreCase("OPTIONS");
	}

	@Override
	public void filter(ContainerRequestContext request) throws IOException {
		origin = request.getHeaderString("Origin");
		logger.info(String.format("-----> New request: %s, Method: %s, getHeaderString(\"Origin\"): %s" ,
				request.getUriInfo().getRequestUri(),
				request.getMethod(), origin));
		if (isPreflightRequest(request)) {
			request.abortWith(Response.ok().build());
		}
	}

	@Override
	public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
		if (request == null || request.getHeaderString("Origin") == null) {
			return;
		}
		origin = request.getHeaderString("Origin");
		if (isPreflightRequest(request)) {
			if (response != null) {
				response.getHeaders().putSingle("Access-Control-Allow-Credentials", "true");
				response.getHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
				response.getHeaders().putSingle("Access-Control-Allow-Headers", "*");
				response.getHeaders().putSingle("Content-Type", "application/json; charset=utf-8");
			}
		}

		if (response != null) {
			Set<String> allowedOrigins = new HashSet<String>(Arrays.asList (origin));

			response.getHeaders().putSingle("Access-Control-Allow-Origin", allowedOrigins.contains(origin) ? origin : "");
		}
	}
}
