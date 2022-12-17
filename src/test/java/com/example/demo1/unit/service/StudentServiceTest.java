package com.example.demo1.unit.service;

import com.example.demo1.dto.StudentDto;
import com.example.demo1.model.Student;
import com.example.demo1.repository.StudentRepository;
import com.example.demo1.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    private StudentRepository studentRepository;
    private StudentService studentService;

    @BeforeEach
    public void init() {
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentService(studentRepository);
    }

    @Test
    void add() {
        when(studentRepository.save(any(Student.class))).thenReturn(new Student());

        var result = studentService.add(new StudentDto(1, "banty", 12));

        assertNotNull(result);
        assertTrue(result instanceof StudentDto);

        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void get() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(new Student()));

        var result = studentService.get(1);

        assertNotNull(result);
        assertTrue(result instanceof StudentDto);

        verify(studentRepository, times(1)).findById(anyLong());
    }

    @Test
    void testGetForError() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));

        assertThrows(RuntimeException.class, () -> studentService.get(1));

        verify(studentRepository, times(1)).findById(anyLong());
    }

    @Test
    void getAll() {
        when(studentRepository.findAll()).thenReturn(new ArrayList<>());

        var result = studentService.getAll();

        assertNotNull(result);
        assertTrue(result instanceof List<StudentDto>);

        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void update() {
        Student student = new Student();
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        var result = studentService.update(new StudentDto(1, "banty", 12));

        assertNotNull(result);
        assertTrue(result instanceof StudentDto);

        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void delete() {
        doNothing().when(studentRepository).deleteById(anyLong());
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(new Student()));

        var result = studentService.delete(3);

        assertNotNull(result);
        assertTrue(result instanceof StudentDto);

        verify(studentRepository,times(1)).deleteById(anyLong());
    }
}