package com.example.attendence_tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.attendence_tracker.Model.CourseInstance;

import java.util.ArrayList;
import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {
    private List<CourseInstance> classList;
    private OnItemClickListener onItemClickListener;

    public ClassAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setClassList(List<CourseInstance> classList) {
        this.classList = classList != null ? classList : new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CourseInstance courseInstance = classList.get(position);
        holder.tvCourseName.setText(courseInstance.getCourseName());
        holder.tvCourseSemester.setText(courseInstance.getCourseSemester());
        // Set initial (first letter, uppercase)
        String initial = courseInstance.getCourseName() != null && !courseInstance.getCourseName().isEmpty() ?
                courseInstance.getCourseName().substring(0, 1).toUpperCase() : "?";
        holder.tvCourseInitial.setText(initial);
    }

    @Override
    public int getItemCount() {
        return classList != null ? classList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCourseName, tvCourseSemester, tvCourseInitial;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCourseName = itemView.findViewById(R.id.tvCourseName);
            tvCourseSemester = itemView.findViewById(R.id.tvCourseSemester);
            tvCourseInitial = itemView.findViewById(R.id.tvCourseInitial);
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
        void onItemClick(CourseInstance courseInstance);
    }
}