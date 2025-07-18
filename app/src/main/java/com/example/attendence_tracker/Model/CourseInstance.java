package com.example.attendence_tracker.Model;

public class CourseInstance {

    public int getCourseID() {
        return courseID;
    }

    public CourseInstance(int courseID, String courseName, String courseSemester) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseSemester = courseSemester;
    }



    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseSemester() {
        return courseSemester;
    }

    public void setCourseSemester(String courseSemester) {
        this.courseSemester = courseSemester;
    }

    private int courseID;
    private String courseName;
    private String courseSemester;



}
