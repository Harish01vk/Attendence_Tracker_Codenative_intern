package com.example.attendence_tracker;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendence_tracker.Model.AttendanceInstance;
import com.example.attendence_tracker.RetrofitService.AttendanceAPI;
import com.example.attendence_tracker.RetrofitService.RetroFitService;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAttendanceActivity extends AppCompatActivity {

    private TextInputEditText editTextDate;
    private RecyclerView attendanceRecyclerView;
    private ViewAttendanceAdapter viewAttendanceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);

        // Initialize views
        editTextDate = findViewById(R.id.editTextDate);
        attendanceRecyclerView = findViewById(R.id.attendanceRecyclerView);

        // Set up RecyclerView
        attendanceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewAttendanceAdapter = new ViewAttendanceAdapter();
        attendanceRecyclerView.setAdapter(viewAttendanceAdapter);

        // On click show date picker
        editTextDate.setOnClickListener(view -> showDatePickerDialog());
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                ViewAttendanceActivity.this,
                (DatePicker view, int year, int month, int dayOfMonth) -> {
                    calendar.set(year, month, dayOfMonth);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                    String selectedDate = sdf.format(calendar.getTime());

                    editTextDate.setText(selectedDate);
                    fetchAttendanceData(selectedDate);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }


    private void fetchAttendanceData(String date) {
        // TODO: Replace this dummy list with a real API call (Retrofit)
        RetroFitService retroFitService = new RetroFitService();

        AttendanceAPI attendanceAPI = retroFitService.getRetrofit().create(AttendanceAPI.class);
        attendanceAPI.RetrieveFromDate(date).enqueue(new Callback<List<AttendanceInstance>>() {

                                                         @Override
                                                         public void onResponse(Call<List<AttendanceInstance>> call, Response<List<AttendanceInstance>> response) {
List<AttendanceInstance> attendanceInstances = response.body();
List<AttendanceRecord> attendanceRecordList = new ArrayList<>();
for (AttendanceInstance attendanceInstance : attendanceInstances) {
    AttendanceRecord attendanceRecord = new AttendanceRecord(attendanceInstance.getStudentName(), attendanceInstance.isAttendanceStatus() ? "Present" : "Absent");
    attendanceRecordList.add(attendanceRecord);}

                                                             viewAttendanceAdapter.setData(attendanceRecordList);

                                                         }

                                                         @Override
                                                         public void onFailure(Call<List<AttendanceInstance>> call, Throwable t) {
Toast.makeText(ViewAttendanceActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                                                         }
                                                     });

        Toast.makeText(this, "Fetching attendance for " + date, Toast.LENGTH_SHORT).show();


    }
}
