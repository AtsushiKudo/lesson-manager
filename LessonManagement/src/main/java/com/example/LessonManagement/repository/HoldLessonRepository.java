package com.example.LessonManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LessonManagement.model.HoldLesson;
import com.example.LessonManagement.model.HoldLesson.PK;
import com.example.LessonManagement.model.Lesson;

public interface HoldLessonRepository extends JpaRepository<HoldLesson, PK>{

}
