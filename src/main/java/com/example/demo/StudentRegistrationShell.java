package com.example.demo;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ShellComponent
public class StudentRegistrationShell {

    private final Map<UUID, Student> students = new HashMap<>();

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
    public String createStudent(@ShellOption(value = "fn") String firstName,
                                @ShellOption(value = "ln") String lastName,
                                @ShellOption(value = "a") int age) {
        UUID id = UUID.randomUUID();
        Student student = new Student(id, firstName, lastName, age);
        students.put(id, student);
        return MessageFormat.format("New student with id - {0} created", id);
    }

    @ShellMethod(key = "d")
    public String deleteStudentById(UUID id) {
        if (!students.containsKey(id)) {
            return MessageFormat.format("There is no student with id - {0}!", id);
        } else {
            students.remove(id);
            return MessageFormat.format("User with id - {0} was deleted!", id);
        }
    }

    @ShellMethod(key = "ca")
    public String clearAll(){
        students.clear();
        return "All students were deleted!";
    }
}
