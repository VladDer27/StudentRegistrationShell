package com.example.demo;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StudentEventListener {

    @EventListener
    public void handleStudentCreated(StudentCreatedEvent event) {
        Student student = event.getStudent();
        System.out.println("Student created with ID: " + student.getId() + ", Name: " + student.getFirstName() + " " + student.getLastName() + ", Age: " + student.getAge());
    }

    @EventListener
    public void handleStudentDeleted(StudentDeletedEvent event) {
        System.out.println("Student deleted with ID: " + event.getStudentId());
    }
}
