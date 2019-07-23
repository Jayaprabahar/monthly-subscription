package com.jayaprabahar.filmland.monthlysubs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@ServletComponentScan
public class MonthlySubsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonthlySubsApplication.class, args);
	}

}
