package com.platypus.service

import grails.test.*

class UrlShortenerServiceTests extends GroovyTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }
    
    public void testShorteningUrl() {
      def longUrl = "http://www.amazon.com/"
      
      def urlShortenerService = new UrlShortenerService()
      urlShortenerService.restClientService = new RestClientService()
      
      def shortUrl = urlShortenerService.shortenUrl(longUrl)
      
      assert shortUrl != null
      assert shortUrl.length() > 1
      assert shortUrl.startsWith('http://bit.ly')
    }
}
