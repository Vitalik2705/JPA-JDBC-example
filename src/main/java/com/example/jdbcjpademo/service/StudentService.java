package com.example.jdbcjpademo.service;

import com.example.jdbcjpademo.entity.Student;
import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    List<Student> getStudents();
    Student getStudentByEmail(String email);
    boolean deleteStudentById(Long id);
    Student updateStudentById(Long id, Student student);
}
