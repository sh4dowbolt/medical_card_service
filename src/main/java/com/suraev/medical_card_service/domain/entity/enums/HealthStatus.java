package com.suraev.medical_card_service.domain.entity.enums;

public enum HealthStatus {
    UP ("UP"),
    DOWN ("DOWN");
    private final String status;
    HealthStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    @Override
    public String toString() {
        return status;
    }
}
