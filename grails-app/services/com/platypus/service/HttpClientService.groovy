package com.platypus.service

import java.util.Map;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicResponseHandler
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.HttpResponseException
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.message.BasicNameValuePair;
import org.apache.commons.lang.NotImplementedException;


class HttpClientService {

  boolean transactional = false

	private Map execute(HttpUriRequest method, ResponseHandler handler = null) throws IOException {
		def client = new DefaultHttpClient()
		
		try {
			def response = client.execute(method, (handler == null) ? internalHandler : handler);
			if (response == null) {
				return [ response : null, code : 500 ]
			} else {
				return [ response : response, code : 200 ]
			}
		} catch (HttpResponseException e) {
			return [ response : e.getMessage(), code : e.getStatusCode() ]
		}
	}
}
