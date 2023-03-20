package com.example.demo.controller;


import com.example.demo.dto.Studentdto;
import com.example.demo.dto.Studentdto2;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentservice;

    @PostMapping("/register")
    public String registerStudent(@RequestBody Studentdto studentdto)
    {
        return studentservice.addStudent(studentdto);
    }
    @PutMapping("/update")
    public String updateStudent(@RequestBody Studentdto studentdto)
    {
        return studentservice.updateStudent(studentdto);
    }
    @GetMapping("/get/{courseid}")
    public ResponseEntity<List<Studentdto>> getStudent(@PathVariable int courseid)
    {
        return studentservice.getAllStudents(courseid);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Studentdto>> getAllStudents()
    {
        return studentservice.getAllStudents();
    }
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id)
    {
        return studentservice.deleteStudent(id);
    }
    @GetMapping("/faculty/{facultyname}")
    public ResponseEntity<List<Studentdto>> getbyFacultyName(@PathVariable  String facultyname)
    {
        return studentservice.getbyFacultyName(facultyname);
    }
    @PostMapping("/nocourse")
    public String addStudentNoCourse(@RequestBody Studentdto2 studentdto2)
    {
        return studentservice.addStudentNoCourse(studentdto2);
    }

    @GetMapping("/getby/{onlinecourse}")
    public List<Studentdto> studentdtos(@PathVariable  String onlinecourse)
    {
        return studentservice.studentdtos(onlinecourse);
    }
}
