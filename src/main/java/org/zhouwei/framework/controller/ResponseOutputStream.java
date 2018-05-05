package org.zhouwei.framework.controller;

import com.google.common.collect.Lists;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 包装的ServletOutputStream
 * 
 * @author zhouwei
 */
public class ResponseOutputStream extends ServletOutputStream {
	
	private ServletOutputStream outputStream;
	
	private StringBuffer sb = new StringBuffer();
	
	private List<Byte> bytes = Lists.newArrayList();
	
	private static final String LSTRING_FILE = "javax.servlet.LocalStrings";
	
    private static ResourceBundle lStrings = ResourceBundle.getBundle(LSTRING_FILE);
	
	public ResponseOutputStream(){
	}
	
	public void setServletOutputStream(ServletOutputStream outputStream){
		this.outputStream = outputStream;
	}
	
	public String getContent() {
		if (bytes.size() > 0) {
			byte[] byteTmp = new byte[bytes.size()];
			int mark = 0;
			for (Byte byteInfo : bytes){
				byteTmp[mark++] = byteInfo;
			}
			return new String(byteTmp,Charset.forName("UTF-8"));
		} else {
			return sb.toString();
		}
	}
	
	public void print(String s) throws IOException {
        if (s == null) {
        	s = "null";
        }
        outputStream.print(s);
        sb.append(s);
    }

    public void print(boolean b) throws IOException {
        String msg;
        if (b) {
            msg = lStrings.getString("value.true");
        } else {
            msg = lStrings.getString("value.false");
        }
        print(msg);
    }

    public void print(char c) throws IOException {
    	print(String.valueOf(c));
    }

    public void print(int i) throws IOException {
    	print(String.valueOf(i));
    }

    public void print(long l) throws IOException {
    	print(String.valueOf(l));
    }

    public void print(float f) throws IOException {
    	print(String.valueOf(f));
    }

    public void print(double d) throws IOException {
    	print(String.valueOf(d));
    }

    public void println() throws IOException {
        String msg = "\r\n";
        print(msg);
    }

    public void println(String s) throws IOException {
        print(s);
        println();
    }

    public void println(boolean b) throws IOException {
        print(b);
        println();
    }

    public void println(char c) throws IOException {
        print(c);
        println();
    }

    public void println(int i) throws IOException {
        print(i);
        println();
    }

    public void println(long l) throws IOException {
        print(l);
        println();
    }

    public void println(float f) throws IOException {
        print(f);
        println();
    }

    public void println(double d) throws IOException {
        print(d);
        println();
    }

    public boolean isReady(){
    	return outputStream.isReady();
    }

    public void setWriteListener(WriteListener writeListener){
    	outputStream.setWriteListener(writeListener);
    }

	@Override
	public void write(int b) throws IOException {
		outputStream.write(b);
		bytes.add(Byte.valueOf(String.valueOf(b)));
	}
	
	@Override
	public void close() throws IOException{
		if (outputStream != null){
			outputStream.close();
		}
		super.close();
		this.sb = null;
		this.bytes = null;
		this.outputStream = null;
	}
}