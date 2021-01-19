package com.xtel.core.config.server;

import com.xtel.core.config.filter.CorsFilter;
import com.xtel.core.config.filter.CustomizeFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class RestfulServer {

	protected final Log logger = LogFactory.getLog(this.getClass());

	protected String packages;
	protected int port;
	protected String contextPath;
	protected String pathSpec;

	protected ServletHolder servlet;
	protected Server server;
	protected ServletContextHandler context;

	public RestfulServer(String packages, int port, String contextPath, String pathSpec) {
		super();
		this.packages = packages;
		this.port = port;
		this.contextPath = contextPath;
		this.pathSpec = pathSpec;
	}

	public void publish() throws Exception {

		ResourceConfig config = new ResourceConfig();
		config.packages(packages);
		config.register(CorsFilter.class);
		config.register(MultiPartFeature.class);
		ServletHolder servlet = new ServletHolder(new ServletContainer(config));

		server = new Server(port);
		context = new ServletContextHandler(server, contextPath);
		context.addServlet(servlet, pathSpec);

		context.addFilter(CustomizeFilter.class, "/*", null);
		FilterHolder corsFilter = context.addFilter(CrossOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));

		corsFilter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
		corsFilter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_CREDENTIALS_HEADER, "*");
		corsFilter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
		corsFilter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "*");
		corsFilter.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "*");
		corsFilter.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");

		try {
			server.start();
		} catch (Exception e) {
			logger.error("", e);
			throw e;
		}
	}

	public void shutdown() {

		if (server != null) {
			server.destroy();
		}
	}

}

