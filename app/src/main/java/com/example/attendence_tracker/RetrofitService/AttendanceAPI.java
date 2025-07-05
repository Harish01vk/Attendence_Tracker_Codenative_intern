package com.example.attendence_tracker.RetrofitService;

import com.example.attendence_tracker.Model.AttendanceInstance;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AttendanceAPI {

    @POST("Attendance/PostAttendance")
    Call<Void> PostAttendance(@Body AttendanceInstance attendanceInstance);
}
