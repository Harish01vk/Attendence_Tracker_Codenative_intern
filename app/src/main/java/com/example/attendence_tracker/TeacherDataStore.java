package com.example.attendence_tracker;

import com.example.attendence_tracker.Model.TeacherInstance;

public class TeacherDataStore {

    private static TeacherInstance currentTeacher = null;

    public static void setCurrentTeacher(TeacherInstance teacher) {
        currentTeacher = teacher;
    }

    public static TeacherInstance getCurrentTeacher() {
        return currentTeacher;
    }

    public static void clear() {
        currentTeacher = null;
    }

    public static boolean isLoggedIn() {
        return currentTeacher != null;
    }
} 