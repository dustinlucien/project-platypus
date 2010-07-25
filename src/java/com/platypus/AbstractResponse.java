package com.platypus;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public abstract class AbstractResponse {
	
	public abstract String getContent() throws IOException;
	
	public void writeTo(OutputStream out) throws IOException {
		Writer writer = new BufferedWriter(new OutputStreamWriter(out));
		writer.write(getContent());
		writer.flush();
		writer.close();
	}
}
