package org.zhouwei.framework.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * httpServletResponse包装类，主要用来注入包装的output与printwrite，用来获取输出的内容
 *
 * @author zhouwei
 *
 */
@Slf4j
public class HttpServletResponseFrameworkWrapper extends javax.servlet.http.HttpServletResponseWrapper {

	private ResponseOutputStream outputStream = new ResponseOutputStream();

	private ResponsePrintWriter printWriter = new ResponsePrintWriter(this.outputStream);

	public HttpServletResponseFrameworkWrapper(HttpServletResponse response) {
		super(response);
	}
	
	@Override
	public PrintWriter getWriter() throws IOException {
		printWriter.setPrintWrite(this.getResponse().getWriter());
		return this.printWriter;
	}
	
	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		outputStream.setServletOutputStream(this.getResponse().getOutputStream());
		return this.outputStream;
	}
	
	public String getContent(){
		return printWriter.getContent() + outputStream.getContent();
	}
	
	public void destroy(){
		try {
			this.finalize();
		} catch (Throwable e) {
			log.debug("destroy EagleEyeHttpServletResponseWrapper fail ,e:{}",e);
		}
	}
	
	protected void finalize() throws Throwable {
		try {
			outputStream.close();
			printWriter.close();
			log.debug("destroy output and printWrite success");
		} catch (Exception e) {
			log.debug("destroy output and printWrite fail : e:{}",e);
		}
	}
}
