package com.example.AttendanceTracker.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attendanceID;


    private int studentID;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate attendanceDate;
    private int courseID;   

    private Boolean attendanceStatus;
    private String studentName;
}
