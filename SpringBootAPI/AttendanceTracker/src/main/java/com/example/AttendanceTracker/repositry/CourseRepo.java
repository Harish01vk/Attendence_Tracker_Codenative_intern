package com.example.AttendanceTracker.repositry;

import com.example.AttendanceTracker.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course,Integer> {

}
