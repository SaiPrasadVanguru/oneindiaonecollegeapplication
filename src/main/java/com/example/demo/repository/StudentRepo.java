package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student,Integer> {

    @Query(value = "select * from student where id=(select students_id from student_course_list where course_list_courseid =(select courseid from course where onlinecourse=?1))",nativeQuery = true)
    public List<Student>  students(String onlinecourse);
}
