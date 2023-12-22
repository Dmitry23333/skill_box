package com.example.studentslist.impl;


import com.example.studentslist.Student;
import com.example.studentslist.dao.DAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Component
@ConditionalOnProperty(prefix = "app", name = "init", havingValue = "true")
public class InitStudentsListDAO implements DAO {

    @Value("${app.init.path}")
    private String path;

    @Override
    public List<Student> initStudents() {
        List<Student> students = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(path))) {
            sc.useLocale(Locale.ENGLISH);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] line1 = line.split(";");
                students.add(new Student(Integer.parseInt(line1[0]), line1[1], line1[2], Integer.parseInt(line1[3])));
            }

        } catch (FileNotFoundException e) {
            System.err.println("FILE NOT FOUND");
        }
        return students;
    }
}
