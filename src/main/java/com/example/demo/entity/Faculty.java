package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Faculty {

    @Id
    private int facultyid;
    private String facultyname;
    private String collegename;


    @OneToMany
    @JoinColumn(name="faculty_id")
    private List<Course> courses;


}
