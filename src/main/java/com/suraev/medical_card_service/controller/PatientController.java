package com.suraev.medical_card_service.controller;

import com.suraev.medical_card_service.dto.DiseaseDTO;
import com.suraev.medical_card_service.dto.PatientCreateDTO;
import com.suraev.medical_card_service.dto.PatientDTO;
import com.suraev.medical_card_service.dto.PatientUpdateDTO;
import com.suraev.medical_card_service.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;

import java.net.URISyntaxException;

@Tag(name = "Информация о пациенте", description = "CRUD операции над информацией о пациенте")
@RestController
@RequestMapping(value = "/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @Operation(summary = "Получить конкретного пациента", description = "Позволяет получить конкретного пациента")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Успешно получено", content = {@Content(schema = @Schema(implementation = PatientDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Пациент не найден", content = {@Content(schema = @Schema(implementation = Problem.class), mediaType = "application/json")})})
    @GetMapping(value = "/{patient_id}", produces = "application/json")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable("patient_id") Long patientId) {
        return patientService.getPatientById(patientId);
    }
    @Operation(summary = "Обновить информацию о пациенте", description = "Обновить информацию о конкретном пациента")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Успешно обновлено", content = {@Content(schema = @Schema(implementation = PatientDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Указан id несуществующего пациента", content = {@Content(schema = @Schema(implementation = Problem.class), mediaType = "application/json")})})
    @PutMapping(produces = "application/json")
    public ResponseEntity<PatientDTO> updatePatient(@Valid @RequestBody PatientUpdateDTO patientDTO) {
        return patientService.updatePatient(patientDTO);
    }
    @Operation(summary = "Удалить пациента", description = "Удалить пациента")
    @ApiResponse(responseCode = "204", description = "Успешно удалено")
    @DeleteMapping(value ="/{patient_id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("patient_id") Long patientId) {
        return patientService.deletePatientById(patientId);
    }
    @Operation(summary = "Создать пациента", description = "Позволяет создать пациента")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Успешно создано", content = {@Content(schema = @Schema(implementation = PatientDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Wrong Input", content = {@Content(schema = @Schema(implementation = Problem.class), mediaType = "application/json")})})
    @PostMapping(produces = "application/json")
    public ResponseEntity<PatientDTO> createPatient(@Valid @RequestBody PatientCreateDTO patientDTO) {
        return patientService.createPatient(patientDTO);
    }


}
