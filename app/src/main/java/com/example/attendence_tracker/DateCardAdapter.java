package com.example.attendence_tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendence_tracker.Model.PeriodInstance;

import java.util.ArrayList;
import java.util.List;

public class DateCardAdapter extends RecyclerView.Adapter<DateCardAdapter.DateViewHolder> {
    private List<PeriodInstance> periods = new ArrayList<>();
    private OnDateClickListener listener;

    public interface OnDateClickListener {
        void onDateClick(PeriodInstance period);
    }

    public void setData(List<PeriodInstance> periods, String unused, OnDateClickListener listener) {
        this.periods = periods != null ? periods : new ArrayList<>();
        this.listener = listener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date_card, parent, false);
        return new DateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DateViewHolder holder, int position) {
        PeriodInstance period = periods.get(position);
        String label = period.getDate() + "  " + period.getStartTime() + "-" + period.getEndTime();
        holder.tvDate.setText(label);
        holder.cardView.setOnClickListener(v -> {
            if (listener != null) listener.onDateClick(period);
        });
    }

    @Override
    public int getItemCount() {
        return periods.size();
    }

    static class DateViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate;
        CardView cardView;
        public DateViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDateCard);
            cardView = (CardView) itemView;
        }
    }
} 