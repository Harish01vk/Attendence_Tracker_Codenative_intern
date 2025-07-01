package com.example.attendence_tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {
    private List<ReportModel> reportList = new ArrayList<>();

    public void setReportData(List<ReportModel> data) {
        this.reportList = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_report, parent, false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        ReportModel model = reportList.get(position);
        holder.name.setText(model.getStudentName());
        holder.present.setText("Present: " + model.getDaysPresent());
        holder.absent.setText("Absent: " + model.getDaysAbsent());
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    static class ReportViewHolder extends RecyclerView.ViewHolder {
        TextView name, present, absent;

        ReportViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textName);
            present = itemView.findViewById(R.id.textPresent);
            absent = itemView.findViewById(R.id.textAbsent);
        }
    }
}
