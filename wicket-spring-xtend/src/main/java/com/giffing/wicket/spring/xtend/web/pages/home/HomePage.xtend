package com.giffing.wicket.spring.xtend.web.pages.home

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage
import com.giffing.wicket.spring.xtend.activeannotations.AutoInjector
import com.giffing.wicket.spring.xtend.web.pages.BasePage
import org.apache.wicket.markup.html.link.BookmarkablePageLink
import org.apache.wicket.request.mapper.parameter.PageParameters

@WicketHomePage
@AutoInjector
class HomePage extends BasePage {
	
	new(){
		application
		new BookmarkablePageLink("link1", typeof(HomePage)).q
		new BookmarkablePageLink("link2", typeof(HomePage)).a
	}
	
	new(PageParameters param){
		application
	}
}
