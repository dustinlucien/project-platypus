package com.platypus.service;

import com.platypus.rest.RestClient;

import org.apache.http.HttpEntity
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

import org.apache.http.impl.client.BasicResponseHandler
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.HttpResponseException
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.message.BasicNameValuePair;
import org.apache.commons.lang.NotImplementedException;


class RestClientService implements RestClient {
  static transactional = false
  
	def httpClientService

	public Map get(String url, Map params, ResponseHandler handler = null) {
		if (url == null) {
			throw new IllegalArgumentException("Invalid URL submitted to GET request");
		}
		
		if (params) {
			
			log.debug('Params going into REST get(): ' + params)
			
			def queryString = null

			//should i look for a ? on the url that's already there?
			
			params.each {
				if (queryString) {
					queryString += '&'
				} else {
					queryString = '?'
				}
				queryString += it.key + "=" + it.value.encodeAsURL()
			}

			log.debug('Created a query string : ' + queryString)
			
			url += queryString
		}
		
		log.debug ('Full URL in REST: ' + url)

		return execute(new HttpGet(url), handler)
	}
	
	public Map post(String url, Map params, InputStream stream, ResponseHandler handler) {
		throw new NotImplementedException("Post with an InputStream not yet implemented");
	}
	
	public Map post(String url, Map params, ResponseHandler handler = null) {
		def method = new HttpPost(url)
		
		if (params) {
			log.debug("Params going into REST.post(): " + params)
			
			def postParams = []
			params.each {
				postParams.add(new BasicNameValuePair(it.key, it.value))
			}
			
			method.setEntity(new UrlEncodedFormEntity(postParams))
		}

		return execute(method, handler)
	}
	
	public Map put(String url, Map params, ResponseHandler handler) {
		throw new NotImplementedException("HTTP PUT not yet implemeneted");
	}

	public Map delete(String url, Map params, ResponseHandler handler) {
		throw new NotImplementedException("HTTP DELETE not yet implemeneted");
	}

	public Map head(String url, Map params, ResponseHandler handler) {
		throw new NotImplementedException("HTTP HEAD not yet implemeneted");		
	}
	
	protected Map execute(HttpUriRequest method, ResponseHandler handler = null) throws IOException {
		return httpClientService.execute(method, (handler == null) ? new BasicResponseHandler() : handler);
	}
}