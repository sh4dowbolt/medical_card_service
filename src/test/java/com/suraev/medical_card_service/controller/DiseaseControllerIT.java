package com.suraev.medical_card_service.controller;

import com.suraev.medical_card_service.service.DiseaseService;
import org.junit.jupiter.api.Nested;
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
@Tag("DiseaseControllerIT")
@Sql(scripts = "/test-schema.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/test-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class DiseaseControllerIT {
    @Autowired
    private DiseaseService diseaseService;
    @Autowired
    private MockMvc mockMvc;

    @Nested
    @Tag("postMethod")
    class postMethod {
        @Test
        void createWithOkAndExistedNumberOfDisease() throws Exception {
            String numberOfDisease ="A01.0";
            String disease =
                    "{\"numberOfDisease\":\""+numberOfDisease+"\","+
                    "\"startDisease\":\"21-10-1996\"," +
                    "\"endDisease\":\"22-10-1996\"," +
                    "\"prescription\":\"take a medicine\" }";

            mockMvc.perform(MockMvcRequestBuilders.post("/patient/1/disease")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(disease)).andExpect(status().isCreated());
        }
        @Test
        void createWithBadRequestNotExistedNumberOfDisease() throws Exception {
            String notExistedNumberOfDisease ="A01.4444";
            String disease =
                    "\"numberOfDisease\":\""+notExistedNumberOfDisease+"\","+
                            "\"startDisease\":\"21-10-1996\"," +
                            "\"endDisease\":\"22-10-1996\"," +
                            "\"prescription\":\"take a medicine\" }";

            mockMvc.perform(MockMvcRequestBuilders.post("/patient/1/disease")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(disease)).andExpect(status().isBadRequest());
        }
        @Test
        void createWithBadRequestExistedPatient() throws Exception {
            String patientId = "1";
            String disease = "{\"id\":\"+"+patientId+"+\"," +
                    "\"numberOfDisease\":\"A01.0\","+
                    "\"startDisease\":\"21-10-1996\"," +
                    "\"endDisease\":\"22-10-1996\"," +
                    "\"prescription\":\"take a medicine\" }";

            mockMvc.perform(MockMvcRequestBuilders.post("/patient/1/disease")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(disease)).andExpect(status().isBadRequest());
        }
    }
    @Nested
    @Tag("updateMethod")
    class updateMethod {
        @Test
        void updateWithBadRequestNotExistedPatient() throws Exception {
            String notExistedPatient = "2";
            String diseaseForUpdate = "{\"id\":\"1\"," +
                    "\"numberOfDisease\":\"A01.0\","+
                    "\"startDisease\":\"21-10-1996\"," +
                    "\"endDisease\":\"22-10-1996\"," +
                    "\"prescription\":\"take a medicine\"}";
            mockMvc.perform(MockMvcRequestBuilders.put("/patient/"+notExistedPatient+"/disease")
                    .contentType(MediaType.APPLICATION_JSON).content(diseaseForUpdate))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void updateWithBadRequestWhenCodeDiseaseNotExist() throws Exception {
            String notExistedNumberOfDisease ="A01.4444";
            String diseaseForUpdate = "{\"id\":\"1\"," +
                    "\"numberOfDisease\":\""+notExistedNumberOfDisease+"\","+
                    "\"startDisease\":\"21-10-1996\"," +
                    "\"endDisease\":\"22-10-1996\"," +
                    "\"prescription\":\"take a medicine\"}";

            mockMvc.perform(MockMvcRequestBuilders.put("/patient/1/disease")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(diseaseForUpdate)).andExpect(status().isBadRequest());
        }
        @Test
        void updateWithBadRequestWhenDiseaseNotExist() throws Exception {
            String notExistedDiseaseId="2";
            String diseaseForUpdate = "{\"id\":"+notExistedDiseaseId+"," +
                    "\"prescription\":\"take a medicine\"}";

            mockMvc.perform(MockMvcRequestBuilders.put("/patient/1/disease")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(diseaseForUpdate)).andExpect(status().isBadRequest());
        }
        @Test
        void updateWithBadRequestWhenDiseaseExist() throws Exception {
            String notExistedDiseaseId="1";
            String diseaseForUpdate = "{\"id\":"+notExistedDiseaseId+"," +
                    "\"prescription\":\"take a medicine\"}";

            mockMvc.perform(MockMvcRequestBuilders.put("/patient/1/disease")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(diseaseForUpdate)).andExpect(status().isOk());
        }
    }
    @Nested
    @Tag("getAllMethod")
    class getAllMethod {
        @Test
        void getResponseOkWhenPatientExist() throws Exception {
            String existedPatient = "1";
            mockMvc.perform(MockMvcRequestBuilders.get("/patient/"+existedPatient+"/disease")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful());
        }
        @Test
        void getResponseBadRequestWhenPatientExist() throws Exception {
            String notExistedPatient = "2";
            mockMvc.perform(MockMvcRequestBuilders.get("/patient/"+notExistedPatient+"/disease")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
    }
    @Nested
    @Tag("getByIdMethod")
    class getByIdMethod {
        @Test
        void getResponseOkWhenPatientExist() throws Exception {
            String existedPatient = "1";
            mockMvc.perform(MockMvcRequestBuilders.get("/patient/"+existedPatient+"/disease")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful());
        }
        @Test
        void getResponseBadRequestWhenPatientExist() throws Exception {
            String notExistedPatient = "2";
            mockMvc.perform(MockMvcRequestBuilders.get("/patient/"+notExistedPatient+"/disease")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void getResponseOkWhenPatientAndDiseaseExist() throws Exception {
            String existedPatient = "1";
            String existedDisease = "1";
            mockMvc.perform(MockMvcRequestBuilders.get("/patient/"+existedPatient+"/disease/"+existedDisease)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful());
        }
        @Test
        void getResponseOkWhenPatientExistButDiseaseNot() throws Exception {
            String existedPatient = "1";
            String notExistedDisease = "2";
            mockMvc.perform(MockMvcRequestBuilders.get("/patient/"+existedPatient+"/disease/"+notExistedDisease)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        }
    }
    @Nested
    @Tag("deleteByIdMethod")
    class deleteByIdMethod {
        @Test
        void deleteWithNoContentResponse() throws Exception {
            String diseaseId = "7";
            mockMvc.perform(MockMvcRequestBuilders.delete("/patient/1/disease/"+diseaseId)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());
        }
    }
}