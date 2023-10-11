package com.example.jdbcjpademo.controller;

import com.example.jdbcjpademo.entity.Student;
import com.example.jdbcjpademo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {
    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = studentService.getStudents();
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student createdStudent = studentService.addStudent(student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    @GetMapping("{email}")
    public ResponseEntity<Student> getStudentByEmail(@PathVariable String email) {
        Student student = studentService.getStudentByEmail(email);

        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String,Boolean>> deleteStudentById(@PathVariable Long id) {
        boolean deleted = studentService.deleteStudentById(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted:", deleted);
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> updateTask(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudentById(id, student);

        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
