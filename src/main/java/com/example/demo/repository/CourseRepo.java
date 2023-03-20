package com.example.demo.repository;

import com.example.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course,Integer> {



    @Query(value = "select * from course where faculty_id=(select facultyid from faculty where facultyid=?1)",nativeQuery = true)
    public List<Course> getByFacultyId(int facultyid);



    @Query(value = "select * from course where faculty_id=(select facultyid from faculty where facultyname=?1)",nativeQuery = true)
    public List<Course> getbyfacultyname(String facultyname);


}
