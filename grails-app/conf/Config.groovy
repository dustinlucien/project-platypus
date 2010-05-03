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


tomcat.deploy.username="tomcat"
tomcat.deploy.password="pl@typu5"
tomcat.deploy.url="http://project-platypus.dyndns.org:8080/manager"

// set per-environment serverURL stem for creating absolute links
environments {
    production {
        grails.serverURL = "http://project-platypus.dyndns.org"

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
				   'grails.app',
				   'org.hibernate'

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
			apiKey = "1CVPFRPBE5NB36ZVWHR2"
			secretKey = "KEnotqrWecohkmxkt2PQqCkQR2V4yCgu/cCdnmYI"
		}
		
		facebook {
			//These two values need to be grabbed from facebook when you create your application there.	
			apiKey = "e46cdaab4a2eb1dfb3614045db7ad73e"
			secretKey = "e744ffd356f89324044fcb0083467ced"
			appId = "314398985906"
		}
		
		platypus {
			imageBucket = "project-platypus"
		}
    }

    development {
        grails.serverURL = "http://localhost:8080/${appName}"

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
			apiKey = "1CVPFRPBE5NB36ZVWHR2"
			secretKey = "KEnotqrWecohkmxkt2PQqCkQR2V4yCgu/cCdnmYI"
		}
		
		facebook {
			apiKey = "8000f0fcf8dcd9594bf19c010b9c723d"
			secretKey = "1434dc650e4dddd3585b59ac956724d3"
			appId = "122941727717546"
		}
		
		
		platypus {
			imageBucket = "project-platypus-development"
		}
    }

    test {
        grails.serverURL = "http://localhost:8080/${appName}"

		amazonaws {
			apiKey = "1CVPFRPBE5NB36ZVWHR2"
			secretKey = "KEnotqrWecohkmxkt2PQqCkQR2V4yCgu/cCdnmYI"
		}

		facebook {
			apiKey = "8000f0fcf8dcd9594bf19c010b9c723d"
			secretKey = "1434dc650e4dddd3585b59ac956724d3"
			appId = "122941727717546"
		}
		
		platypus {
			imageBucket = "project-platypus-development"
		}
    }

}