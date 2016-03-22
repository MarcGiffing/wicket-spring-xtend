package com.giffing.wicket.spring.xtend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class WicketApplication {
	def static void main(String[] args) {
		new SpringApplicationBuilder()
			.sources(WicketApplication)
			.run(args)
	}
}
