package com.platypus.google

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

class BlobstoreService {

    boolean transactional = false
	
	def service = com.google.appengine.api.blobstore.BlobstoreServiceFactory.getBlobstoreService();

    def createUploadUrl(String successPath = null) {
		return service.createUploadUrl(successPath);
    }

	def delete(BlobKey blob) {
		return service.delete(blob);
	}
	
	def getUploadedBlobs(HttpServletRequest request) {
		return service.getUploadedBlobs(request);
	}
	
	def serve(BlobKey blobKey, HttpServletResponse response) {
		service.serve(blobKey, response);
	}
}
