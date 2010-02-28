package com.platypus.service

import grails.test.*

class SecurityServiceTests extends GrailsUnitTestCase {
	
	def securityService
	
    protected void setUp() {
        super.setUp()

		securityService = new SecurityService()
    }

    protected void tearDown() {
        super.tearDown()

		securityService = null
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
		
		def results = securityService.sign(policy, secret)
		
		assert results.signed != null
 		assert results.signature != null
		
		assert results['signed'] == "CQkJInsiZXhwaXJhdGlvbiI6ICIyMDA5LTAxLTAxVDAwOjAwOjAwWiIsCgkJCSAgImNvbmRpdGlvbnMiOiBbIAoJCQkgICAgeyJidWNrZXQiOiAiczMtYnVja2V0In0sIAoJCQkgICAgWyJzdGFydHMtd2l0aCIsICIka2V5IiwgInVwbG9hZHMvIl0sCgkJCSAgICB7ImFjbCI6ICJwcml2YXRlIn0sCgkJCSAgICB7InN1Y2Nlc3NfYWN0aW9uX3JlZGlyZWN0IjogImh0dHA6Ly9sb2NhbGhvc3QvIn0sCgkJCSAgICBbInN0YXJ0cy13aXRoIiwgIiRDb250ZW50LVR5cGUiLCAiIl0sCgkJCSAgICBbImNvbnRlbnQtbGVuZ3RoLXJhbmdlIiwgMCwgMTA0ODU3Nl0KCQkJICBdCgkJCX07"		
		assert results['signature'] == "yI8B0+87aR1psiatZsUnpF6P3cE="
    }
}
