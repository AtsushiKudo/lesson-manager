package com.example.LessonManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LessonManagement.model.Lesson;



public interface LessonRepository extends JpaRepository<Lesson, Long> {

}
