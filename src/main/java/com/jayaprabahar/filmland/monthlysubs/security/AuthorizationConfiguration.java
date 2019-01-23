/**
 * 
 */
package com.jayaprabahar.filmland.monthlysubs.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <p> Project : monthlysubs </p>
 * <p> Title : AuthorizationConfiguration.java </p>
 * <p> Description: </p>
 * <p> Created: Jan 21, 2019</p>
 * 
 * @version 1.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@Configuration
@EnableWebSecurity
public class AuthorizationConfiguration extends WebSecurityConfigurerAdapter {

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#
	 * configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/*").permitAll();
	}

}
