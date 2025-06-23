package com.example.attendence_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    TextView welcomeText, totalClasses;
    Button btnMarkAttendance, btnViewAttendance, btnStudentList, btnReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcomeText = findViewById(R.id.welcomeText);
        totalClasses = findViewById(R.id.totalClasses);

        // Normally fetched from login/session data
        welcomeText.setText("Welcome, Mr. Harish!");

        // Buttons
        btnMarkAttendance = findViewById(R.id.btnMarkAttendance);
        btnViewAttendance = findViewById(R.id.btnViewAttendance);
        btnStudentList = findViewById(R.id.btnStudentList);
        btnReports = findViewById(R.id.btnReports);

        // Actions
        btnMarkAttendance.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, MarkAttendanceActivity.class));
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
    }
}
