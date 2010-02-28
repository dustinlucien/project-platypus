package com.platypus

import grails.test.*

class SignatureServiceTests extends GrailsUnitTestCase {
	
	def signatureService
	
    protected void setUp() {
        super.setUp()

		signatureService = new SignatureService()
    }

    protected void tearDown() {
        super.tearDown()

		signatureService = null
    }

    void testSign() {		
		def policy = '''\
			"{"expiration": "2009-01-01T00:00:00Z",
			  "conditions": [ 
			    {"bucket": "s3-bucket"}, 
			    ["starts-with", "$key", "uploads/"],
			    {"acl": "private"},
			    {"success_action_redirect": "http://localhost/"},
			    ["starts-with", "$Content-Type", ""],
			    ["content-length-range", 0, 1048576]
			  ]
			};'''
			
		def secret = "KEnotqrWecohkmxkt2PQqCkQR2V4yCgu/cCdnmYI"
		
		def results = signatureService.sign(policy, secret)
		
		print "${results}"
    }
}
