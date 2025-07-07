package com.example.attendence_tracker.RetrofitService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetroFitService {
    private Retrofit retrofit;

    public RetroFitService(){
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd") // 🔥 this trims time and timezone
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080")
                .addConverterFactory(GsonConverterFactory.create(gson)) // handles JSON with correct date format
                .addConverterFactory(ScalarsConverterFactory.create()) // handles plain text like "Student added"
                .build();

    }
    public Retrofit getRetrofit(){
        return retrofit;
    }
}
