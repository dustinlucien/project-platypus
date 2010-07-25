package com.platypus;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.platypus.AbstractResponse;

public class StringResponse extends AbstractResponse implements Response<String> {

	private String data;
	
	public StringResponse(String data) {
		this.data = data;
	}
	
	public int getStatusCode() {
		return 200;
	}

	public boolean hasContent() {
		return true;
	}

	public String getContent() throws IOException {
		return data;
	}

	public String getContentDecoded() throws IOException {
		return data;
	}

	public String getContentEncoding() {
		return "UTF-8";
	}

	public String getContentType() {
		return "text/plain";
	}

	public void writeTo(OutputStream out) throws IOException {
		Writer writer = new BufferedWriter(new OutputStreamWriter(out));
		writer.write(data);
		writer.flush();
		writer.close();
	}
	

}
