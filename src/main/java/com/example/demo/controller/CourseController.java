package com.example.demo.controller;


import com.example.demo.dto.Coursedto;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    CourseService courseService;

    @PostMapping("/add/course")
    public String addCourse(@RequestBody Coursedto coursedto)
    {
        return courseService.addCourse(coursedto);
    }

    @GetMapping("/get/{faculty_id}")
    public ResponseEntity<List<Coursedto>> getbyFaculty(@PathVariable  int faculty_id)
    {
        return courseService.getcourse(faculty_id);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Coursedto>> coursedtos()
    {
        return courseService.getcourses();
    }
    @DeleteMapping("/course/delete/{courseid}")
    public String delete(@PathVariable int courseid)
    {
        return courseService.deleteCourse(courseid);
    }
    @PutMapping("/course/update")
    public String updateCourse(@RequestBody Coursedto coursedto)
    {
        return courseService.updateCourse(coursedto);
    }

}

