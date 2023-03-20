package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Studentdto2 {
    private int id;
    private String name;
    private String collegename;

    private String degree;
    private String branch;
}
