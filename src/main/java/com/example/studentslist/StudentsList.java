package com.example.studentslist;

import com.example.studentslist.dao.DAO;
import com.example.studentslist.event.EventHolderAdd;
import com.example.studentslist.event.EventHolderRemove;
import com.example.studentslist.event.EventHolderStartApp;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Getter
@RequiredArgsConstructor
public class StudentsList {

    private final List<Student> studentList;

    private final ApplicationEventPublisher publisher;

    private final DAO dao;

    @Value("${app.init}")
    private String initMessage;

    @PostConstruct
    @EventListener(ApplicationStartedEvent.class)
    public void init() {
        studentList.addAll(dao.initStudents());
        publisher.publishEvent(new EventHolderStartApp(this, studentList, initMessage));
    }

    @EventListener(ApplicationReadyEvent.class)
    public void addStudent(Student student) {
        studentList.add(student);
        publisher.publishEvent(new EventHolderAdd(this, student));
    }

    @EventListener(ApplicationReadyEvent.class)
    public void removeStudentById(int id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId() == id) {
                publisher.publishEvent(new EventHolderRemove(this, studentList.get(i)));
                studentList.remove(studentList.get(i));
            }
        }
    }

    public void removeAll() {
        studentList.clear();
    }


    public void print() {
        for (Student student : studentList) {
            System.out.println(student);
        }
    }
}
