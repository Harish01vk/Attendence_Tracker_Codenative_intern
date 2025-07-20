package com.example.attendence_tracker;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendence_tracker.Model.StudentInstance;
import com.example.attendence_tracker.RetrofitService.RetroFitService;
import com.example.attendence_tracker.RetrofitService.StudentAPI;
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

        fetchStudentsFromBackend();
    }

    private void fetchStudentsFromBackend() {
        RetroFitService retroFitService = new RetroFitService();
        StudentAPI studentAPI = retroFitService.getRetrofit().create(StudentAPI.class);

        studentAPI.GetStudents().enqueue(new retrofit2.Callback<List<StudentInstance>>() {
            @Override
            public void onResponse(retrofit2.Call<List<StudentInstance>> call, retrofit2.Response<List<StudentInstance>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    StudentListAdapter adapter = new StudentListAdapter(response.body());
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(StudentListActivity.this, "No students found", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(retrofit2.Call<List<StudentInstance>> call, Throwable t) {
                Toast.makeText(StudentListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
