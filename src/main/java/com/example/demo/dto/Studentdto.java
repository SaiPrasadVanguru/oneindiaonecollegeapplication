package com.example.demo.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Studentdto {

    private int id;
    private String name;
    private String collegename;
    private String degree;
    private String branch;
    private List<Coursedto> coursedtos;

}
