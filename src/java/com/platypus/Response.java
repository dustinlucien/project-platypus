package com.platypus;

import java.io.OutputStream;
import java.io.IOException;

public interface Response<T> {
	public int getStatusCode();
	public boolean hasContent();
	public String getContent() throws IOException;
	public T getContentDecoded() throws IOException;
	public String getContentType();
	public String getContentEncoding();
	public void writeTo(OutputStream out) throws IOException;
}