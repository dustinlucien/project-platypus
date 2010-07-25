package com.platypus.json.helpers;

import grails.converters.JSON;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.log4j.Logger;

import org.codehaus.groovy.grails.web.json.*;

public class JsonHttpResponseHandler implements ResponseHandler<JSONElement> {

	private Logger log = Logger.getLogger(JsonHttpResponseHandler.class);
	
	public JSONElement handleResponse(final HttpResponse response)
			throws ClientProtocolException, IOException {
		log.debug("Inside JsonHttpResponseHandler");
		
		StatusLine statusLine = response.getStatusLine();
		if (statusLine.getStatusCode() >= 300) {
			throw new HttpResponseException(statusLine.getStatusCode(), 
											statusLine.getReasonPhrase());
		}
		/*
		 * TODO:Maybe i should try to figure out the actual encoding before assuming
		 */
		HttpEntity entity = response.getEntity();
        
		if (entity == null) {
			log.debug("HttpEntity in response is NULL");
            return null;
        }
        
        InputStream instream = entity.getContent();
        if (instream == null) {
        	log.debug("InputStream in HttpEntity is NULL");
            return null;
        }
        
        if (entity.getContentLength() > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
        }
        
        String charset = getContentCharSet(entity);        
        if (charset == null) {
        	log.debug("NULL charset.  Forcing to UTF-8");
        	charset = "UTF-8";
        }
        
        return JSON.parse(new InputStreamReader(instream, charset));
	}
	
    private String getContentCharSet(final HttpEntity entity)
    	throws ParseException {

	    if (entity == null) {
	        throw new IllegalArgumentException("HTTP entity may not be null");
	    }
	    String charset = null;
	    if (entity.getContentType() != null) { 
	        HeaderElement values[] = entity.getContentType().getElements();
	        if (values.length > 0) {
	            NameValuePair param = values[0].getParameterByName("charset");
	            if (param != null) {
	                charset = param.getValue();
	            }
	        }
	    }
	    return charset;
    }

}
