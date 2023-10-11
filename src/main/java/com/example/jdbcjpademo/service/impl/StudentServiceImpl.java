package com.example.jdbcjpademo.service.impl;

import com.example.jdbcjpademo.entity.Student;
import com.example.jdbcjpademo.repository.StudentRepository;
import com.example.jdbcjpademo.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        Student createdStudent = new Student();
        BeanUtils.copyProperties(student, createdStudent);
        studentRepository.save(createdStudent);
        return createdStudent;
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentRepository.getStudentByEmail(email);
    }

    @Override
    public boolean deleteStudentById(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        studentOptional.ifPresent(student -> studentRepository.delete(student));
        return studentOptional.isPresent();
    }

    @Override
    public Student updateStudentById(Long id, Student student) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isPresent()) {
            Student oldStudent = studentOptional.get();
            oldStudent.setFirstName(student.getFirstName());
            oldStudent.setLastName(student.getLastName());
            oldStudent.setEmail(student.getEmail());

            return studentRepository.save(oldStudent);
        } else {
            return null;
        }
    }
}
