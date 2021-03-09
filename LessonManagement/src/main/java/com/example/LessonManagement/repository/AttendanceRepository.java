package com.example.LessonManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LessonManagement.model.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

}
