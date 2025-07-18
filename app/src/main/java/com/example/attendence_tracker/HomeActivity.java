package com.example.attendence_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.attendence_tracker.Model.TeacherInstance;
import com.example.attendence_tracker.TeacherDataStore;

public class HomeActivity extends AppCompatActivity {

    TextView welcomeText, totalClasses;
    Button btnMarkAttendance, btnViewAttendance, btnStudentList, btnReports, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcomeText = findViewById(R.id.welcomeText);
        totalClasses = findViewById(R.id.totalClasses);

        // Get teacher data and set welcome message
        TeacherInstance currentTeacher = TeacherDataStore.getCurrentTeacher();
        if (currentTeacher == null) {
            // Hardcode teacher credentials for development
            currentTeacher = new TeacherInstance(1, "Deva Kumar", "deva.kumar@example.com", "password1");
            TeacherDataStore.setCurrentTeacher(currentTeacher);
        }
        welcomeText.setText("Welcome, " + currentTeacher.getTeacherName() + "!");

        // Buttons
        btnMarkAttendance = findViewById(R.id.btnMarkAttendance);
        btnViewAttendance = findViewById(R.id.btnViewAttendance);
        btnStudentList = findViewById(R.id.btnStudentList);
        btnReports = findViewById(R.id.btnReports);
        btnLogout = findViewById(R.id.btnLogout);

        // Actions
        btnMarkAttendance.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ClassSelectionActivity.class);
            startActivity(intent);
        });

        btnViewAttendance.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, ViewAttendanceActivity.class));
        });

        btnStudentList.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, StudentListActivity.class));
        });

        btnReports.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, ReportActivity.class));
        });

        btnLogout.setOnClickListener(v -> {
            TeacherDataStore.clear();
            Intent intent = new Intent(HomeActivity.this, TeacherLoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}