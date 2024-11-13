package com.suraev.medical_card_service.service;

public interface ParseCodeService {
     void scheduledUpdate();
     void publishUpdateEvent();
     void initCodeDiseaseDatabaseWhenAppIsUp();
}
