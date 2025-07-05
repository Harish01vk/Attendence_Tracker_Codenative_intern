package com.example.attendence_tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendence_tracker.Model.AttendanceInstance;
import com.example.attendence_tracker.Model.StudentInstance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AttendanceMarkAdapter extends RecyclerView.Adapter<AttendanceMarkAdapter.ViewHolder> {

    private List<StudentInstance> studentList= StudentDataStore.getStudentList();
    private List<AttendanceInstance> attendanceList;
    public AttendanceMarkAdapter(List<StudentInstance> studentList ) {
        this.studentList = studentList;
        this.attendanceList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String today = sdf.format(new Date());

        for(StudentInstance studentInstance : studentList){
                AttendanceInstance attendanceInstance = new AttendanceInstance();
                attendanceInstance.setStudentID(studentInstance.getStudentId());
                attendanceInstance.setAttendanceStatus(false);
                attendanceInstance.setAttendanceDate(today);
                attendanceInstance.setStudentName(studentInstance.getName());
                attendanceList.add(attendanceInstance);
        }

    }


    public List<AttendanceInstance> getattendanceList() {
        return attendanceList;
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
        StudentInstance studentInstance = studentList.get(position);
        AttendanceInstance attendanceInstance = attendanceList.get(position);
        holder.name.setText(studentInstance.getName());
        holder.checkbox.setChecked(attendanceInstance.isAttendanceStatus());

        holder.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            attendanceInstance.setAttendanceStatus(isChecked);
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
