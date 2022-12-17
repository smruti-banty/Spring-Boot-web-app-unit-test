package com.example.demo1.service;

import com.example.demo1.dto.StudentDto;
import com.example.demo1.model.Student;
import com.example.demo1.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDto add(StudentDto studentDto) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        studentRepository.save(student);
        return convert(student);
    }

    public StudentDto get(long roll) {
        var optionalStudent = studentRepository.findById(roll);

        if (optionalStudent.isPresent()) {
            var student = optionalStudent.get();
            return convert(student);
        }

        throw new RuntimeException("Student not found for roll: " + roll);
    }

    public List<StudentDto> getAll() {
        return studentRepository.findAll().stream().map(student -> convert(student)).collect(Collectors.toList());
    }

    public StudentDto update(StudentDto studentDto) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        get(student.getRoll());
        studentRepository.save(student);
        return convert(student);
    }

    public StudentDto delete(long roll) {
        var studentDto = get(roll);
        studentRepository.deleteById(roll);
        return studentDto;
    }

    private StudentDto convert(Student student) {
        return new StudentDto(student.getRoll(), student.getName(), student.getAge());
    }

}
