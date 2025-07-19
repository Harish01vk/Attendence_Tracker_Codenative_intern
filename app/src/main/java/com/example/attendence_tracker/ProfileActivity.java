package com.example.attendence_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.attendence_tracker.Model.TeacherInstance;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView tvProfileName = findViewById(R.id.tvProfileName);
        TextView tvProfileEmail = findViewById(R.id.tvProfileEmail);
        TextView tvProfileAvatar = findViewById(R.id.tvProfileAvatar);
        MaterialButton btnLogout = findViewById(R.id.btnLogoutProfile);

        TeacherInstance teacher = TeacherDataStore.getCurrentTeacher();
        if (teacher != null) {
            tvProfileName.setText(teacher.getTeacherName());
            tvProfileEmail.setText(teacher.getTeacherEmail());
            String initial = teacher.getTeacherName() != null && !teacher.getTeacherName().isEmpty() ?
                    teacher.getTeacherName().substring(0, 1).toUpperCase() : "?";
            tvProfileAvatar.setText(initial);
        }

        btnLogout.setOnClickListener(v -> {
            TeacherDataStore.clear();
            Intent intent = new Intent(ProfileActivity.this, TeacherLoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
} 