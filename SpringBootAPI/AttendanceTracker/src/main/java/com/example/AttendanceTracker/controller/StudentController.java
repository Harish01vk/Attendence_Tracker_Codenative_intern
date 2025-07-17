package com.example.AttendanceTracker.controller;


import com.example.AttendanceTracker.Model.Student;
import com.example.AttendanceTracker.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController


@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/Students/GetStudents")
    public List<Student> GetStudents(){
      return  studentService.GetStudents();
    }
    @PostMapping("Students/AddStudents")
    public void AddStudent(@RequestBody Student student){
        studentService.AddStudent(student);
    }

}
