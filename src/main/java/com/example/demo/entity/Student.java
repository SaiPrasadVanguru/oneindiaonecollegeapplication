package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    private int id;
    private String name;
    private String collegename;

    private String degree;
    private String branch;

    @ManyToMany
    @JoinColumn()
    private List<Course> courseList;




}
