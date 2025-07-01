package com.example.attendence_tracker;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    private TextView reportTitle;
    private RecyclerView reportRecyclerView;
    private ReportAdapter reportAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        // Initialize UI components
        reportTitle = findViewById(R.id.reportTitle);
        reportRecyclerView = findViewById(R.id.reportRecyclerView);

        // Setup RecyclerView
        reportRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reportAdapter = new ReportAdapter();
        reportRecyclerView.setAdapter(reportAdapter);

        // Temporary dummy data
        ArrayList<ReportModel> dummyReports = new ArrayList<>();
        dummyReports.add(new ReportModel("Harish M", 22, 2));
        dummyReports.add(new ReportModel("Divya R", 20, 4));
        dummyReports.add(new ReportModel("Rahul K", 23, 1));

        reportAdapter.setReportData(dummyReports);
    }
}
