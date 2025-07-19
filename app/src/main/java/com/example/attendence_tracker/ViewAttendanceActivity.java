package com.example.attendence_tracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendence_tracker.Model.PeriodInstance;
import com.example.attendence_tracker.Model.AttendanceInstance;
import com.example.attendence_tracker.Model.TimeTableEntry;
import com.example.attendence_tracker.Model.TeacherInstance;
import com.example.attendence_tracker.RetrofitService.RetroFitService;
import com.example.attendence_tracker.RetrofitService.TimeTableAPI;
import com.example.attendence_tracker.RetrofitService.AttendanceAPI;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAttendanceActivity extends AppCompatActivity {
    private RecyclerView recyclerViewPeriods;
    private DateCardAdapter dateCardAdapter;
    private RecyclerView attendanceRecyclerView;
    private ViewAttendanceAdapter viewAttendanceAdapter;
    private int courseID;
    private ArrayList<PeriodInstance> periodList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);

        recyclerViewPeriods = findViewById(R.id.recyclerViewDates);
        recyclerViewPeriods.setLayoutManager(new LinearLayoutManager(this));
        dateCardAdapter = new DateCardAdapter();
        recyclerViewPeriods.setAdapter(dateCardAdapter);

        attendanceRecyclerView = findViewById(R.id.attendanceRecyclerView);
        attendanceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewAttendanceAdapter = new ViewAttendanceAdapter();
        attendanceRecyclerView.setAdapter(viewAttendanceAdapter);
        attendanceRecyclerView.setVisibility(View.GONE);

        // Get courseID from intent or selection (for demo, hardcode or get from intent)
        courseID = getIntent().getIntExtra("courseID", -1);
        if (courseID == -1) {
            Toast.makeText(this, "No course selected", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        fetchPeriodsForCourse();
    }

    private void fetchPeriodsForCourse() {
        TeacherInstance teacher = TeacherDataStore.getCurrentTeacher();
        if (teacher == null) return;
        RetroFitService retroFitService = new RetroFitService();
        TimeTableAPI timeTableAPI = retroFitService.getRetrofit().create(TimeTableAPI.class);
        timeTableAPI.getCoursePeriods(teacher.getTeacherID(), courseID).enqueue(new Callback<List<TimeTableEntry>>() {
            @Override
            public void onResponse(Call<List<TimeTableEntry>> call, Response<List<TimeTableEntry>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    periodList = new ArrayList<>();
                    for (TimeTableEntry entry : response.body()) {
                        List<LocalDate> last10Dates = getLastNDatesForDay(entry.getDayOfWeek(), 10);
                        for (LocalDate date : last10Dates) {
                            periodList.add(new PeriodInstance(date.toString(), entry.getStartTime(), entry.getEndTime()));
                        }
                    }
                    dateCardAdapter.setData(periodList, null, period -> {
                        fetchAndShowAttendanceForPeriod(period);
                    });
                } else {
                    Toast.makeText(ViewAttendanceActivity.this, "No periods found for this course", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<TimeTableEntry>> call, Throwable t) {
                Toast.makeText(ViewAttendanceActivity.this, "Failed to load periods", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchAndShowAttendanceForPeriod(PeriodInstance period) {
        // Hide period cards, show attendance list
        recyclerViewPeriods.setVisibility(View.GONE);
        attendanceRecyclerView.setVisibility(View.VISIBLE);
        // Fetch attendance for the selected course and period
        RetroFitService retroFitService = new RetroFitService();
        AttendanceAPI attendanceAPI = retroFitService.getRetrofit().create(AttendanceAPI.class);
        attendanceAPI.RetrieveFromDate(period.getDate()).enqueue(new Callback<List<AttendanceInstance>>() {
            @Override
            public void onResponse(Call<List<AttendanceInstance>> call, Response<List<AttendanceInstance>> response) {
                List<AttendanceInstance> attendanceInstances = response.body();
                List<AttendanceRecord> attendanceRecordList = new ArrayList<>();
                if (attendanceInstances != null) {
                    for (AttendanceInstance attendanceInstance : attendanceInstances) {
                        if (attendanceInstance.getCourseID() == courseID) {
                            AttendanceRecord attendanceRecord = new AttendanceRecord(
                                    attendanceInstance.getStudentName(),
                                    attendanceInstance.isAttendanceStatus() ? "Present" : "Absent");
                            attendanceRecordList.add(attendanceRecord);
                        }
                    }
                }
                viewAttendanceAdapter.setData(attendanceRecordList);
            }
            @Override
            public void onFailure(Call<List<AttendanceInstance>> call, Throwable t) {
                Toast.makeText(ViewAttendanceActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
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
