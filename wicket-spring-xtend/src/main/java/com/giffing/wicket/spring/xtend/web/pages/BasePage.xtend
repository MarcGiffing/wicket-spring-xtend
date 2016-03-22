package com.giffing.wicket.spring.xtend.web.pages

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.request.mapper.parameter.PageParameters

class BasePage extends WebPage {
	
	new(){
	}
	
	new(PageParameters params){
		super(params);
	}
	
}
