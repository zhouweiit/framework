package org.zhouwei.framework.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器：HttpServletResponseFrameworkWrapper包装response类的注入
 * @author zhouwei
 */
public class FilterResponseWrapper implements Filter {

	private HttpServletResponseFrameworkWrapper responseWrapper;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		responseWrapper = new HttpServletResponseFrameworkWrapper((HttpServletResponse)response);
		chain.doFilter(request,responseWrapper);
	}

	@Override
	public void destroy() {
		if (responseWrapper != null) {
			responseWrapper.destroy();
		}
	}

}
