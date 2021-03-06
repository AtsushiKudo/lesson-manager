package com.example.LessonManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LessonManagement.model.HoldLessonDate.HLDPK;
import com.example.LessonManagement.model.Lesson;

public interface HoldLessonRepository extends JpaRepository<Lesson, HLDPK>{

}
