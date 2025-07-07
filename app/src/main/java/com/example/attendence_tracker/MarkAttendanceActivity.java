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
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarkAttendanceActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AttendanceMarkAdapter adapter;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);
        int courseID = getIntent().getIntExtra("courseID", -1);

        recyclerView = findViewById(R.id.recyclerViewAttendance);
        btnSubmit = findViewById(R.id.btnSubmitAttendance);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // ✅ Get student list from global store
        List<StudentInstance> studentList = StudentDataStore.getStudentList();
        RetroFitService retroFitService = new RetroFitService();
        AttendanceAPI attendanceAPI = retroFitService.getRetrofit().create(AttendanceAPI.class);
        // ✅ Initialize adapter with just student list (it internally builds attendance list)
        adapter = new AttendanceMarkAdapter(studentList,courseID);
        recyclerView.setAdapter(adapter);

        btnSubmit.setOnClickListener(view -> {
            List<AttendanceInstance> attendanceList = adapter.getattendanceList();

            long presentCount = attendanceList.stream()
                    .filter(AttendanceInstance::isAttendanceStatus)
                    .count();

            Toast.makeText(this, "Present: " + presentCount + " / " + attendanceList.size(), Toast.LENGTH_SHORT).show();
            for (AttendanceInstance attendanceInstance : attendanceList) {
                attendanceAPI.PostAttendance(attendanceInstance).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Log.d("POST_SUCCESS", "Attendance marked for: " + attendanceInstance.getStudentID());
                        } else {
                            Log.e("POST_FAIL", "Response Code: " + response.code() + " | Error Body: " + response.errorBody());
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(MarkAttendanceActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("MarkAttendanceActivity", "Error marking attendance", t);
                    }
                });

            }

        });
    }
}