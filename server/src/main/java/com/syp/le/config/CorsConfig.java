package com.syp.le.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * 
 * This configuration allows global CORS for all endpoints
 * 
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since 29 Jul 2019
 */
@Component
public class CorsConfig implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,HEAD,POST,PUT,PATCH,DELETE,OPTIONS,TRACE");
		httpServletResponse.setHeader("Access-Control-Allow-Headers",
				"Origin,Accept,Content-Type,X-Requested-With,Access-Control-Request-Method,Access-Control-Request-Headers,Access-Control-Allow-Headers,Access-Control-Allow-Origin");
		// httpServletResponse.setHeader("Access-Control-Expose-Headers", "");
	}

	@Override
	public void destroy() {

	}
}
