package com.example.AttendanceTracker.service;

import com.example.AttendanceTracker.Model.Student;
import com.example.AttendanceTracker.repositry.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService {
    @Autowired
    StudentRepo studentRepo;
    public List<Student> GetStudents() {
      return  studentRepo.findAll();
    }

    public void AddStudent(Student student)
    {
        studentRepo.save(student);

    }
}
