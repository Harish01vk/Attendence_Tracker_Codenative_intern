package com.example.attendence_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendence_tracker.Model.CourseInstance;
import com.example.attendence_tracker.Model.TeacherInstance;
import com.example.attendence_tracker.RetrofitService.TeacherAPI;
import com.example.attendence_tracker.RetrofitService.RetroFitService;
import com.example.attendence_tracker.TeacherDataStore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ClassSelectionActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ClassAdapter adapter;
    private List<CourseInstance> classList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_selection);

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new ClassAdapter(new ClassAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CourseInstance courseInstance) {
                Intent intent = new Intent(ClassSelectionActivity.this, MarkAttendanceActivity.class);
                intent.putExtra("courseID", courseInstance.getCourseID());
                 Log.d("DEBUG_COURSE", "ID: " + courseInstance.getCourseID() + ", Name: " + courseInstance.getCourseName());


                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Check if teacher is logged in
        TeacherInstance currentTeacher = TeacherDataStore.getCurrentTeacher();
        if (currentTeacher == null) {
            // Hardcode teacher credentials for development
            currentTeacher = new TeacherInstance(1, "Deva Kumar", "deva.kumar@example.com", "password1");
            TeacherDataStore.setCurrentTeacher(currentTeacher);
        }

        RetroFitService retroFitService = new RetroFitService();
        TeacherAPI teacherAPI = retroFitService.getRetrofit().create(TeacherAPI.class);

        // Get courses for the current teacher
        Log.d("TEACHER_DEBUG", "Fetching courses for teacher ID: " + currentTeacher.getTeacherID());
        Log.d("TEACHER_DEBUG", "Teacher name: " + currentTeacher.getTeacherName());
        Log.d("TEACHER_DEBUG", "API URL: /Teacher/" + currentTeacher.getTeacherID() + "/teacherCourses");
        
        teacherAPI.getTeacherCourses(currentTeacher.getTeacherID()).enqueue(new Callback<List<CourseInstance>>() {
            @Override
            public void onResponse(Call<List<CourseInstance>> call, Response<List<CourseInstance>> response) {
                Log.d("TEACHER_DEBUG", "API Response Code: " + response.code());
                Log.d("TEACHER_DEBUG", "API Response Message: " + response.message());
                
                if (response.isSuccessful()) {
                    List<CourseInstance> courseList = response.body();
                    Log.d("TEACHER_DEBUG", "Response body: " + (courseList != null ? courseList.toString() : "null"));
                    Log.d("TEACHER_DEBUG", "Number of courses received: " + (courseList != null ? courseList.size() : 0));

                    if (courseList != null && !courseList.isEmpty()) {
                        Log.d("TEACHER_DEBUG", "Setting course list with " + courseList.size() + " courses");
                        adapter.setClassList(courseList);
                        Toast.makeText(ClassSelectionActivity.this, "Found " + courseList.size() + " courses", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("TEACHER_DEBUG", "No courses found - setting empty list");
                        adapter.setClassList(new ArrayList<>()); // prevents crash, shows empty list
                        Toast.makeText(ClassSelectionActivity.this, "No courses found for this teacher", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("TEACHER_DEBUG", "API Error: " + response.code() + " - " + response.message());
                    try {
                        String errorBody = response.errorBody() != null ? response.errorBody().string() : "No error body";
                        Log.e("TEACHER_DEBUG", "Error body: " + errorBody);
                    } catch (IOException e) {
                        Log.e("TEACHER_DEBUG", "Error reading error body: " + e.getMessage());
                    }
                    Toast.makeText(ClassSelectionActivity.this, "API Error: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<CourseInstance>> call, Throwable t) {
                Log.e("TEACHER_DEBUG", "Network Error: " + t.getMessage());
                Log.e("TEACHER_DEBUG", "Error type: " + t.getClass().getSimpleName());
                t.printStackTrace();
                Toast.makeText(ClassSelectionActivity.this, "Network Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        }); // <-- closes enqueue()
    } // <-- closes onCreate()
} // <-- closes ClassSelectionActivity
