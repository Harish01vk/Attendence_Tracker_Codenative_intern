package com.example.attendence_tracker;

public class AttendanceRecord {
    private String studentName;
    private String status; // "Present" or "Absent"

    public AttendanceRecord(String studentName, String status) {
        this.studentName = studentName;
        this.status = status;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStatus() {
        return status;
    }
}
