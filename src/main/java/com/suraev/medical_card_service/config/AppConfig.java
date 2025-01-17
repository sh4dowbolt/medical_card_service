package com.suraev.medical_card_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.zalando.problem.jackson.ProblemModule;

@Configuration
@EnableScheduling
@EnableCaching
public class AppConfig {
    @Value("${cron.update.dictionary}")
    private String cronForUpdate;

    @Bean
    public String cronForUpdate() {
        return cronForUpdate;
    }
    @Bean
    ObjectMapper getObjectmapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new ProblemModule().withStackTraces(false))
                .registerModule(new JavaTimeModule())
                .registerModule(new JsonNullableModule());
        return mapper;
    }



}
