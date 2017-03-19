package com.giffing.wicket.spring.xtend.web.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class WicketWebSecurityApapterConfig extends WebSecurityConfigurerAdapter {
	
	override void configure(HttpSecurity http) {
		http
			.csrf()
				.disable()
			.authorizeRequests()
				.antMatchers("/**")
					.permitAll()
			.and()
				.logout()
					.permitAll();
		http
			.headers()
				.frameOptions()
					.disable()
	}

	@Autowired
	def configureGlobal(AuthenticationManagerBuilder auth) {
		auth
			.inMemoryAuthentication()
			.withUser("admin").password("admin").authorities("USER", "ADMIN")
	}
}
