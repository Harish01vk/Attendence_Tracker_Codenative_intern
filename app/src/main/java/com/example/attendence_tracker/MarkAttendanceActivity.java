package com.example.attendence_tracker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MarkAttendanceActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AttendanceMarkAdapter adapter;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        recyclerView = findViewById(R.id.recyclerViewAttendance);
        btnSubmit = findViewById(R.id.btnSubmitAttendance);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Dummy student list
        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Harish M", false));
        studentList.add(new Student("Divya R", false));
        studentList.add(new Student("Rahul K", false));

        adapter = new AttendanceMarkAdapter(studentList);
        recyclerView.setAdapter(adapter);

        btnSubmit.setOnClickListener(view -> {
            ArrayList<Student> presentStudents = adapter.getMarkedStudents();
            Toast.makeText(this, "Attendance marked for " + presentStudents.size() + " students", Toast.LENGTH_SHORT).show();

            // TODO: Send data to backend (Spring Boot) using Retrofit
        });
    }
}
