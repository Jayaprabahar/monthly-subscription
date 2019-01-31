package com.jayaprabahar.filmland.monthlysubs.cache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> Project : monthlysubs </p>
 * <p> Title : AuthCacheRepository.java </p>
 * <p> Description: </p>
 * <p> Created: Jan 30, 2019</p>
 * 
 * @version 1.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@Component
@CacheConfig(cacheNames = AuthCacheRepository.DEFAULT_CACHE)
@Slf4j
public class AuthCacheRepository {
	public static final String DEFAULT_CACHE = "filmlandCache";

	@Cacheable
	public String storeByToken(AuthToken authToken) {
		log.info("Store cache " + authToken.getToken() + " " + authToken.getName());
		return authToken.getToken();
	}

}
