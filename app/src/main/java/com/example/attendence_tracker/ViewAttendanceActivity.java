package com.example.attendence_tracker;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ViewAttendanceActivity extends AppCompatActivity {

    private TextInputEditText editTextDate;
    private RecyclerView attendanceRecyclerView;
    private AttendanceAdapter attendanceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);

        // Initialize views
        editTextDate = findViewById(R.id.editTextDate);
        attendanceRecyclerView = findViewById(R.id.attendanceRecyclerView);

        // Set up RecyclerView
        attendanceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        attendanceAdapter = new AttendanceAdapter();
        attendanceRecyclerView.setAdapter(attendanceAdapter);

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
        Toast.makeText(this, "Fetching attendance for " + date, Toast.LENGTH_SHORT).show();

        // Dummy data for now
        List<AttendanceRecord> dummyList = new ArrayList<>();
        dummyList.add(new AttendanceRecord("Harish M", "Present"));
        dummyList.add(new AttendanceRecord("Divya R", "Absent"));
        dummyList.add(new AttendanceRecord("Rahul K", "Present"));

        attendanceAdapter.setData(dummyList);
    }
}
