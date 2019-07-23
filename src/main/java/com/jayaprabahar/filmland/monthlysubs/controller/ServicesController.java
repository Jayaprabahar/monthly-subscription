/**
 * 
 */
package com.jayaprabahar.filmland.monthlysubs.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jayaprabahar.filmland.monthlysubs.beans.AvailableServicesDO;
import com.jayaprabahar.filmland.monthlysubs.beans.GenericResponseDO;
import com.jayaprabahar.filmland.monthlysubs.beans.GetServicesResponseDO;
import com.jayaprabahar.filmland.monthlysubs.beans.SubscribedServicesDO;
import com.jayaprabahar.filmland.monthlysubs.repo.AvailableServicesRepository;
import com.jayaprabahar.filmland.monthlysubs.repo.SubscribedServicesRepository;
import com.jayaprabahar.filmland.monthlysubs.scheduler.PaymentScheduler;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> Project : monthlysubs </p>
 * <p> Title : LoginController.java </p>
 * <p> Description: Services controller. Handles api calls for list/buy/share services</p>
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

	@Autowired
	private PaymentScheduler paymentScheduler;

	@GetMapping("/{email}")
	@ResponseBody
	private GetServicesResponseDO getSubscribedServices(@PathVariable String email, HttpServletRequest request) {
		log.info("getSubscribedServices");
		
		List<AvailableServicesDO> availableServicesDOs = availableServicesRepository.findAll();
		List<SubscribedServicesDO> subscribedServicesDOs = subscribedServicesRepository.findAllByServicesKey_Email(email);

		// Skips the subscribed services from available services as they are already subscribed
		Set<String> subscribedServiceNames = subscribedServicesDOs.stream().map(s -> s.getServicesKey().getName()).collect(Collectors.toSet());
		availableServicesDOs.removeIf(a -> subscribedServiceNames.contains(a.getName()));

		return new GetServicesResponseDO(availableServicesDOs, subscribedServicesDOs);
	}

	@PostMapping("/buy/{email}/{name}")
	@ResponseBody
	private GenericResponseDO buyService(@PathVariable String email, @PathVariable String name) throws SchedulerException {
		log.info("buyService");

		if (!availableServicesRepository.findAll().stream().filter(e -> e.getName().equals(name)).findAny().isPresent())
			return GenericResponseDO.failedSubscribeUnavailable;

		if (!subscribedServicesRepository.findAllByServicesKey_Email(email).parallelStream().filter(e -> e.getServicesKey().getName().equals(name)).findAny().isPresent()) {
			SubscribedServicesDO subscribedServicesDO = subscribedServicesRepository.save(new SubscribedServicesDO(email, name, 0, 0, LocalDate.now()));

			// Adds payment scheduler upon successful subscription
			if (subscribedServicesDO != null) {
				paymentScheduler.addPaymentSchedule(subscribedServicesDO);
				return GenericResponseDO.sucessfulSubscribe;
			}
		}
		return GenericResponseDO.failedSubscribe;
	}

	@PostMapping("/share/{email}/{friend}/{serviceName}")
	@ResponseBody
	private GenericResponseDO shareService(@PathVariable String email, @PathVariable String friend, @PathVariable String serviceName) throws SchedulerException {
		log.info("shareService");

		Optional<SubscribedServicesDO> subscribedService = subscribedServicesRepository.findAllByServicesKey_Email(email).parallelStream().filter(e -> e.getServicesKey().getName().equals(serviceName))
				.findAny();

		if (subscribedService.isPresent()) {
			SubscribedServicesDO oldServices = subscribedService.get();
			SubscribedServicesDO newServices = new SubscribedServicesDO(friend, oldServices.getServicesKey().getName(), oldServices.getRemainContent(), oldServices.getPrice(),
					oldServices.getStartDate());

			// Adds or removes payment scheduler upon successful sharing
			if (subscribedServicesRepository.save(newServices) != null) {
				subscribedServicesRepository.delete(oldServices);
				paymentScheduler.addPaymentSchedule(newServices);
				paymentScheduler.deletePaymentSchedule(oldServices);
				return GenericResponseDO.sucessfulShare;
			}

		}
		return GenericResponseDO.failedShare;
	}
}
