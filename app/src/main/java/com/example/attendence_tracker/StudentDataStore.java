package com.example.attendence_tracker;

import com.example.attendence_tracker.Model.StudentInstance;

import java.util.ArrayList;
import java.util.List;

public class StudentDataStore {

    private static List<StudentInstance> studentList = new ArrayList<>();

    public static void setStudentList(List<StudentInstance> students) {
        studentList = students;
    }

    public static List<StudentInstance> getStudentList() {
        return studentList;
    }

    public static void clear() {
        studentList.clear();
    }
}
