package com.example.demo.service;


import com.example.demo.dto.Coursedto;
import com.example.demo.dto.Studentdto;
import com.example.demo.dto.Studentdto2;
import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CourseRepo;
import com.example.demo.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    @Autowired
    StudentRepo studentrepo;
    @Autowired
    CourseRepo courserepo;

    public String addStudent(Studentdto studentdto) {
        Optional<Student> optional = studentrepo.findById(studentdto.getId());
        Student student = null;
        if (optional.isPresent()) {
            return "student is already registered";

        } else {
            student = new Student();
            student.setId(studentdto.getId());
            student.setName(studentdto.getName());
            student.setBranch(studentdto.getBranch());
            student.setCollegename(studentdto.getCollegename());
            student.setDegree(studentdto.getDegree());

            List<Coursedto> coursedtos = studentdto.getCoursedtos();
            List<Course> courses = new ArrayList<>();
            for (Coursedto coursedto : coursedtos) {
                Optional<Course> optional1 = courserepo.findById(coursedto.getCourseid());
                if (optional1.isPresent()) {
                    Course course = optional1.get();
                    if(course.getStream().equals(studentdto.getDegree())) {
                        courses.add(course);
                        student.setCourseList(courses);
                    }
                    else {
                        return "courses cant added for different streams";
                    }
                } else {
                    return "course doesnot exist and student is not registered to that courses";
                }
            }
            if (student != null) {
                studentrepo.save(student);

            }
            return "student is registered";
        }
    }

    public String updateStudent(Studentdto studentdto) {
        Optional<Student> student = studentrepo.findById(studentdto.getId());
        if (student.isPresent()) {
            Student student1 = student.get();
            student1.setName(studentdto.getName());
            student1.setId(studentdto.getId());
            student1.setBranch(studentdto.getBranch());
            student1.setDegree(studentdto.getDegree());
            student1.setCollegename(studentdto.getCollegename());
            List<Coursedto> coursedtos2 = studentdto.getCoursedtos();
            List<Course> courses2 = new ArrayList<>();
            for (Coursedto coursedto : coursedtos2) {
                Optional<Course> optional1 = courserepo.findById(coursedto.getCourseid());
                if (optional1.isPresent()) {
                    Course course = optional1.get();
                    if(course.getStream().equals(studentdto.getDegree())) {
                        course.setCourseid(coursedto.getCourseid());
                        course.setOnlinecourse(coursedto.getOnlinecourse());
                        courses2.add(course);
                        student1.setCourseList(courses2);
                    }
                    else {
                        return  "you cant give to different stream";
                    }
                } else {
                    return "course doesnot exist and student is not registered to that courses";
                }
            }
            if (student1 != null) {
                studentrepo.save(student1);

            }
            return "student data is updated";
        } else {
            return "student with this id not present";
        }

    }

    public ResponseEntity<List<Studentdto>> getAllStudents(int courseid) {
        Optional<Course> course = courserepo.findById(courseid);
        if (course.isPresent()) {
            List<Student> students = course.get().getStudents();
            if (students.size() > 0) {
                Coursedto course1 = new Coursedto();
                course1.setCourseid(course.get().getCourseid());
                course1.setOnlinecourse(course.get().getOnlinecourse());
                course1.setStream(course.get().getStream());
                List<Coursedto> coursedtos = new ArrayList<>();
                coursedtos.add(course1);
                List<Studentdto> studentdtos = new ArrayList<>();
                for (Student student : students) {
                    Studentdto student2 = new Studentdto();
                    student2.setName(student.getName());
                    student2.setId(student.getId());
                    student2.setDegree(student.getDegree());
                    student2.setBranch(student.getBranch());
                    student2.setCollegename(student.getCollegename());
                    student2.setCoursedtos(coursedtos);
                    studentdtos.add(student2);


                }

                return ResponseEntity.ok(studentdtos);
            } else {
                throw new ResourceNotFoundException("No students are applied to that course");
            }
        } else {
            throw new ResourceNotFoundException("courses are not present with this id");
        }
    }


    public ResponseEntity<List<Studentdto>> getAllStudents()
    {
        List<Student> students = studentrepo.findAll();
        if(students.size()>0)
        {
            List<Studentdto> studentdtos = new ArrayList<>();
            for(Student student:students)
            {
                Studentdto studentdto = new Studentdto();
                studentdto.setId(student.getId());
                studentdto.setName(student.getName());
                studentdto.setBranch(student.getBranch());
                studentdto.setDegree(student.getDegree());
                studentdto.setCollegename(student.getCollegename());
                List<Coursedto> coursedtos = new ArrayList<>();
                List<Course> courses = student.getCourseList();
                for(Course course:courses)
                {
                    Coursedto coursedto = new Coursedto();
                    coursedto.setCourseid(course.getCourseid());
                    coursedto.setOnlinecourse(course.getOnlinecourse());
                    coursedto.setStream(course.getStream());
                    coursedtos.add(coursedto);
                }
                studentdto.setCoursedtos(coursedtos);
                studentdtos.add(studentdto);
            }
            return ResponseEntity.ok(studentdtos);
        }
        else {
            throw new ResourceNotFoundException("No students are found");
        }
    }
    public String deleteStudent(int id)
    {
        Optional<Student> optional = studentrepo.findById(id);
        if(optional.isPresent())
        {
            studentrepo.deleteById(id);
            return "student deleted";
        }
        else {
            return "student is not found";
        }
    }
    public ResponseEntity<List<Studentdto>> getbyFacultyName(String facultyname)
    {
        List<Course> courses = courserepo.getbyfacultyname(facultyname);
        List<Coursedto> coursedtos = new ArrayList<>();
        for(Course course:courses)
        {
            Coursedto coursedto = new Coursedto();
            coursedto.setCourseid(course.getCourseid());
            coursedto.setOnlinecourse(course.getOnlinecourse());
            coursedto.setStream(course.getStream());
            coursedtos.add(coursedto);
        }
        if (courses.size() > 0) {

            List<Studentdto> studentdtos = new ArrayList<>();
            courses.stream().findFirst().stream().forEach(course -> course.getStudents().stream().forEach(student -> {

                Studentdto studentdto = new Studentdto();
                studentdto.setId(student.getId());
                studentdto.setName(student.getName());
                studentdto.setBranch(student.getBranch());
                studentdto.setCollegename(student.getCollegename());
                studentdto.setDegree(student.getDegree());
                studentdto.setCoursedtos(coursedtos);
                studentdtos.add(studentdto);
            }));
            if(studentdtos.size()>0) {
                return ResponseEntity.ok(studentdtos);
            }
            else {
                throw new ResourceNotFoundException("no students are there for that faculty");
            }
        }
        else {
            throw new ResourceNotFoundException("faculty didnot take up any courses ");
        }

    }
    public String addStudentNoCourse(Studentdto2 studentdto2)
    {
        Optional<Student> student = studentrepo.findById(studentdto2.getId());
        if(student.isPresent())
        {
            return "this id is already taken";
        }
        else {
            Student student1 = new Student();
            student1.setId(studentdto2.getId());
            student1.setName(studentdto2.getName());
            student1.setDegree(studentdto2.getDegree());
            student1.setCollegename(studentdto2.getCollegename());
            student1.setBranch(studentdto2.getBranch());
            studentrepo.save(student1);
            return "student added with no online course";
        }
    }
  public List<Studentdto> studentdtos(String onlinecourse)
  {
      List<Student> students = studentrepo.students(onlinecourse);
      List<Studentdto> studentdtos = new ArrayList<>();
      if (students.size()>0)
      {
          for(Student student:students)
          {
              Studentdto studentdto = new Studentdto();
              studentdto.setId(student.getId());
              studentdto.setName(student.getName());
              studentdto.setDegree(student.getDegree());
              studentdto.setCollegename(student.getCollegename());
              studentdto.setBranch(student.getBranch());
              List<Course> courses = student.getCourseList();
              List<Coursedto> coursedtos = new ArrayList<>();
              for (Course course:courses)
              {
                  Coursedto coursedto = new Coursedto();
                  coursedto.setCourseid(course.getCourseid());
                  coursedto.setOnlinecourse(course.getOnlinecourse());
                  coursedto.setStream(course.getStream());
                  coursedtos.add(coursedto);
              }
              studentdto.setCoursedtos(coursedtos);
              studentdtos.add(studentdto);
          }
          return studentdtos;
      }
      else {
        throw new ResourceNotFoundException("No students present for that online course");
      }
  }

}
