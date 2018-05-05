package org.zhouwei.framework.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 *
 * ApiController的通用Result接口
 *
 * @author zhouwei
 *
 */
@Data
public class ApiResultDto<T>{
	
	@JsonProperty("resultCode")
	private Integer resultCode;
	
	@JsonProperty("resultMessage")
	private String resultMessage;
	
	@JsonProperty("result")
	@JsonInclude(Include.NON_NULL) 
	private T result;
	
	public static <E> ApiResultDto<E> getInstance(){
		ApiResultDto<E> dto = new ApiResultDto<E>();
		dto.setResultCode(200);
		dto.setResultMessage("请求成功");
		return dto;
	}
	
	public static <E> ApiResultDto<E> getInstanceError(){
		ApiResultDto<E> dto = new ApiResultDto<E>();
		dto.setResultCode(0);
		dto.setResultMessage("请求失败");
		return dto;
	}

}
