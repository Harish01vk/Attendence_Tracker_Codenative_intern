package com.example.attendence_tracker.Model;

import java.util.List;

public class TeacherInstance {
    private int teacherID;
    private String teacherName;
    private String teacherEmail;
    private String teacherPassword;
    private List<CourseInstance> courses;

    public TeacherInstance() {
    }

    public TeacherInstance(int teacherID, String teacherName, String teacherEmail, String teacherPassword) {
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.teacherEmail = teacherEmail;
        this.teacherPassword = teacherPassword;
    }

    public TeacherInstance(int teacherID, String teacherName, String teacherEmail, String teacherPassword, List<CourseInstance> courses) {
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.teacherEmail = teacherEmail;
        this.teacherPassword = teacherPassword;
        this.courses = courses;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }

    public List<CourseInstance> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseInstance> courses) {
        this.courses = courses;
    }
} 