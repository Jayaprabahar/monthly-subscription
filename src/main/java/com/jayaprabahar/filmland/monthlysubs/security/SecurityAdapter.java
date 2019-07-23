/**
 * 
 */
package com.jayaprabahar.filmland.monthlysubs.security;

import java.util.Collections;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * <p> Project : monthlysubs </p>
 * <p> Title : SecurityAdapter.java </p>
 * <p> Description: </p>
 * <p> Created: Jan 21, 2019</p>
 * 
 * @version 1.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@Configuration
@EnableWebSecurity
public class SecurityAdapter extends WebSecurityConfigurerAdapter {

	public static final String FILMLAND_SECURITY_TOKEN = "FLTOKEN";

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	public FilterRegistrationBean<TokenAuthenticationFilter> myFilterRegistrationBean() {
		FilterRegistrationBean<TokenAuthenticationFilter> regBean = new FilterRegistrationBean<TokenAuthenticationFilter>();
		regBean.setFilter(new TokenAuthenticationFilter());
		regBean.setUrlPatterns(Collections.singletonList("/services/*"));

		return regBean;
	}

	@Bean
	public FilterRegistrationBean<DBAuthenticationFilter> authFilter() {
		FilterRegistrationBean<DBAuthenticationFilter> regBean = new FilterRegistrationBean<DBAuthenticationFilter>();
		regBean.setFilter(new DBAuthenticationFilter());
		regBean.setUrlPatterns(Collections.singletonList("/"));

		return regBean;
	}
}
