package com.suraev.medical_card_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.jackson.ProblemModule;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public ProblemModule problemModule() {
        return new ProblemModule().withStackTraces(false);
    }
}
