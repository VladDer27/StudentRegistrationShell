package com.example.demo;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

@Getter
public class StudentDeletedEvent extends ApplicationEvent {

    private final UUID studentId;

    public StudentDeletedEvent(Object source, UUID studentId) {
        super(source);
        this.studentId = studentId;
    }

}
