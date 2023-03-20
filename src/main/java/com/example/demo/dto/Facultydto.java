package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Facultydto {
    private int facultyid;
    private String facultyname;
    private String collegename;


    private List<Coursedto> coursedtos;
}

