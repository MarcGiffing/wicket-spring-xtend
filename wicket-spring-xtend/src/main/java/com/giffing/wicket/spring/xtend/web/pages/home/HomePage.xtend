package com.giffing.wicket.spring.xtend.web.pages.home

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage
import com.giffing.wicket.spring.xtend.activeannotations.AutoInjector
import com.giffing.wicket.spring.xtend.web.pages.BasePage
import org.apache.wicket.markup.html.link.BookmarkablePageLink
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.authroles.authentication.pages.SignInPage

@WicketHomePage
@AutoInjector
class HomePage extends BasePage {
	
	new(){
		new BookmarkablePageLink("link1", typeof(SignInPage)).a
		new BookmarkablePageLink("link2", typeof(HomePage)).q
	}
	
	new(PageParameters param){
	}
}
