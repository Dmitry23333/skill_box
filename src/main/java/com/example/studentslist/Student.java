package com.example.studentslist;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
}
