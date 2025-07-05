package com.example.attendence_tracker;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.attendence_tracker.Model.StudentInstance;
import com.example.attendence_tracker.RetrofitService.RetroFitService;
import com.example.attendence_tracker.RetrofitService.StudentAPI;
import com.example.attendence_tracker.StudentDataStore;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class STUDENTLOADER extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        preloadStudents();
    }

    private void preloadStudents() {
        RetroFitService retroFitService = new RetroFitService();
        StudentAPI studentAPI = retroFitService.getRetrofit().create(StudentAPI.class);

        studentAPI.GetStudents().enqueue(new Callback<List<StudentInstance>>() {
            @Override
            public void onResponse(Call<List<StudentInstance>> call, Response<List<StudentInstance>> response) {
                if (response.body() != null) {
                    StudentDataStore.setStudentList(response.body());
                    Log.d("MyApp", "Students preloaded: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<StudentInstance>> call, Throwable t) {
                Log.e("STUDENTLOADER", "Failed to preload students", t);
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}
