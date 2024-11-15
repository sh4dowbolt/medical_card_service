package com.suraev.medical_card_service.controller;

import com.suraev.medical_card_service.service.CodeDiseaseService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Tag("CodeDiseaseController")
@Sql(scripts = "/test-schema.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/test-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class CodeDiseaseControllerIT {
    @Autowired
    private CodeDiseaseService patientService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCodeDiseases() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/dictionary/mkb10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}