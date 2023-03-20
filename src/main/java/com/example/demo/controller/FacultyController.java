package com.example.demo.controller;


import com.example.demo.dto.Facultydto;
import com.example.demo.entity.Course;
import com.example.demo.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    FacultyService facultyservice;


    @PostMapping("/add")
    public String registerFaculty(@RequestBody Facultydto facultydto)
    {
        return facultyservice.addFaculty(facultydto);
    }

    @PutMapping("/update")
    public String updateFaculty(@RequestBody Facultydto facultydto)
    {
        return facultyservice.updatefaculty(facultydto);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Facultydto>> getFaculties()
    {
        return facultyservice.getFaculties();
    }
    @DeleteMapping("/delete/{facultyid}")
    public String delete(@PathVariable int facultyid)
    {
        return facultyservice.deletefaculty(facultyid);
    }
}