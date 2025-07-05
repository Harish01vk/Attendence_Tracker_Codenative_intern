package com.example.attendence_tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendence_tracker.Model.StudentInstance;

import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListHolder> {

    private List<StudentInstance> studentList;

    public StudentListAdapter(List<StudentInstance> studentList) {
        this.studentList = studentList;
    }


    @NonNull
    @Override
    public StudentListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
  return new StudentListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentListHolder holder, int position) {
        StudentInstance studentInstance = studentList.get(position);
       holder.studentName.setText(studentInstance.getName());
       holder.studentID.setText(String.valueOf(studentInstance.getStudentId()));


    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }
}
