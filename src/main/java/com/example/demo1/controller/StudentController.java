package com.example.demo1.controller;

import com.example.demo1.dto.StudentDto;
import com.example.demo1.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto) {
        var student = studentService.add(studentDto);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAll() {
        var students = studentService.getAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{roll}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable long roll) {
        var student = studentService.get(roll);
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto) {
        var student = studentService.update(studentDto);
        return new ResponseEntity<>(student,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{roll}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable long roll) {
        var student = studentService.delete(roll);
        return new ResponseEntity<>(student,HttpStatus.ACCEPTED);
    }

}
