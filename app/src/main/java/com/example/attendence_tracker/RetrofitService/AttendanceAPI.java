package com.example.attendence_tracker.RetrofitService;

import com.example.attendence_tracker.Model.AttendanceInstance;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AttendanceAPI {

    @POST("Attendance/PostAttendance")
    Call<Void> PostAttendance(@Body AttendanceInstance attendanceInstance);

    @GET("Attendance/RetrieveFromDate/{date}")
    Call<List<AttendanceInstance>> RetrieveFromDate(@Path("date") String date);
}
