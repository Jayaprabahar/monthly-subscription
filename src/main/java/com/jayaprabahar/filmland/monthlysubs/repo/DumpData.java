/**
 * 
 */
package com.jayaprabahar.filmland.monthlysubs.repo;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jayaprabahar.filmland.monthlysubs.beans.AvailableServicesDO;
import com.jayaprabahar.filmland.monthlysubs.beans.LoginRequestDO;
import com.jayaprabahar.filmland.monthlysubs.beans.SubscribedServicesDO;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> Project : monthlysubs </p>
 * <p> Title : DumpData.java </p>
 * <p> Description: </p>
 * <p> Created: Jan 21, 2019</p>
 * 
 * @version 1.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */

@Configuration
@Slf4j
public class DumpData {

	@Bean
	CommandLineRunner loadLoginData(LoginRepository loginRepository) {
		return args -> {
			log.info("Preloading " + loginRepository.save(new LoginRequestDO("java@sogeti.com", "Javaiscool90")));
			log.info("Preloading " + loginRepository.save(new LoginRequestDO("jpofficial@gmail.com", "JPlovesJava")));
		};
	}
	
	@Bean
	CommandLineRunner loadAvaiableServicesData(AvailableServicesRepository availableServicesRepository) {
		return args -> {
			log.info("Preloading " + availableServicesRepository.save(new AvailableServicesDO("Netherlandse Films", 10, 4.0)));
			log.info("Preloading " + availableServicesRepository.save(new AvailableServicesDO("Netherlandse Series", 20, 6.0)));
		};
	}
	
	@Bean
	CommandLineRunner loadSubscribedServicesData(SubscribedServicesRepository subscribedServicesRepository) {
		return args -> {
			log.info("Preloading " + subscribedServicesRepository.save(new SubscribedServicesDO("java@sogeti.com", "Internationale Films", 5, 8.0, LocalDate.now())));
		};
	}

}
