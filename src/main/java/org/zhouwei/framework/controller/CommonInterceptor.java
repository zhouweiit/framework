package org.zhouwei.framework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * 通用的拦截器，主要做一些全局的配置，处理，输出等等。
 * 
 * @author zhouwei
 */
@Slf4j
public class CommonInterceptor implements HandlerInterceptor {
	
	/**
	 * 唯一的session_id
	 */
	public static final ThreadLocal<String> requestSessionId = new ThreadLocal<String>();
	
	/**
	 * 记录请求中中间过程无法方便保存的请求数据，大家可以根据自己的需要处理
	 */
	public static final ThreadLocal<HashMap<String,Object>> requestDataMap = new ThreadLocal<HashMap<String,Object>>();
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uuid = UUID.randomUUID().toString();
		String url = request.getRequestURI();
		long timestamp = new Date().getTime();
		requestSessionId.set(uuid);
		requestDataMap.set(new HashMap<String, Object>());
		requestDataMap.get().put("CommenInterceptor_start",timestamp);
		log.info("request start,url :{},uuid:{},timestamp:{}",url,uuid,timestamp);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		String uuid = requestSessionId.get();
		String url = request.getRequestURI();
		HashMap<String,Object> dataMap = requestDataMap.get();
		Long requestStartTime = Long.valueOf(dataMap.get("CommenInterceptor_start").toString());
		long requestEndTime = new Date().getTime();
		log.info("request end,url :{} uuid:{},elapsed:{}",url,uuid,requestEndTime - requestStartTime);
	}

}