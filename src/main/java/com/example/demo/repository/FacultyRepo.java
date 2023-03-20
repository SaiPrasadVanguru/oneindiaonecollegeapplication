package com.example.demo.repository;


import com.example.demo.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepo extends JpaRepository<Faculty,Integer> {
}
