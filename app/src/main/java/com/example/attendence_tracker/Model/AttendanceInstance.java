package com.example.attendence_tracker.Model;

import java.util.Date;

public class AttendanceInstance {
    private Integer attendanceID;
    private int studentID;
    private boolean attendanceStatus;
    private String attendanceDate;
    private int courseID;
    private String studentName;
    public AttendanceInstance(int attendanceID, int studentID, Date attendanceDate, boolean attendanceStatus ,String studentName, int courseID) {
        this.attendanceStatus = attendanceStatus;
        this.studentID = studentID;
        this.attendanceDate = String.valueOf(attendanceDate);
        this.attendanceID = attendanceID;
        this.studentName = studentName;
        this.courseID = courseID;

    }

    public String getStudentName() {
        return studentName;
    }

    public void setAttendanceID(Integer attendanceID) {
        this.attendanceID = attendanceID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public AttendanceInstance() {
    }

    public int getAttendanceID() {
        return attendanceID;
    }

    public void setAttendanceID(int attendanceID) {
        this.attendanceID = attendanceID;
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public boolean isAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(boolean attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
}
