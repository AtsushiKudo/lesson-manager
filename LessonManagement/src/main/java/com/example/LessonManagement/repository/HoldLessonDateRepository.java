package com.example.LessonManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LessonManagement.model.HoldLessonDate;

public interface HoldLessonDateRepository extends JpaRepository<HoldLessonDate, Long>{

}
