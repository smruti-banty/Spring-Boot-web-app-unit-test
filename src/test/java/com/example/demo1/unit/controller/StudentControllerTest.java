package com.example.demo1.unit.controller;


import com.example.demo1.controller.StudentController;
import com.example.demo1.dto.StudentDto;
import com.example.demo1.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    private final String URI = "/api/v1/student";

    @Test
    public void addStudent() throws Exception {
        var student = new StudentDto(1,"smruti",12);
        when(studentService.add(any(StudentDto.class))).thenReturn(student);

        mockMvc.perform(
                post(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student))
        ).andExpect(status().isCreated()).andDo(print());
    }

    @Test
    void getAll() throws Exception {
        when(studentService.getAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get(URI)).andExpect(status().isOk()).andDo(print());
    }

    @Test
    void getStudent() throws Exception {
        when(studentService.get(anyLong())).thenReturn(new StudentDto(1,"smruti",23));

        mockMvc.perform(get(URI+"/1")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    void updateStudent() throws Exception {
        when(studentService.update(any(StudentDto.class))).thenReturn(new StudentDto(1,"smruti",23));

        mockMvc.perform(put(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new StudentDto(1,"smruti",23))))
                .andExpect(status().isAccepted()).andDo(print());

    }

    @Test
    void deleteStudent() throws Exception {
        when(studentService.delete(anyLong())).thenReturn(new StudentDto(1,"smruti",23));

        mockMvc.perform(delete(URI+"/1")).andExpect(status().isAccepted()).andDo(print());
    }

}