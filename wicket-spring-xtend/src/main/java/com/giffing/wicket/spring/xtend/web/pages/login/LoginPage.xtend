package com.giffing.wicket.spring.xtend.web.pages.login

import com.giffing.wicket.spring.boot.context.scan.WicketSignInPage
import com.giffing.wicket.spring.xtend.web.pages.BasePage
import com.giffing.wicket.spring.xtend.web.pages.home.HomePage
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.PasswordTextField
import org.apache.wicket.markup.html.form.RequiredTextField
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.eclipse.xtend.lib.annotations.Accessors

@WicketSignInPage
class LoginPage extends BasePage {

	new(PageParameters parameters) {
		super(parameters)

		var webSession = getSession() as AbstractAuthenticatedWebSession
		if (webSession.isSignedIn) {
			continueToOriginalDestination()
		}

		queue(new LoginForm("loginForm"))
	}

	@Accessors
	private static class LoginForm extends Form<LoginForm> {

		String username

		String password

		new(String id) {
			super(id)
			setModel(new CompoundPropertyModel<LoginForm>(this))
			queue(new FeedbackPanel("feedback"))
			queue(new RequiredTextField<String>("username"))
			queue(new PasswordTextField("password"))
		}

		override protected onSubmit() {
			var session = AuthenticatedWebSession.get()
			if (session.signIn(username, password)) {
				setResponsePage(typeof(HomePage))
			} else {
				error("Login failed")
			}
		}
	}

}
