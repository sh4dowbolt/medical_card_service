package com.suraev.medical_card_service.service;

import com.suraev.medical_card_service.util.eventListener.UpdateEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface UpdateCodeService {
    void handle(UpdateEvent event);
}
