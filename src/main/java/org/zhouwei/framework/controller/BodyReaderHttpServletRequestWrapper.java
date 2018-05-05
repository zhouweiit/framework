package org.zhouwei.framework.controller;

import com.google.common.collect.Lists;
import org.zhouwei.framework.util.HttpHelper;
import org.zhouwei.framework.util.JsonUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;

/**
 *
 * 封装request
 * 重载request获取parameter的相关方法，以便于程序修改request的parameterMap
 *
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

	/**
	 * 用于保存http request 流数据
	 * 解决问题：在filter中读取流中的body数据，导致后续controller无法读取，用body缓存流之后，再重新输出
	 */
    private final byte[] body;
    
    /**
     * 保存 request 中的paramsMap
     * 解决问题：request中默认的paramsMap无法修改，通过重载相关方法，直接操作params，将其当做作为paramsMap
     */
    private Map<String , String[]> params = new HashMap<String, String[]>();
    
    private List<ByteArrayInputStream> inputStream = Lists.newArrayList();

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.body = HttpHelper.getBodyString(request).getBytes(Charset.forName("UTF-8"));
        this.params.putAll(request.getParameterMap());
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        inputStream.add(bais);
        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return bais.read();
            }
            @Override
            public boolean isFinished() {
                return false;
            }
            @Override
            public boolean isReady() {
                return false;
            }
            @Override
            public void setReadListener(ReadListener readListener) {
            }
        };
    }
    
    @Override
    public String getParameter(String name) {//重写getParameter，代表参数从当前类中的map获取
        String[]values = params.get(name);
        if(values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }
    
    @Override
    public Enumeration<String> getParameterNames(){
    	return Collections.enumeration(this.params.keySet());
    }
    
    @Override
    public Map<String, String[]> getParameterMap(){
    	return this.params;
    }
    
    @Override
    public String[] getParameterValues(String name){
    	return this.params.get(name);
    }
    
    public void addParameter(String name , Object value) {//增加参数
        if(value != null) {
            if(value instanceof String[]) {
                params.put(name, (String[])value);
            }else if(value instanceof String) {
                params.put(name, new String[] {(String)value});
            }else if(value instanceof Integer || value instanceof Double || value instanceof Long || value instanceof Float || value instanceof Boolean){
                params.put(name, new String[] {String.valueOf(value)});
            }else if(value instanceof ArrayList<?>){	
            	List<?> valueList = (List<?>)value;
            	List<String> resultList = Lists.newArrayList();
            	Boolean isSimpleTypeArray = true;	//标记 当前value是否为简单类型的数组
            	int valueListSize = valueList.size();
            	for(int i=0; i<valueListSize; i++ ){
            		Object currentValue = valueList.get(i);
            		if(currentValue instanceof String){
            			resultList.add((String)currentValue);
            		}else if(currentValue instanceof Integer || currentValue instanceof Double || currentValue instanceof Long || currentValue instanceof Float || currentValue instanceof Boolean){
            			resultList.add(String.valueOf(valueList.get(i)));
            		}else{
            			isSimpleTypeArray = false;
            		}
            	}
            	params.put(name, isSimpleTypeArray ? resultList.toArray(new String[valueListSize]): new String[] {JsonUtils.object2json(value)});
            }else{	//非简单类型 且非数组，直接将字符串返回controller
            	params.put(name,new String[] {JsonUtils.object2json(value)});
            }
        }
    }
    
    /**
     * 销毁stream
     * @author ZHOUWE02
     */
    public void destroy(){
    	for (ByteArrayInputStream stream : inputStream){
    		try {
    			stream.close();
			} catch (Exception e) {
			}
    	}
    }
 
}
