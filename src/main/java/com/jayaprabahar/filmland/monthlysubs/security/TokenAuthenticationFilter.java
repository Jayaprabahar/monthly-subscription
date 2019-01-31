/**
 * 
 */
package com.jayaprabahar.filmland.monthlysubs.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jayaprabahar.filmland.monthlysubs.cache.AuthCacheReader;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> Project : monthlysubs </p>
 * <p> Title : TokenAuthenticationFilter.java </p>
 * <p> Description: </p>
 * <p> Created: Jan 30, 2019</p>
 * 
 * @version 1.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@Component
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private AuthCacheReader authCacheReader;

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		log.info("executing filter");
		authCacheReader = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext()).getBean(AuthCacheReader.class);

		String accessToken = request.getHeader(SecurityAdapter.FILMLAND_SECURITY_TOKEN);
		if (StringUtils.isNotBlank(accessToken) && authCacheReader.hasAuthToken(accessToken)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		SecurityContextHolder.getContext().setAuthentication(null);
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Security Token missing/Invalid");
	}

}
