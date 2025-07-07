package com.example.attendence_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ClassSelectionActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ClassAdapter adapter;
    private List<ClassModel> classList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_selection);

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new ClassAdapter(new ClassAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ClassModel classModel) {
                Intent intent = new Intent(ClassSelectionActivity.this, MarkAttendanceActivity.class);
                intent.putExtra("class_id", classModel.getId());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        classList = new ArrayList<>();
        classList.add(new ClassModel("Class 1"));
        classList.add(new ClassModel("Class 2"));
        classList.add(new ClassModel("Class 3"));

        adapter.setClassList(classList);
    }
}