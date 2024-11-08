package com.suraev.medical_card_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MedicalCardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalCardServiceApplication.class, args);
	}

}
