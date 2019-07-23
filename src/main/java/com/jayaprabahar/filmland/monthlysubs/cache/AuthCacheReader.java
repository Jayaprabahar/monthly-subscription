package com.jayaprabahar.filmland.monthlysubs.cache;

import org.apache.commons.lang3.RandomStringUtils;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> Project : monthlysubs </p>
 * <p> Title : AuthCacheReader.java </p>
 * <p> Description: </p>
 * <p> Created: Jan 30, 2019</p>
 * 
 * @version 1.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@Component
@Slf4j
public class AuthCacheReader {

	private final EmbeddedCacheManager cacheManager;
	private final AuthCacheRepository repository;

	public AuthCacheReader(AuthCacheRepository repository, EmbeddedCacheManager embeddedCacheManager) {
		this.repository = repository;
		this.cacheManager = embeddedCacheManager;
	}

	@Scheduled(fixedDelay = 10000)
	public void retrieveSize() {
		log.info("Authentication Cache size " + cacheManager.getCache(AuthCacheRepository.DEFAULT_CACHE).size());

		cacheManager.getCache(AuthCacheRepository.DEFAULT_CACHE).forEach((k, v) -> log.info(k + " - " + v));
	}

	public String storeAuthToken(String email) {
		String authToken = RandomStringUtils.randomAlphanumeric(32);
		return this.repository.storeByToken(new AuthToken(authToken, email));
	}

	public boolean hasAuthToken(String authToken) {
		log.info("Find name by token " + authToken);
		return this.cacheManager.getCache(AuthCacheRepository.DEFAULT_CACHE).containsValue(authToken);
	}
}
