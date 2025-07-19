package com.example.attendence_tracker;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendence_tracker.Model.TeacherInstance;
import com.example.attendence_tracker.Model.TimeTableEntry;
import com.example.attendence_tracker.RetrofitService.RetroFitService;
import com.example.attendence_tracker.RetrofitService.TimeTableAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeTableActivity extends AppCompatActivity {
    private RecyclerView recyclerViewTimeTable;
    private TimeTableAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        recyclerViewTimeTable = findViewById(R.id.recyclerViewTimeTable);
        recyclerViewTimeTable.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TimeTableAdapter();
        recyclerViewTimeTable.setAdapter(adapter);

        fetchTimeTable();
    }

    private void fetchTimeTable() {
        TeacherInstance teacher = TeacherDataStore.getCurrentTeacher();
        if (teacher == null) {
            Toast.makeText(this, "No teacher logged in", Toast.LENGTH_SHORT).show();
            return;
        }
        RetroFitService retroFitService = new RetroFitService();
        TimeTableAPI api = retroFitService.getRetrofit().create(TimeTableAPI.class);
        api.getTimeTable(teacher.getTeacherID()).enqueue(new Callback<List<TimeTableEntry>>() {
            @Override
            public void onResponse(Call<List<TimeTableEntry>> call, Response<List<TimeTableEntry>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setData(response.body());
                } else {
                    Toast.makeText(TimeTableActivity.this, "Failed to load timetable", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<TimeTableEntry>> call, Throwable t) {
                Toast.makeText(TimeTableActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
