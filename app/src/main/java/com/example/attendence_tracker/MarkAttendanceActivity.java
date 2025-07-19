package com.example.attendence_tracker;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendence_tracker.Model.AttendanceInstance;
import com.example.attendence_tracker.Model.StudentInstance;
import com.example.attendence_tracker.RetrofitService.AttendanceAPI;
import com.example.attendence_tracker.RetrofitService.RetroFitService;
import com.example.attendence_tracker.RetrofitService.StudentAPI;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendence_tracker.Model.CourseInstance;
import com.example.attendence_tracker.Model.StudentInstance;
import com.example.attendence_tracker.Model.PeriodInstance;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MarkAttendanceActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AttendanceMarkAdapter adapter;
    private Button btnSubmit;

    private RecyclerView recyclerViewDates;
    private DateCardAdapter dateCardAdapter;
    private int courseID;
    private ArrayList<PeriodInstance> periodList;
    private PeriodInstance selectedPeriod;
    private List<StudentInstance> studentList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        recyclerViewDates = findViewById(R.id.recyclerViewDates);
        recyclerViewDates.setLayoutManager(new LinearLayoutManager(this));
        dateCardAdapter = new DateCardAdapter();
        recyclerViewDates.setAdapter(dateCardAdapter);

        recyclerView = findViewById(R.id.recyclerViewAttendance);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = null; // Will be set after period selection

        btnSubmit = findViewById(R.id.btnSubmitAttendance);
        btnSubmit.setVisibility(View.GONE);

        courseID = getIntent().getIntExtra("courseID", -1);
        periodList = getIntent().getParcelableArrayListExtra("periodList");

        if (courseID == -1 || periodList == null || periodList.isEmpty()) {
            Toast.makeText(this, "No periods available for this course", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        dateCardAdapter.setData(periodList, null, period -> {
            // When a period is selected, hide period cards and show student list for marking attendance
            selectedPeriod = period;
            recyclerViewDates.setVisibility(View.GONE);
            fetchAndShowStudentsForPeriod();
        });
    }

    private void fetchAndShowStudentsForPeriod() {
        RetroFitService retroFitService = new RetroFitService();
        StudentAPI studentAPI = retroFitService.getRetrofit().create(StudentAPI.class);
        studentAPI.GetStudents().enqueue(new Callback<List<StudentInstance>>() {
            @Override
            public void onResponse(Call<List<StudentInstance>> call, Response<List<StudentInstance>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    studentList = response.body();
                    adapter = new AttendanceMarkAdapter(studentList, courseID);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setVisibility(View.VISIBLE);
                    btnSubmit.setVisibility(View.VISIBLE);
                    setupSubmitButton();
                } else {
                    Toast.makeText(MarkAttendanceActivity.this, "Failed to load students", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<StudentInstance>> call, Throwable t) {
                Toast.makeText(MarkAttendanceActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupSubmitButton() {
        btnSubmit.setOnClickListener(v -> {
            List<AttendanceInstance> attendanceList = adapter.getattendanceList();
            // Set the selected period's date and time for each attendance instance
            for (AttendanceInstance instance : attendanceList) {
                instance.setAttendanceDate(selectedPeriod.getDate());
                // Optionally, you can add startTime/endTime to AttendanceInstance if needed
            }
            // Post attendance to backend
            RetroFitService retroFitService = new RetroFitService();
            AttendanceAPI attendanceAPI = retroFitService.getRetrofit().create(AttendanceAPI.class);
            for (AttendanceInstance instance : attendanceList) {
                attendanceAPI.PostAttendance(instance).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        // Optionally handle success
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Optionally handle failure
                    }
                });
            }
            Toast.makeText(this, "Attendance submitted!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

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
}