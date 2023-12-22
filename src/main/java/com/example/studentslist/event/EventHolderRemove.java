package com.example.studentslist.event;

import com.example.studentslist.Student;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EventHolderRemove extends ApplicationEvent {
    private final Student student;

    public EventHolderRemove(Object source, Student student) {
        super(source);
        this.student = student;
    }
}
