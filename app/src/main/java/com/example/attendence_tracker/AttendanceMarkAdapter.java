package com.example.attendence_tracker;

import android.graphics.Color;
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

    private List<StudentInstance> studentList;
    private List<AttendanceInstance> attendanceList;
    public AttendanceMarkAdapter(List<StudentInstance> studentList, int courseID) {
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
            attendanceInstance.setCourseID(courseID);
            attendanceList.add(attendanceInstance);
        }

    }

    public List<AttendanceInstance> getattendanceList() {
        return attendanceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mark_attendance, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AttendanceInstance attendanceInstance = attendanceList.get(position);
        holder.studentName.setText(attendanceInstance.getStudentName());
        setStatusText(holder, attendanceInstance.isAttendanceStatus());
        holder.checkBox.setChecked(attendanceInstance.isAttendanceStatus());

        holder.itemView.setOnClickListener(v -> {
            boolean newStatus = !attendanceInstance.isAttendanceStatus();
            attendanceInstance.setAttendanceStatus(newStatus);
            holder.checkBox.setChecked(newStatus);
            setStatusText(holder, newStatus);
        });
        holder.checkBox.setOnClickListener(v -> {
            boolean newStatus = holder.checkBox.isChecked();
            attendanceInstance.setAttendanceStatus(newStatus);
            setStatusText(holder, newStatus);
        });
    }

    private void setStatusText(ViewHolder holder, boolean isPresent) {
        holder.statusText.setText(isPresent ? "Present" : "Absent");
        holder.statusText.setTextColor(isPresent ? Color.parseColor("#388E3C") : Color.parseColor("#F44336"));
    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView studentName, statusText;
        CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.tvStudentName);
            statusText = itemView.findViewById(R.id.tvAttendanceStatus);
            checkBox = itemView.findViewById(R.id.checkBoxPresent);
        }
    }
}
