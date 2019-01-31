package com.jayaprabahar.filmland.monthlysubs.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.anonymous().disable()
				.requestMatchers().antMatchers("/services/**")
				.and()
				.authorizeRequests().antMatchers("/services/**").access("hasRole('USER')")
				.and()
				.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}
}