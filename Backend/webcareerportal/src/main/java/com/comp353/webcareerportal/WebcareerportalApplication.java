package com.comp353.webcareerportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebcareerportalApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebcareerportalApplication.class, args);
	}

}
