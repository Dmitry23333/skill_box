package com.example.studentslist.event;

import com.example.studentslist.Student;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EventHolderAdd extends ApplicationEvent {
    private final Student student;

    public EventHolderAdd(Object source, Student student) {
        super(source);
        this.student = student;
    }
}
