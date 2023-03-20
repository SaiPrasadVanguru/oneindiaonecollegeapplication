package com.example.demo.service;


import com.example.demo.dto.Coursedto;
import com.example.demo.dto.Facultydto;
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
public class FacultyService {

    @Autowired
    FacultyRepo facultyrepo;

    @Autowired
    CourseRepo courserepo;


    public String addFaculty(Facultydto facultydto)
    {
        Optional<Faculty> optional = facultyrepo.findById(facultydto.getFacultyid());
        Faculty faculty = null;
        if(optional.isPresent())
        {
            return "Faculty id is already present";
        }
        else
        {
            faculty = new Faculty();
            faculty.setFacultyid(facultydto.getFacultyid());
            faculty.setFacultyname(facultydto.getFacultyname());
            faculty.setCollegename(facultydto.getCollegename());
            List<Coursedto> coursedtos = facultydto.getCoursedtos();
            List<Course> courses = new ArrayList<>();
            for(Coursedto course : coursedtos)
            {
                Optional<Course> optional1 = courserepo.findById(course.getCourseid());
                if(optional1.isPresent())
                {
                    Course course1 = optional1.get();
                    courses.add(course1);
                    faculty.setCourses(courses);
                }
                else {
                    return "courses are not present and not added to faculty";
                }
            }
            if(faculty!=null)
            {
                facultyrepo.save(faculty);
            }
            return "faculty is assigned to courses with id";
        }
    }
    public String updatefaculty(Facultydto facultydto)
    {
        Optional<Faculty> faculty = facultyrepo.findById(facultydto.getFacultyid());
        if(faculty.isPresent())
        {
            Faculty faculty1 = faculty.get();
            faculty1.setFacultyid(facultydto.getFacultyid());
            faculty1.setFacultyname(facultydto.getFacultyname());
            faculty1.setCollegename(facultydto.getCollegename());
            List<Coursedto> coursedtos = facultydto.getCoursedtos();
            List<Course> courses = new ArrayList<>();
            for(Coursedto course : coursedtos)
            {
                Optional<Course> optional1 = courserepo.findById(course.getCourseid());
                if(optional1.isPresent())
                {
                    Course course1 = optional1.get();
                    courses.add(course1);
                    faculty1.setCourses(courses);
                }
                else {
                    return "course id is not present";
                }
            }
            if(faculty1!=null)
            {
                facultyrepo.save(faculty1);
            }
            return "faculty info updated";
        }
        else {
            return "faculty id is not present";
        }
    }
    public ResponseEntity<List<Facultydto>> getFaculties()
    {
        List<Faculty> faculties = facultyrepo.findAll();
        if(faculties.size()>0)
        {
            List<Facultydto> facultydtos = new ArrayList<>();
            for (Faculty faculty:faculties)
            {
                Facultydto facultydto = new Facultydto();
                facultydto.setFacultyid(faculty.getFacultyid());
                facultydto.setFacultyname(faculty.getFacultyname());
                facultydto.setCollegename(faculty.getCollegename());
                List<Course> courses = faculty.getCourses();
                List<Coursedto> coursedtos = new ArrayList<>();
                for (Course course : courses) {
                    Coursedto coursedto = new Coursedto();
                    coursedto.setCourseid(course.getCourseid());
                    coursedto.setOnlinecourse(course.getOnlinecourse());
                    coursedto.setStream(course.getStream());
                    coursedtos.add(coursedto);
                }

                facultydto.setCoursedtos(coursedtos);
                facultydtos.add(facultydto);
            }
            return ResponseEntity.ok(facultydtos);
        }
        else {
            throw new ResourceNotFoundException("faculties are empty");
        }
    }
    public String deletefaculty(int facultyid)
    {
        Optional<Faculty> optional = facultyrepo.findById(facultyid);
        if(optional.isPresent())
        {
            facultyrepo.deleteById(facultyid);
            return "faculty deleted";
        }
        else {
            return "faculty not present";
        }
    }

}

