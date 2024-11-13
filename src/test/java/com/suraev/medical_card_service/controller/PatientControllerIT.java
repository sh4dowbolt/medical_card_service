package com.suraev.medical_card_service.controller;

import com.suraev.medical_card_service.service.PatientService;
import com.suraev.medical_card_service.service.PatientServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
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
@Tag("PatientController")
@Sql(scripts = "/test-schema.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/test-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class PatientControllerIT {
    @Autowired
    private PatientService patientService;
    @Autowired
    private MockMvc mockMvc;

@Nested
@Tag("postMethod")
class postMethod {
    @Test
    void createPatientWithOk() throws Exception {
        String newPatient = "{\"firstName\":\"zxc\",\"lastName\":\"Suraev\",\"middleName\":\"Viktorovich\"," +
                "\"sex\":\"F\",\"birthday\":\"21-10-2021\",\"numberOfPolicy\": \"1234567812314568\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newPatient)).andExpect(status().isCreated());
    }
    @Test
    void createPatientWithNotFound() throws Exception {
        String patientWithWrongData = "{\"firstName\":\"zxc\"," +
                "\"lastName\":\"Suraev\"," +
                "\"middleName\":\"Viktorovich\"," +
                "\"sex\":\"L\"," +
                "\"birthday\":\"21-10-2021\"," +
                "\"numberOfPolicy\": \"1234567812314568\"}"; // sex - L

        mockMvc.perform(MockMvcRequestBuilders.post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(patientWithWrongData)).andExpect(status().isBadRequest());
    }
}
@Nested
@Tag("updateMethod")
class updateMethod {
        @Test
        void getResponseOkWhenPatientIdExist() throws Exception {

            String newPatient = "{\"id\":\"1\",\"firstName\":\"zxc\",\"lastName\":\"Suraev\",\"middleName\":\"Viktorovich\"," +
                    "\"sex\":\"F\",\"birthday\":\"21-10-2021\",\"numberOfPolicy\": \"1234567812314568\"}";

            mockMvc.perform(MockMvcRequestBuilders.put("/patient")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(newPatient)).andExpect(status().is2xxSuccessful());
        }
        @Test
        void getResponseBadRequestWhenPatientIdNotExist() throws Exception {
            String patientWithWrongData = "{\"id\":\"2\",\"firstName\":\"zxc\"," +
                    "\"lastName\":\"Suraev\"," +
                    "\"middleName\":\"Viktorovich\"," +
                    "\"sex\":\"M\"," +
                    "\"birthday\":\"21-10-2021\"," +
                    "\"numberOfPolicy\": \"1234567812314568\"}"; // sex - L

            mockMvc.perform(MockMvcRequestBuilders.put("/patient")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(patientWithWrongData)).andExpect(status().isBadRequest());
        }
        @Test
        void getResponseBadRequestWhenPatientIdNull() throws Exception {
            String patientWithWrongData = "{\"firstName\":\"zxc\"," +
                    "\"lastName\":\"Suraev\"," +
                    "\"middleName\":\"Viktorovich\"," +
                    "\"sex\":\"M\"," +
                    "\"birthday\":\"21-10-2021\"," +
                    "\"numberOfPolicy\": \"1234567812314568\"}"; // sex - L

            mockMvc.perform(MockMvcRequestBuilders.put("/patient")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(patientWithWrongData)).andExpect(status().isBadRequest());
        }
    }
@Nested
@Tag("getMethod")
class getMethod {
    @Test
    void getResponseOkWhenPatientExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/patient/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }
    @Test
    void getResponseNotFoundWhenPatientNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/patient/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
    @Nested
    @Tag("deleteMethod")
    class deleteMethod {
        @Test
        void getResponseOkWhenPatientExist() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.delete("/patient/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());
        }
    }
}