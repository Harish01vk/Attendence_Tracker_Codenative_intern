package com.example.attendence_tracker.RetrofitService;

import com.example.attendence_tracker.Model.TimeTableEntry;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TimeTableAPI {
    @GET("/teacher/{teacherID}/timetable")
    Call<List<TimeTableEntry>> getTimeTable(@Path("teacherID") int teacherID);

    @GET("/teacher/{teacherID}/course/{courseID}/timetable")
    Call<List<TimeTableEntry>> getCoursePeriods(@Path("teacherID") int teacherID, @Path("courseID") int courseID);
} 