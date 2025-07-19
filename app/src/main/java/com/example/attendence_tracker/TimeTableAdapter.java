package com.example.attendence_tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendence_tracker.Model.TimeTableEntry;

import java.util.ArrayList;
import java.util.List;

public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.TimeTableViewHolder> {
    private List<TimeTableEntry> data = new ArrayList<>();

    public void setData(List<TimeTableEntry> newData) {
        data = newData != null ? newData : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TimeTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timetable_row, parent, false);
        return new TimeTableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeTableViewHolder holder, int position) {
        TimeTableEntry entry = data.get(position);
        holder.tvDayOfWeek.setText(entry.getDayOfWeek());
        holder.tvTime.setText(entry.getStartTime() + " - " + entry.getEndTime());
        holder.tvCourseName.setText(entry.getCourseName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class TimeTableViewHolder extends RecyclerView.ViewHolder {
        TextView tvDayOfWeek, tvTime, tvCourseName;
        public TimeTableViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDayOfWeek = itemView.findViewById(R.id.tvDayOfWeek);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvCourseName = itemView.findViewById(R.id.tvCourseName);
        }
    }
} 