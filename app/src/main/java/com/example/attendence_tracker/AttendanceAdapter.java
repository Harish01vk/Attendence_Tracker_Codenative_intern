package com.example.attendence_tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {

    private List<AttendanceRecord> attendanceList = new ArrayList<>();

    public void setData(List<AttendanceRecord> newList) {
        this.attendanceList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_attendance, parent, false);
        return new AttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewHolder holder, int position) {
        AttendanceRecord record = attendanceList.get(position);
        holder.textStudentName.setText(record.getStudentName());
        holder.textStatus.setText(record.getStatus());

        // Optional color indication
        if ("Present".equalsIgnoreCase(record.getStatus())) {
            holder.textStatus.setTextColor(0xFF2E7D32); // Green
        } else {
            holder.textStatus.setTextColor(0xFFD32F2F); // Red
        }
    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

    static class AttendanceViewHolder extends RecyclerView.ViewHolder {
        TextView textStudentName, textStatus;

        public AttendanceViewHolder(@NonNull View itemView) {
            super(itemView);
            textStudentName = itemView.findViewById(R.id.textStudentName);
            textStatus = itemView.findViewById(R.id.textStatus);
        }
    }
}
