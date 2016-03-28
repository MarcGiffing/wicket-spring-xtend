package com.giffing.wicket.spring.xtend.web.pages.home

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage
import com.giffing.wicket.spring.xtend.web.pages.BasePage
import com.giffing.wicket.spring.xtend.activeannotations.AutoInjector
import org.apache.wicket.request.mapper.parameter.PageParameters

@WicketHomePage
@AutoInjector
class HomePage extends BasePage {
	
	new(){
		application
	}
	
	new(PageParameters param){
		application
	}
}
