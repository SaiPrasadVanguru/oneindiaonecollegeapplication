package com.example.demo.service;


import com.example.demo.dto.Coursedto;
import com.example.demo.entity.Course;
import com.example.demo.entity.Faculty;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CourseRepo;
import com.example.demo.repository.FacultyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CourseService {
    @Autowired
    CourseRepo courserepo;

    @Autowired
    FacultyRepo facultyrepo;

    public String addCourse(Coursedto coursedto) {
        Optional<Course> course = courserepo.findById(coursedto.getCourseid());
        if (course.isPresent()) {
            return "courseID is already taken and take new id";
        } else {
            Course course1 = new Course();
            course1.setCourseid(coursedto.getCourseid());
            course1.setOnlinecourse(coursedto.getOnlinecourse());
            course1.setStream(coursedto.getStream());
            courserepo.save(course1);
            return "course is saved";
        }
    }

    public ResponseEntity<List<Coursedto>> getcourse(int facultyid) {
        Optional<Faculty> faculty = facultyrepo.findById(facultyid);
        if (faculty.isPresent()) {
            List<Course> course = courserepo.getByFacultyId(facultyid);
            List<Coursedto> coursedto = new ArrayList<>();
            if (course.size() != 0) {
                for (Course course1 : course) {
                    Coursedto coursedto1 = new Coursedto();
                    coursedto1.setCourseid(course1.getCourseid());
                    coursedto1.setOnlinecourse(course1.getOnlinecourse());
                    coursedto1.setStream(course1.getStream());
                    coursedto.add(coursedto1);

                }
                return ResponseEntity.ok(coursedto);
            } else {
                throw new ResourceNotFoundException("Course are not present for that faculty id");
            }

        }
        else {
            throw new ResourceNotFoundException("faculty is not present with this id");
        }
    }

    public ResponseEntity<List<Coursedto>> getcourses() {
        List<Course> courses = courserepo.findAll();
        if (courses.size() > 0) {
            List<Coursedto> coursedtoList = new ArrayList<>();
            for (Course course : courses) {
                Coursedto coursedto = new Coursedto();
                coursedto.setCourseid(course.getCourseid());
                coursedto.setOnlinecourse(course.getOnlinecourse());
                coursedto.setStream(course.getStream());
                coursedtoList.add(coursedto);
            }
            return ResponseEntity.ok(coursedtoList);
        } else {
            throw new ResourceNotFoundException("courses are empty");
        }
    }

    public String deleteCourse(int courseid) {
        Optional<Course> optional = courserepo.findById(courseid);
        if (optional.isPresent()) {
            courserepo.deleteById(courseid);
            return "course is deleted";
        } else {
            return "course is not present";
        }
    }

    public String updateCourse(Coursedto coursedto) {
        Optional<Course> course = courserepo.findById(coursedto.getCourseid());
        if (course.isPresent()) {
            Course course1 = course.get();
            course1.setCourseid(coursedto.getCourseid());
            course1.setOnlinecourse(coursedto.getOnlinecourse());
            course1.setStream(coursedto.getStream());
            courserepo.save(course1);
            return "course gets updated";
        } else {
            return "course id not found";
        }
    }
}
