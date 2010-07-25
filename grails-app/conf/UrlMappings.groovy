class UrlMappings {
    static mappings = {
      "/$controller/$action?/$id?"{
	      constraints {
			 // apply constraints here
		  }
	  }
    "/"(controller:"create", action:"redneckify")
	  "500"(view:'/error')
	}
}
