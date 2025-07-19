package com.example.attendence_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.attendence_tracker.Model.TeacherInstance;
import com.example.attendence_tracker.TeacherDataStore;

public class HomeActivity extends AppCompatActivity {

    TextView welcomeText, totalClasses;
    Button btnMarkAttendance, btnViewAttendance, btnStudentList, btnTimeTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(""); // Remove app name from toolbar
            toolbar.setNavigationIcon(android.R.drawable.ic_menu_myplaces);
            toolbar.setNavigationOnClickListener(v -> {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            });
        }

        welcomeText = findViewById(R.id.welcomeText);

        // Get teacher data and set welcome message
        TeacherInstance currentTeacher = TeacherDataStore.getCurrentTeacher();
        if (currentTeacher == null) {
            // If not logged in, redirect to login
            Intent intent = new Intent(HomeActivity.this, TeacherLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        welcomeText.setText("Welcome, " + currentTeacher.getTeacherName() + "!");

        // Buttons
        btnMarkAttendance = findViewById(R.id.btnMarkAttendance);
        btnViewAttendance = findViewById(R.id.btnViewAttendance);
        btnStudentList = findViewById(R.id.btnStudentList);
        btnTimeTable = findViewById(R.id.btnReports); // Reuse the old reports button id

        // Actions
        btnMarkAttendance.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ClassSelectionActivity.class);
            startActivity(intent);
        });

        btnViewAttendance.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ClassSelectionActivity.class);
            intent.putExtra("forViewAttendance", true); // Add a flag to indicate view mode
            startActivity(intent);
        });

        btnStudentList.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, StudentListActivity.class));
        });

        btnTimeTable.setText("🗓️ Time Table");
        btnTimeTable.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, TimeTableActivity.class));
        });
    }
}