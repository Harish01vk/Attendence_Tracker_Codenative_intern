package com.example.attendence_tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {
    private List<ClassModel> classList;
    private OnItemClickListener onItemClickListener;

    public ClassAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setClassList(List<ClassModel> classList) {
        this.classList = classList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ClassModel classModel = classList.get(position);
        holder.textView.setText(classModel.getName());
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(classList.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ClassModel classModel);
    }
}