package com.suraev.medical_card_service.util.eventListener;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class UpdateEvent extends ApplicationEvent {

    public UpdateEvent(Object source) {
        super(source);
    }



}
