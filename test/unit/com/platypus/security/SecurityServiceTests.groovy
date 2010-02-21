package com.platypus.security

import grails.test.*

class SecurityServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSign() {
	
		def securityService = new SecurityService();
		
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
		
		def results = securityService.sign(policy, secret)
		
		print "${results}"
    }
}
