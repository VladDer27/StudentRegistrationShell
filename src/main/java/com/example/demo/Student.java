package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.ApplicationEventPublisher;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Student {

    private UUID id;
    private String firstName;
    private String lastName;
    private int age;

    public void deleteStudent(ApplicationEventPublisher eventPublisher) {
        eventPublisher.publishEvent(new StudentDeletedEvent(this, id));
    }

    public void createStudent(ApplicationEventPublisher eventPublisher) {
        eventPublisher.publishEvent(new StudentCreatedEvent(this, this));
    }
}
