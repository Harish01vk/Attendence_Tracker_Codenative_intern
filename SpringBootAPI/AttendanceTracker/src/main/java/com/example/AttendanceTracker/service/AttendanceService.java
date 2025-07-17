package com.example.AttendanceTracker.service;

import com.example.AttendanceTracker.Model.Attendance;
import com.example.AttendanceTracker.repositry.AttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {
    @Autowired
    AttendanceRepo attendanceRepo;
    public void PostAttendance(Attendance attendance) {
        attendanceRepo.save(attendance);
    }

    public List<Attendance> getAttendance() {
       return attendanceRepo.findAll();
    }

    public List<Attendance> RetrieveFromDate(LocalDate date) {
        return attendanceRepo.findByAttendanceDate(date);
    }
}
