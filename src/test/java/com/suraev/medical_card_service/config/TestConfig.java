package com.suraev.medical_card_service.config;

import com.suraev.medical_card_service.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.MockMvc;


@TestConfiguration
public class TestConfig {
    @MockBean
    private DiseaseService diseaseService;
    @Autowired
    private MockMvc mockMvc;

    @Bean
    public DiseaseService getDiseaseService() {
        return diseaseService;
    }
    @Bean
    public MockMvc getMockMvc() {
        return mockMvc;
    }
}
