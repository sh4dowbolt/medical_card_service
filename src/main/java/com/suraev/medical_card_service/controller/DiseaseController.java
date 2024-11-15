package com.suraev.medical_card_service.controller;

import com.suraev.medical_card_service.dto.DiseaseCreateDTO;
import com.suraev.medical_card_service.dto.DiseaseDTO;
import com.suraev.medical_card_service.dto.DiseaseUpdateDTO;
import com.suraev.medical_card_service.service.DiseaseService;
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
import java.util.List;

@Tag(name = "Информация о заболевании", description = "CRUD операции над информацией о заболевании")
@RestController
@RequestMapping("/patient/{patient_id}/disease")
@RequiredArgsConstructor
public class DiseaseController {
    private final DiseaseService diseaseService;

    @Operation(summary = "Получить список заболеваний", description = "Позволяет получить список заболеваний конкретного пациента")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Успешно получено", content = {@Content(schema = @Schema(implementation = DiseaseDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Указан id несуществующего пациента", content = {@Content(schema = @Schema(implementation = Problem.class), mediaType = "application/json")})})
    @GetMapping(produces = "application/json")
    public List<DiseaseDTO> getAllDiseases(@PathVariable("patient_id") Long patientId) {
        return diseaseService.getAllDiseases(patientId);
    }
    @Operation(summary = "Получить конкретное заболевание", description = "Позволяет получить заболевание конкретного пациента")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Успешно получено", content = {@Content(schema = @Schema(implementation = DiseaseDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Указан id несуществующего пациента или пациента", content = {@Content(schema = @Schema(implementation = Problem.class), mediaType = "application/json")})})

    @GetMapping(value = "/{disease_id}", produces = "application/json")
    public ResponseEntity<DiseaseDTO>getDiseaseById(@PathVariable("patient_id") Long patientId, @PathVariable("disease_id") Long diseaseId) {
        return diseaseService.getDiseaseByID(patientId,diseaseId);
    }
    @Operation(summary = "Создать заболевание", description = "Позволяет создать заболевание у конкретного пациента")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Успешно создано", content = {@Content(schema = @Schema(implementation = DiseaseDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "1)Указан id несуществующего пациента 2)Указан несуществующий код заболевания по МКБ10", content = {@Content(schema = @Schema(implementation = Problem.class), mediaType = "application/json")})})
    @PostMapping(produces = "application/json")

    public ResponseEntity<DiseaseDTO> createDisease(@PathVariable("patient_id") Long patientId, @Valid @RequestBody DiseaseCreateDTO diseaseDTO) throws URISyntaxException {
        return diseaseService.createDisease(patientId, diseaseDTO);
    }
    @Operation(summary = "Обновить заболевание", description = "Обновить заболевание у конкретного пациента")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Успешно обновлено", content = {@Content(schema = @Schema(implementation = DiseaseDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "1)Указан id несуществующего пациента 2)Указан несуществующий код заболевания по МКБ10 3) Указан id несуществующего заболевания", content = {@Content(schema = @Schema(implementation = Problem.class), mediaType = "application/json")})})
    @PutMapping(produces = "application/json")
    public ResponseEntity<DiseaseDTO> updateDisease(@PathVariable("patient_id") Long patientId, @Valid @RequestBody DiseaseUpdateDTO diseaseDTO) {
        return diseaseService.updateDisease(patientId, diseaseDTO);
    }
    @Operation(summary = "Удалить заболевание", description = "Удалить заболевание у конкретного пациента")
    @ApiResponse(responseCode = "204", description = "Успешно удалено")
    @DeleteMapping(value = "/{disease_id}")
    public ResponseEntity<Void> deleteDisease(@PathVariable("patient_id") Long patientId, @PathVariable("disease_id") Long diseaseId) {
        return diseaseService.deleteDisease(patientId,diseaseId);
    }
}
