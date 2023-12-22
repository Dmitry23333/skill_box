package com.example.studentslist;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.stereotype.Component;

import java.util.Random;

@ShellComponent
@Component
@RequiredArgsConstructor
public class StudentsWorker {

    private final StudentsList studentsList;

    @ShellMethod(key = "add")
    public void addStudent(String firstName,
                           String lastName,
                           int age) {
        int id = new Random().nextInt(0, 100000);
        Student student = new Student(id, firstName, lastName, age);
        studentsList.addStudent(student);
    }

    @ShellMethod(key = "delete")
    @ShellMethodAvailability("canDelete")
    public void removeById(int id) {
        studentsList.removeStudentById(id);
    }

    @ShellMethod(key = "print")
    @ShellMethodAvailability("canPrintOrRemoveAll")
    public void printStudents() {
        studentsList.print();
    }

    @ShellMethod(key = "removeAll")
    @ShellMethodAvailability("canPrintOrRemoveAll")
    public void removeAll() {
        studentsList.removeAll();
    }


    public Availability canPrintOrRemoveAll() {
        return studentsList.getStudentList().size() == 0 ? Availability.unavailable("list is empty") : Availability.available();
    }

}
