package com.example.attendence_tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendence_tracker.Model.StudentInstance;

import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder> {
    private List<StudentInstance> studentList;

    public StudentListAdapter(List<StudentInstance> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        StudentInstance student = studentList.get(position);
        holder.tvStudentName.setText(student.getName());
        // Set initial (first letter, uppercase)
        String initial = student.getName() != null && !student.getName().isEmpty() ?
                student.getName().substring(0, 1).toUpperCase() : "?";
        holder.tvStudentInitial.setText(initial);
    }

    @Override
    public int getItemCount() {
        return studentList != null ? studentList.size() : 0;
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudentName, tvStudentInitial;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentName = itemView.findViewById(R.id.tvStudentName);
            tvStudentInitial = itemView.findViewById(R.id.tvStudentInitial);
        }
    }
}
