// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
  //    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
  // }
  grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
  grails.mime.use.accept.header = false
  grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                        xml: ['text/xml', 'application/xml'],
                        text: 'text/plain',
                        js: 'text/javascript',
                        rss: 'application/rss+xml',
                        atom: 'application/atom+xml',
                        css: 'text/css',
                        csv: 'text/csv',
                        all: '*/*',
                        json: ['application/json','text/json'],
                        form: 'application/x-www-form-urlencoded',
                        multipartForm: 'multipart/form-data'
  ]
  // The default codec used to encode data with ${}
  grails.views.default.codec="none" // none, html, base64
  grails.views.gsp.encoding="UTF-8"
  grails.converters.encoding="UTF-8"
  // enable Sitemesh preprocessing of GSP pages
  grails.views.gsp.sitemesh.preprocess = true
  // scaffolding templates configuration
  grails.scaffolding.templates.domainSuffix = 'Instance'

  // Set to false to use the new Grails 1.2 JSONBuilder in the render method
  grails.json.legacy.builder=false
  // enabled native2ascii conversion of i18n properties files
  grails.enable.native2ascii = true
  // whether to install the java.util.logging bridge for sl4j. Disable fo AppEngine!
  grails.logging.jul.usebridge = true
  // packages to include in Spring bean scanning
  grails.spring.bean.packages = []
  //use jquery instead of prototype
  grails.views.javascript.library="jquery"
  //disable the jsessionid on the url.  security risk
  grails.views.enable.jsessionid = false
  
  // set per-environment serverURL stem for creating absolute links
  environments {
    production {
      grails.serverURL = "http://redneckify.me"

      def catalinaBase = System.properties.getProperty('catalina.base')
      if (!catalinaBase) catalinaBase = '.'   // just in case
      def logDirectory = "${catalinaBase}/logs"

      log4j = {
        // Example of changing the log pattern for the default console
        // appender:
        //
        appenders {
          rollingFile name:"debugFile", file:"${logDirectory}/${appName}.log"
        }

        root {
          error 'debugFile'
          additivity = true
        }

        debug  'com.platypus',
        'grails.app'

        error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
        'org.codehaus.groovy.grails.commons', // core / classloading
        'org.codehaus.groovy.grails.web.pages', //  GSP
        'org.codehaus.groovy.grails.web.sitemesh', //  layouts
        'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
        'org.codehaus.groovy.grails.web.mapping', // URL mapping
        'org.codehaus.groovy.grails.plugins', // plugins
        'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
        'org.springframework'
      }

      amazonaws {
        apiKey = "AKIAJUJNIBYO3FIT77MQ"
        secretKey = "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"
      }

      google {
        accountCode = "UA-17797647-3"
        domainName = "redneckify.me"
      }
      
      facebook {
        //These two values need to be grabbed from facebook when you create your application there.	
        apiKey = "e46cdaab4a2eb1dfb3614045db7ad73e"
        secretKey = "e744ffd356f89324044fcb0083467ced"
        appId = "314398985906"
      }

      bitly {
        login = "dustinlucien"
        apiKey = "R_0ea3eb123496c37782a78c303c43edb1"
      }
      
      twitter {
        username = "redneckify"
        apiKey = "4DWQFnQxzAsVbOLVKHNTlA"
        apiVersion = "1"
        oAuthAccessToken = "133405786-hikbelIdIlFGzBWeTcaJ1vNLOyauvP83uMxMt1J2"
        oAuthAccessTokenSecret = "BjZZ4iUk8R6hO5vGfUq9Dj6b9xCNzO1RXpfyJCOj7Q"
      }
      
      oauth {
        twitter {
          requestTokenUrl = "https://api.twitter.com/oauth/request_token"
          accessTokenUrl = "https://api.twitter.com/oauth/access_token"
          authUrl = "https://api.twitter.com/oauth/authorize"
          consumerKey = "4DWQFnQxzAsVbOLVKHNTlA"
          consumerSecret = "KRrQoBmroO3oi48adA9pD9gukwMrdAzmpMWBrH0zsY"
        }
      }

      platypus {
        imageBucket = "project-platypus"
      }
      
      buffers {
        imageStreamingBufferSize = 8096
      }
    }

    development {
      grails.serverURL = "http://ohioinbrooklyn.dyndns.org:8080/${appName}"

      log4j = {
        // Example of changing the log pattern for the default console
        // appender:
        //
        appenders {
          console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
          rollingFile name:'debugFile', file:'logs/platypus-development.log'
        }

        root {
          error 'debugFile','stdout'
          additivity = true
        }

        debug  'com.platypus',
        'grails.app',
        'com.restfb'

        error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
                'org.codehaus.groovy.grails.commons', // core / classloading
                'org.codehaus.groovy.grails.web.pages', //  GSP
                'org.codehaus.groovy.grails.web.sitemesh', //  layouts
                'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
                'org.codehaus.groovy.grails.web.mapping', // URL mapping
                'org.codehaus.groovy.grails.plugins', // plugins
                'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
                'org.springframework'
      }

      google {
        accountCode = "UA-17797647-3"
        domainName = "localhost"
      }
      
      amazonaws {
        apiKey = "AKIAJUJNIBYO3FIT77MQ"
        secretKey = "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"
      }

      facebook {
        apiKey = "251d4b5b391a70904920a88c4f1208db"
        secretKey = "b887bb1fd73b2f8ec37fbef9d4a64073"
        appId = "130899563611059"
      }

      bitly {
        login = "dustinlucien"
        apiKey = "R_0ea3eb123496c37782a78c303c43edb1"
      }
      
      twitter {
        username = "platypusdev"
        apiKey = "l5ISiphqMnhQ5S57FWwrA"
        apiVersion = "1"
        oAuthAccessToken = "174473475-CYdeBsKoufiz3GmVLtDLRMMs5vj69bpgRxj7xy0A"
        oAuthAccessTokenSecret = "DLZwBdXdWABdFDgzLFKGBeNVS8RHzG5cL7FH3qndc"
      }
      
      oauth {
        twitter {
          requestTokenUrl = "https://api.twitter.com/oauth/request_token"
          accessTokenUrl = "https://api.twitter.com/oauth/access_token"
          authUrl = "https://api.twitter.com/oauth/authorize"
          consumerKey = "l5ISiphqMnhQ5S57FWwrA"
          consumerSecret = "TaYRL4sZR5ORzaXQi9GkNSnINK6r8EnseZTIwRP1BMU"
        }
      }
      
      platypus {
        imageBucket = "project-platypus-development"
      }
      
      buffers {
        imageStreamingBufferSize = 8096
      }
    }

    test {
      grails.serverURL = "http://localhost:8080/${appName}"

      amazonaws {
        apiKey = "AKIAJUJNIBYO3FIT77MQ"
        secretKey = "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"
      }

      facebook {
        apiKey = "251d4b5b391a70904920a88c4f1208db"
        secretKey = "b887bb1fd73b2f8ec37fbef9d4a64073"
        appId = "130899563611059"
      }
      
      bitly {
        login = "dustinlucien"
        apiKey = "R_0ea3eb123496c37782a78c303c43edb1"
      }
      
      twitter {
        username = "platypusdev"
        apiKey = "l5ISiphqMnhQ5S57FWwrA"
        apiVersion = "1"
        oAuthAccessToken = "174473475-CYdeBsKoufiz3GmVLtDLRMMs5vj69bpgRxj7xy0A"
        oAuthAccessTokenSecret = "DLZwBdXdWABdFDgzLFKGBeNVS8RHzG5cL7FH3qndc"
      }
      
      oauth {
        twitter {
          requestTokenUrl = "https://api.twitter.com/oauth/request_token"
          accessTokenUrl = "https://api.twitter.com/oauth/access_token"
          authUrl = "https://api.twitter.com/oauth/authorize"
          consumerKey = "l5ISiphqMnhQ5S57FWwrA"
          consumerSecret = "TaYRL4sZR5ORzaXQi9GkNSnINK6r8EnseZTIwRP1BMU"
        }
      }
            
      platypus {
        imageBucket = "project-platypus-development"
      }
      
      buffers {
        imageStreamingBufferSize = 8096
      }
    }

  }
