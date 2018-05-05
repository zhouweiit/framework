package org.zhouwei.framework.controller;


import org.zhouwei.framework.util.HttpHelper;
import org.zhouwei.framework.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;

/**
 * 将request body中的json数据存入request的ParameterMap
 * 便于controller中方法参数自动注入
 *
 */
@Slf4j
public class ApiResetRequestBodyToParameterFilter implements Filter {

	private static String API_URL = "/api";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (((HttpServletRequestWrapper) request).getRequestURI().startsWith(API_URL) && request.getContentType() != null && request.getContentType().contains("json")){
			BodyReaderHttpServletRequestWrapper requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequestWrapper)request);
            String body = HttpHelper.getBodyString(requestWrapper);
            log.info("init request:{}", body);
            Map<String, Object> paramsMap = JsonUtils.json2object(body, Map.class);
            if(paramsMap != null && paramsMap.size() > 0){
            	for(Map.Entry<String, Object> entry : paramsMap.entrySet()){
            		requestWrapper.addParameter(entry.getKey(), entry.getValue());
            	}
            }
            log.info("wrapper request:{}", requestWrapper.getParameterMap().toString());
            chain.doFilter(requestWrapper,response);
		}else{
			chain.doFilter(request,response);
		}
	}

	@Override
	public void destroy() {
	}

}
