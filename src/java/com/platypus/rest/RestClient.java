package com.platypus.rest;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.apache.http.client.ResponseHandler;

public interface RestClient {
	public Map get(String url, Map params, ResponseHandler handler);
	public Map post(String url, Map params, ResponseHandler handler);
	public Map post(String url, Map params, InputStream stream, ResponseHandler handler);
	public Map put(String url, Map params, ResponseHandler handler);
	public Map delete(String url, Map params, ResponseHandler handler);
	public Map head(String url, Map params, ResponseHandler handler);
}
