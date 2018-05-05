package org.zhouwei.framework.controller;

import org.zhouwei.framework.util.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import java.util.Map;

/**
 * 响应拦击器，做参数的注入等
 * 
 * @author zhouwei
 */
public class ResponseInterceptor implements WebRequestInterceptor {

	@Override
	public void preHandle(WebRequest request) throws Exception {
	}

	@Override
	public void postHandle(WebRequest request, ModelMap model) throws Exception {
		if (model == null){
			return;
		}
		ServletWebRequest servletWebRequest = (ServletWebRequest)request;
		Map<String, String[]> parameterMap = servletWebRequest.getParameterMap();
		for (String key : parameterMap.keySet()){
			String[] value = parameterMap.get(key);
			if (value == null || value.length == 0){
				model.put(key,"");
				continue;
			}
			if (value.length == 1){
				model.put(key, value[0]);
			} else {
				model.put(key, StringUtils.implode(value, ","));
			}
		}
	}

	@Override
	public void afterCompletion(WebRequest request, Exception ex) throws Exception {
		
	}
	
}
