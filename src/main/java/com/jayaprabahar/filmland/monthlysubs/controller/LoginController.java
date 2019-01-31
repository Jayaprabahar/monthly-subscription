/**
 * 
 */
package com.jayaprabahar.filmland.monthlysubs.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jayaprabahar.filmland.monthlysubs.beans.GenericResponseDO;
import com.jayaprabahar.filmland.monthlysubs.beans.LoginRequestDO;
import com.jayaprabahar.filmland.monthlysubs.repo.AuthenticationServicesRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> Project : monthlysubs </p>
 * <p> Title : LoginController.java </p>
 * <p> Description: Authentication and Authorization controller</p>
 * <p> Created: Jan 21, 2019</p>
 * 
 * @version 1.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@RestController
@RequestMapping("/")
@Slf4j
public class LoginController {

	@Autowired
	private AuthenticationServicesRepository authenticationServicesRepository;

	/**
	 * @param loginRequestDO
	 * @return
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	private GenericResponseDO authorize(@RequestBody @Valid LoginRequestDO loginRequestDO) {
		log.info("authorize" + loginRequestDO);

		if (authenticationServicesRepository.findById(loginRequestDO.getEmail()).filter(e -> e.getPassword().equals(loginRequestDO.getPassword())).isPresent())
			return GenericResponseDO.sucessfulLogin;

		return GenericResponseDO.failedLogin;
	}

	@GetMapping
	@ResponseBody
	private List<LoginRequestDO> getAllLogins() {
		log.info("getAllLogins");
		return authenticationServicesRepository.findAll();

	}
}
