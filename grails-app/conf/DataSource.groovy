dataSource {
	pooled = true
	driverClassName = "org.hsqldb.jdbcDriver"
	username = "sa"
	password = ""
}

hibernate {
    cache.use_second_level_cache=true
    cache.use_query_cache=true
    cache.provider_class='net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
	development {
		dataSource {
			dbCreate = "create-drop" // one of 'create', 'create-drop','update'
			url = "jdbc:hsqldb:mem:devDB"
		}
		/*
		dataSource {
			pooled = false
			dbCreate = "update"
			driverClassName = "com.mysql.jdbc.Driver"
			username ="tomcat"
			password = "pl@typu5"
			url = "jdbc:mysql://184.106.207.184:3306/platypus"
		}
		*/	
	}
	test {
		dataSource {
			dbCreate = "update"
			url = "jdbc:hsqldb:mem:testDb"
		}
	}
	production {
		dataSource {
			pooled = true
			dbCreate = "update"
			driverClassName = "com.mysql.jdbc.Driver"
			username ="tomcat"
			password = "pl@typu5"
			url = "jdbc:mysql://184.106.207.184:3306/platypus"
		}
	}
}