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
import com.example.attendence_tracker.Model.TimeTableEntry;
import com.example.attendence_tracker.Model.PeriodInstance;
import com.example.attendence_tracker.RetrofitService.TeacherAPI;
import com.example.attendence_tracker.RetrofitService.RetroFitService;
import com.example.attendence_tracker.RetrofitService.TimeTableAPI;
import com.example.attendence_tracker.TeacherDataStore;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
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
                boolean forViewAttendance = getIntent().getBooleanExtra("forViewAttendance", false);
                Intent intent;
                if (forViewAttendance) {
                    intent = new Intent(ClassSelectionActivity.this, ViewAttendanceActivity.class);
                    intent.putExtra("courseID", courseInstance.getCourseID());
                } else {
                    TeacherInstance teacher = TeacherDataStore.getCurrentTeacher();
                    if (teacher == null) return;
                    RetroFitService retroFitService = new RetroFitService();
                    TimeTableAPI timeTableAPI = retroFitService.getRetrofit().create(TimeTableAPI.class);
                    timeTableAPI.getCoursePeriods(teacher.getTeacherID(), courseInstance.getCourseID()).enqueue(new retrofit2.Callback<List<TimeTableEntry>>() {
                        @Override
                        public void onResponse(retrofit2.Call<List<TimeTableEntry>> call, retrofit2.Response<List<TimeTableEntry>> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                ArrayList<PeriodInstance> periodList = new ArrayList<>();
                                for (TimeTableEntry entry : response.body()) {
                                    List<LocalDate> last10Dates = getLastNDatesForDay(entry.getDayOfWeek(), 10);
                                    for (LocalDate date : last10Dates) {
                                        periodList.add(new PeriodInstance(date.toString(), entry.getStartTime(), entry.getEndTime()));
                                    }
                                }
                                Intent markIntent = new Intent(ClassSelectionActivity.this, MarkAttendanceActivity.class);
                                markIntent.putExtra("courseID", courseInstance.getCourseID());
                                markIntent.putParcelableArrayListExtra("periodList", periodList);
                                startActivity(markIntent);
                            } else {
                                Toast.makeText(ClassSelectionActivity.this, "No periods found for this course", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(retrofit2.Call<List<TimeTableEntry>> call, Throwable t) {
                            Toast.makeText(ClassSelectionActivity.this, "Failed to load periods", Toast.LENGTH_SHORT).show();
                        }
                    });
                    return;
                }
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
        Log.d("TEACHER_DEBUG", "API URL: /teacher/" + currentTeacher.getTeacherID() + "/teacherCourses");
        
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

    private List<LocalDate> getLastNDatesForDay(String dayOfWeekStr, int n) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate d = today;
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(dayOfWeekStr.toUpperCase());
        while (dates.size() < n) {
            if (d.getDayOfWeek() == dayOfWeek && !d.isAfter(today)) {
                dates.add(d);
            }
            d = d.minusDays(1);
        }
        return dates;
    }
} // <-- closes ClassSelectionActivity
