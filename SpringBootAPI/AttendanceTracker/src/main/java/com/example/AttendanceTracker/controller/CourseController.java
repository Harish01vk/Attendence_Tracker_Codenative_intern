package com.example.AttendanceTracker.controller;

import com.example.AttendanceTracker.Model.Course;
import com.example.AttendanceTracker.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("Course/GetCourse")
    public List<Course> GetAttendance(){
       return courseService.getCourse();
    }

}
