package com.example.attendence_tracker.RetrofitService;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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

        // Add HTTP logging interceptor
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("HTTP_DEBUG", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson)) // handles JSON with correct date format
                .addConverterFactory(ScalarsConverterFactory.create()) // handles plain text like "Student added"
                .build();

    }
    public Retrofit getRetrofit(){
        return retrofit;
    }
}
