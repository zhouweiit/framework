package org.zhouwei.framework.jackson;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.zhouwei.framework.util.DateUtil;

public class CustomDateSerializer{
	
	public static class YYYYMMDDHHMMSS extends JsonSerializer<Date> {
		@Override  
	    public void serialize(Date value, JsonGenerator jgen,SerializerProvider provider) throws IOException,JsonProcessingException{  
	        jgen.writeString(DateUtil.DateToString(value,DateUtil.yyyyMMddHHmmss));  
	    } 
	}
	
	public static class YYYYMMDD extends JsonSerializer<Date> {
		@Override  
	    public void serialize(Date value, JsonGenerator jgen,SerializerProvider provider) throws IOException,JsonProcessingException{  
	        jgen.writeString(DateUtil.DateToString(value,DateUtil.yyyyMMdd));  
	    } 
	}

}
