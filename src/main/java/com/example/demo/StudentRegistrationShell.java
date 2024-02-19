package com.example.demo;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ShellComponent
public class StudentRegistrationShell {

    private final ApplicationEventPublisher eventPublisher;
    private final Map<UUID, Student> students = new HashMap<>();

    public StudentRegistrationShell(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @ShellMethod(key = "r")
    public void readAllStudents() {
        if (!students.isEmpty()) {
            System.out.println("Student's list:");
            for (Map.Entry<UUID, Student> entry : students.entrySet()) {
                System.out.println(entry.getValue());
            }
        } else System.out.println("There are no students here!");
    }

    @ShellMethod(key = "c")
    public void createStudent(@ShellOption(value = "fn") String firstName,
                                @ShellOption(value = "ln") String lastName,
                                @ShellOption(value = "a") int age) {
        UUID id = UUID.randomUUID();
        Student student = new Student(id, firstName, lastName, age);
        student.createStudent(eventPublisher);
        students.put(id, student);
//        return MessageFormat.format("New student with id - {0} created", id); Вывод реализован через EventListener
    }

    @ShellMethod(key = "d")
    public void deleteStudentById(UUID id) {
        if (!students.containsKey(id)) {
            System.out.println(MessageFormat.format("There is no student with id - {0}!", id));
        } else {
            students.get(id).deleteStudent(eventPublisher);
            students.remove(id);
//            return MessageFormat.format("User with id - {0} was deleted!", id); Вывод реализован через EventListener
        }
    }

    @ShellMethod(key = "ca")
    public void clearAll(){
        if (!students.isEmpty()) {
            for (Map.Entry<UUID, Student> entry : students.entrySet()) {
                entry.getValue().deleteStudent(eventPublisher);
            }
            students.clear();
            System.out.println("All students were deleted!");
        } else System.out.println("There are no students here!");
//        return "All students were deleted!"; Вывод реализован через EventListener
    }
}
