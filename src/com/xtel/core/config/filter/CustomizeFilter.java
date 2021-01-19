package com.xtel.core.config.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import java.io.IOException;

public class CustomizeFilter implements Filter {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("Initializing filter, config: {}" + filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("===> New request: " + (request == null ? "null" : request.toString()));

		if(chain != null) {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {

	}

}