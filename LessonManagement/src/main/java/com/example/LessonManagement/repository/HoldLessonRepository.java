package com.example.LessonManagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.LessonManagement.model.HoldLesson;
import com.example.LessonManagement.model.HoldLesson.PK;

public interface HoldLessonRepository extends JpaRepository<HoldLesson, PK>{
	@Query(value = "SELECT * FROM hold_lesson hl WHERE (hl.hold_times, hl.lesson_id) IN "
			+ "(SELECT hold_times,lesson_id FROM hold_lesson_date hld GROUP BY (hld.hold_times, hld.lesson_id) HAVING MIN(hold_date) > ?1)" ,
			nativeQuery = true)
	List<HoldLesson> findHoldLessonsAfterToday(LocalDate today);
}
