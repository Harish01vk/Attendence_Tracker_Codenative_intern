package com.example.attendence_tracker.RetrofitService;

import com.example.attendence_tracker.Model.CourseInstance;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CourseAPI {
    @GET("Course/GetCourse")
    Call<List<CourseInstance>> getCourse();
} 