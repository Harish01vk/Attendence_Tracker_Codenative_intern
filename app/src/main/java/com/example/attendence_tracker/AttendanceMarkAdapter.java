package com.example.attendence_tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AttendanceMarkAdapter extends RecyclerView.Adapter<AttendanceMarkAdapter.ViewHolder> {

    private List<Student> studentList;

    public AttendanceMarkAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    public ArrayList<Student> getMarkedStudents() {
        ArrayList<Student> presentList = new ArrayList<>();
        for (Student s : studentList) {
            if (s.isPresent()) {
                presentList.add(s);
            }
        }
        return presentList;
    }

    @NonNull
    @Override
    public AttendanceMarkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mark_attendance, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceMarkAdapter.ViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.name.setText(student.getName());
        holder.checkbox.setChecked(student.isPresent());

        holder.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            student.setPresent(isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        CheckBox checkbox;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textStudentName);
            checkbox = itemView.findViewById(R.id.checkboxPresent);
        }
    }
}
