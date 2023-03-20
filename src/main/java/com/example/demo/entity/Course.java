package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    private  int courseid;
    private String onlinecourse;


    private String stream;

    @ManyToMany(mappedBy = "courseList")
    List<Student> students;




}
