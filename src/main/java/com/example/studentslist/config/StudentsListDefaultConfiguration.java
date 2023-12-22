package com.example.studentslist.config;

import com.example.studentslist.StudentsList;
import com.example.studentslist.StudentsWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class StudentsListDefaultConfiguration {

    @Bean
    public StudentsWorker studentsWorker(StudentsList studentsList) {
        return new StudentsWorker(studentsList);
    }
}
