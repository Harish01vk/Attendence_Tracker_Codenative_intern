package com.example.attendence_tracker.RetrofitService;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetroFitService {
    private Retrofit retrofit;

    public RetroFitService(){
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080")
                .addConverterFactory(ScalarsConverterFactory.create()) // handles plain text like "Student added"
                .addConverterFactory(GsonConverterFactory.create(new Gson())) // handles JSON
                .build();
    }
    public Retrofit getRetrofit(){
        return retrofit;
    }
}
