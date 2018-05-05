package org.zhouwei.framework.controller;

import com.google.common.collect.Lists;
import org.zhouwei.framework.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

@Slf4j
public class ApiResultDtoMessageConverter extends AbstractHttpMessageConverter<ApiResultDto<?>> {

	@Override
	protected boolean supports(Class<?> clazz) {
		return ApiResultDto.class.isAssignableFrom(clazz);
	}

	@Override
	protected ApiResultDto<?> readInternal(Class<? extends ApiResultDto<?>> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		return null;
	}

	@Override
	protected void writeInternal(ApiResultDto<?> t, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		outputMessage.getBody().write(JsonUtils.object2json(t).getBytes(Charset.forName("UTF-8")));
	}
	
	@Override
	public List<MediaType> getSupportedMediaTypes(){
		return Lists.newArrayList(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON, MediaType.ALL);
	}

}