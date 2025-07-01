package com.example.attendence_tracker;

public class ReportModel {
    private String studentName;
    private int daysPresent;
    private int daysAbsent;

    public ReportModel(String studentName, int daysPresent, int daysAbsent) {
        this.studentName = studentName;
        this.daysPresent = daysPresent;
        this.daysAbsent = daysAbsent;
    }

    public String getStudentName() { return studentName; }
    public int getDaysPresent() { return daysPresent; }
    public int getDaysAbsent() { return daysAbsent; }
}
