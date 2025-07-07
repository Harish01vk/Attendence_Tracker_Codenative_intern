package com.example.attendence_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendence_tracker.Model.CourseInstance;
import com.example.attendence_tracker.RetrofitService.CourseAPI;
import com.example.attendence_tracker.RetrofitService.RetroFitService;

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

        RetroFitService retroFitService = new RetroFitService();
        CourseAPI courseAPI = retroFitService.getRetrofit().create(CourseAPI.class);

        courseAPI.getCourse().enqueue(new Callback<List<CourseInstance>>() {
            @Override
            public void onResponse(Call<List<CourseInstance>> call, Response<List<CourseInstance>> response) {
                List<CourseInstance> courseList = response.body();

                if (courseList != null && !courseList.isEmpty()) {
                    adapter.setClassList(courseList);
                } else {
                    adapter.setClassList(new ArrayList<>()); // prevents crash, shows empty list
                    Toast.makeText(ClassSelectionActivity.this, "No courses found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CourseInstance>> call, Throwable t) {
                Toast.makeText(ClassSelectionActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        }); // <-- closes enqueue()
    } // <-- closes onCreate()
} // <-- closes ClassSelectionActivity
