package com.example.attendence_tracker;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendence_tracker.Model.StudentInstance;
import com.example.attendence_tracker.StudentDataStore; // 👈 this is your global holder

import java.util.List;

public class StudentListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        recyclerView = findViewById(R.id.attendanceRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadStudentsFromStore();  // 🔄 Changed
    }

    private void loadStudentsFromStore() {
        List<StudentInstance> students = StudentDataStore.getStudentList();

        if (students != null && !students.isEmpty()) {
            StudentListAdapter adapter = new StudentListAdapter(students);
            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(StudentListActivity.this, "No students found or not loaded yet", Toast.LENGTH_LONG).show();
            Log.e("StudentListActivity", "Student list is null or empty");

            // You can show a fallback or error message if needed
        }
    }
}
