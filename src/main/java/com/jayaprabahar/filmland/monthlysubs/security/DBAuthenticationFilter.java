/**
 * 
 */
package com.jayaprabahar.filmland.monthlysubs.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jayaprabahar.filmland.monthlysubs.beans.GenericResponseDO;
import com.jayaprabahar.filmland.monthlysubs.beans.LoginRequestDO;
import com.jayaprabahar.filmland.monthlysubs.controller.LoginController;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> Project : monthlysubs </p>
 * <p> Title : DBAuthenticationFilter.java </p>
 * <p> Description: </p>
 * <p> Created: Jan 27, 2019</p>
 * 
 * @version 1.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@Component
@Slf4j
public class DBAuthenticationFilter extends OncePerRequestFilter {

	private LoginController loginController;

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		log.info("executing filter");
		SecurityContextHolder.getContext().setAuthentication(null);
		
		loginController = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext()).getBean(LoginController.class);

		String authorization = request.getHeader("Authorization");
		String[] authValues = new String[2];

		if (StringUtils.startsWith(authorization, "Basic")) {
			String base64Credentials = StringUtils.removeStart(authorization, "Basic").trim();
			String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
			authValues = credentials.split(":", 2);

			if (authValues.length == 2 && !loginController.authorize(new LoginRequestDO(authValues[0], authValues[1]), request, response).equals(GenericResponseDO.failedLogin)) {
				SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(authValues[0], null));
				filterChain.doFilter(request, response);
				return;
			}
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User credentials are wrong");
			return;
		}
		filterChain.doFilter(request, response);
	}

}
