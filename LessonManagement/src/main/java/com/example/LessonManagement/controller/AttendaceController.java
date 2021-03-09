package com.example.LessonManagement.controller;

import java.time.LocalDate;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.LessonManagement.model.HoldLesson.PK;
import com.example.LessonManagement.repository.HoldLessonRepository;

public class AttendaceController {

	HoldLessonRepository holdLessonRepository;

	@GetMapping("/attendance/hold-lesson-index")
	public String holdLessonIndexAfterToday(Model model) {
		model.addAttribute("holdLessons", holdLessonRepository.findHoldLessonsAfterToday(LocalDate.now()));
		return "attendance/hold-lesson-index";
	}


	@GetMapping("/attendance/hold-lesson/{lesson-id}/{hold-times}")
	public String showHoldLessonDetail(@PathVariable("lesson-id") Long lessonId, @PathVariable("hold-times") Long holdTimes, Model model) {
		PK holdLessonId = new PK(lessonId, holdTimes);
		model.addAttribute("holdLesson", holdLessonRepository.findById(holdLessonId));
		return "attendance/hold-lesson-detail";
	}
}
