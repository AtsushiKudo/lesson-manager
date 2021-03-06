package com.example.LessonManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LessonManagement.model.Lesson;

public interface HoldLessonDateRepository extends JpaRepository<Lesson, Long>{

}
