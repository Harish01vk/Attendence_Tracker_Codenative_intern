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
@Table(name = "Attendance")
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendanceID")
    private int attendanceID;

    @Column(name = "studentID")
    private int studentID;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "attendanceDate")
    private LocalDate attendanceDate;

    @Column(name = "courseID")
    private int courseID;

    @Column(name = "attendanceStatus")
    private Boolean attendanceStatus;

    @Column(name = "studentName")
    private String studentName;
}
