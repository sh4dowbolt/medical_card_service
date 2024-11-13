package com.suraev.medical_card_service.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.zalando.problem.jackson.ProblemModule;

@Configuration
@EnableScheduling
@EnableAsync
public class ApplicationConfiguration {
    @Bean
    ObjectMapper getObjectmapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new ProblemModule().withStackTraces(false))
                .registerModule(new JavaTimeModule())
                .registerModule(new JsonNullableModule());
        return mapper;
    }

}
