package com.platypus.google

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

class BlobstoreService {

    boolean transactional = false
	
	def service = com.google.appengine.api.blobstore.BlobstoreServiceFactory.getBlobstoreService();

    def createUploadUrl(String successPath = '/upload/success') {
		return service.createUploadUrl(successPath);
    }

	def delete(BlobKey blob) {
		return service.delete(blob);
	}
	
	def getUploadedBlobs(def request) {
		return service.getUploadedBlobs(request);
	}
}
