package com.suraev.medical_card_service.domain.entity;


import com.suraev.medical_card_service.domain.entity.enums.HealthStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Schema(description = "Ответ с состоянием работоспособности приложения")
public class Health {
    @Schema(description = "Статус приложения", example = "UP")
    private HealthStatus status;

    public void setHealthStatus(HealthStatus healthStatus) {
        status = healthStatus;
    }
}
