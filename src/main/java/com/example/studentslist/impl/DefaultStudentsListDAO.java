package com.example.studentslist.impl;

import com.example.studentslist.Student;
import com.example.studentslist.dao.DAO;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@ConditionalOnProperty(prefix = "app", name = "init", havingValue = "false")
@Component
public class DefaultStudentsListDAO implements DAO {
    @Override
    public List<Student> initStudents() {
        return new ArrayList<>();
    }
}
