package com.example.attendence_tracker;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentListHolder extends RecyclerView.ViewHolder {


    TextView studentID,studentName;

    public StudentListHolder(@NonNull View itemView) {
        super(itemView);
        studentName = itemView.findViewById(R.id.tvStudentName);
        studentID = itemView.findViewById(R.id.tvStudentInitial);
    }


}