package com.example.attendence_tracker.RetrofitService;

import com.example.attendence_tracker.Model.StudentInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface StudentAPI {

    @GET("/Students/GetStudents")
    Call<List<StudentInstance>> GetStudents();

    @POST("/Students/AddStudent")
    Call<Void> addstudent(@Body StudentInstance student);

}

