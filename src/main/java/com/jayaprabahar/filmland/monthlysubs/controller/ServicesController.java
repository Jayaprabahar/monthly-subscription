/**
 * 
 */
package com.jayaprabahar.filmland.monthlysubs.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jayaprabahar.filmland.monthlysubs.beans.GenericResponseDO;
import com.jayaprabahar.filmland.monthlysubs.beans.GetServicesResponseDO;
import com.jayaprabahar.filmland.monthlysubs.beans.SubscribedServicesDO;
import com.jayaprabahar.filmland.monthlysubs.repo.AvailableServicesRepository;
import com.jayaprabahar.filmland.monthlysubs.repo.SubscribedServicesRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> Project : monthlysubs </p>
 * <p> Title : AuthorizationController.java </p>
 * <p> Description: </p>
 * <p> Created: Jan 21, 2019</p>
 * 
 * @version 1.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@RestController
@RequestMapping("/services")
@Slf4j
public class ServicesController {

	@Autowired
	private SubscribedServicesRepository subscribedServicesRepository;

	@Autowired
	private AvailableServicesRepository availableServicesRepository;

	@GetMapping("/{email}")
	@ResponseBody
	private GetServicesResponseDO getSubscribedServices(@PathVariable String email) {
		log.info("getSubscribedServices");
		/*return new GetServicesResponseDO(availableServicesRepository.findAll(), subscribedServicesRepository.findAllByServicesKey_Email(email));*/
		availableServicesRepository.findAll();
		
		
		return new GetServicesResponseDO(availableServicesRepository.findAll(), subscribedServicesRepository.findAllByServicesKey_Email(email));
		
	}

	@PostMapping("/buy/{email}/{name}")
	@ResponseBody
	private GenericResponseDO buyService(@PathVariable String email, @PathVariable String name) {
		log.info("buyService");

		if (subscribedServicesRepository.findAllByServicesKey_Email(email).parallelStream().filter(e -> e.getServicesKey().getName().equals(name)).findAny().isPresent())
			return GenericResponseDO.failedSubscribe;

		subscribedServicesRepository.save(new SubscribedServicesDO(email, name, 0, 0, LocalDate.now()));
		return GenericResponseDO.sucessfulSubscribe;
	}

	@PostMapping("/share/{email}/{friend}/{serviceName}")
	@ResponseBody
	private GenericResponseDO shareService(@PathVariable String email, @PathVariable String friend,  @PathVariable String serviceName) {
		log.info("shareService");
		
		Optional<SubscribedServicesDO> subscribedService = subscribedServicesRepository.findAllByServicesKey_Email(email).parallelStream().filter(e -> e.getServicesKey().getName().equals(serviceName)).findAny();

		if (!subscribedService.isPresent())
			return GenericResponseDO.failedShare;
		
		SubscribedServicesDO newServices  = subscribedService.get();
		newServices.getServicesKey().setEmail(friend);
		subscribedServicesRepository.save(newServices);
		
		return GenericResponseDO.sucessfulShare;
	}
}
