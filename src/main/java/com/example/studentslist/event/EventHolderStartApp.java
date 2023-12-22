package com.example.studentslist.event;

import com.example.studentslist.Student;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;


@Getter
public class EventHolderStartApp extends ApplicationEvent {

    private final List<Student> students;
    private final String message;

    public EventHolderStartApp(Object source, List<Student> students, String message) {
        super(source);
        this.students = students;
        this.message = message;
    }
}
