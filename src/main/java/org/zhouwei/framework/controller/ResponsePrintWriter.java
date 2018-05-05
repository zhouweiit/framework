package org.zhouwei.framework.controller;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * 包装的printWriter
 * 
 * @author zhouwei
 */
public class ResponsePrintWriter extends PrintWriter{
	
	private PrintWriter printWriter;
	
	private final String lineSeparator;
	
	private StringBuffer sb = new StringBuffer();
   
    @SuppressWarnings("restriction")
	public ResponsePrintWriter(OutputStream out) {
    	super(out);
        lineSeparator = java.security.AccessController.doPrivileged(new sun.security.action.GetPropertyAction("line.separator"));
    }
    
    public void setPrintWrite(PrintWriter printWriter){
    	this.printWriter = printWriter;
    }

	public String getContent() {
		return sb.toString();
	}

    public void flush() {
    	printWriter.flush();
    	
    }
    
    public void close(){
		if (printWriter != null){
			printWriter.close();
		}
		super.close();
		this.printWriter = null;
		this.sb = null;
    }

    public boolean checkError() {
        return printWriter.checkError();
    }
    
    public void write(int c) {
    	printWriter.write(c);
    	sb.append(c);
    }

    public void write(char buf[], int off, int len) {
    	printWriter.write(buf, off, len);
    	sb.append(buf, off, len);
    }

    public void write(char buf[]) {
    	printWriter.write(buf, 0, buf.length);
    	sb.append(buf, 0, buf.length);
    }

    public void write(String s, int off, int len) {
    	printWriter.write(s, off, len);
    	sb.append(s);
    }

    public void write(String s) {
    	printWriter.write(s, 0, s.length());
    	sb.append(s);
    }

    private void newLine() {
    	write(lineSeparator);
    }

    public void print(boolean b) {
        write(b ? "true" : "false");
    }

    public void print(char c) {
        write(c);
    }

    public void print(int i) {
        write(String.valueOf(i));
    }

    public void print(long l) {
        write(String.valueOf(l));
    }

    public void print(float f) {
        write(String.valueOf(f));
    }
    
    public void print(double d) {
        write(String.valueOf(d));
    }
 
    public void print(char s[]) {
        write(s);
    }

    public void print(String s) {
        if (s == null) {
            s = "null";
        }
        write(s);
    }

    public void print(Object obj) {
        write(String.valueOf(obj));
    }

    public void println() {
        newLine();
    }

    public void println(boolean x) {
        synchronized (lock) {
            print(x);
            println();
        }
    }

    public void println(char x) {
        synchronized (lock) {
            print(x);
            println();
        }
    }

    public void println(int x) {
        synchronized (lock) {
            print(x);
            println();
        }
    }

    public void println(long x) {
        synchronized (lock) {
            print(x);
            println();
        }
    }

    public void println(float x) {
        synchronized (lock) {
            print(x);
            println();
        }
    }

    public void println(double x) {
        synchronized (lock) {
            print(x);
            println();
        }
    }

    public void println(char x[]) {
        synchronized (lock) {
            print(x);
            println();
        }
    }

    public void println(String x) {
        synchronized (lock) {
            print(x);
            println();
        }
    }

    public void println(Object x) {
        String s = String.valueOf(x);
        synchronized (lock) {
            print(s);
            println();
        }
    }

    public PrintWriter printf(String format, Object ... args) {
    	printWriter.printf(format, args);
    	return this;
    }

    public PrintWriter printf(Locale l, String format, Object ... args) {
    	printWriter.printf(l, format, args);
    	return this;
    }

    public PrintWriter format(String format, Object ... args) {
    	printWriter.format(format, args);
        return this;
    }

    public PrintWriter format(Locale l, String format, Object ... args) {
    	printWriter.format(l, format, args);
        return this;
    }

    public PrintWriter append(CharSequence csq) {
        if (csq == null)
            write("null");
        else
            write(csq.toString());
        return this;
    }

    public PrintWriter append(CharSequence csq, int start, int end) {
        CharSequence cs = (csq == null ? "null" : csq);
        write(cs.subSequence(start, end).toString());
        return this;
    }

    public PrintWriter append(char c) {
        write(c);
        return this;
    }
	
}
