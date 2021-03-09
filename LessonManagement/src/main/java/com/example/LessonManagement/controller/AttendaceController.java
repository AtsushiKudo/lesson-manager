package com.example.LessonManagement.controller;

import java.time.LocalDate;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.LessonManagement.model.Attendance;
import com.example.LessonManagement.model.Employee;
import com.example.LessonManagement.model.HoldLesson;
import com.example.LessonManagement.model.HoldLesson.PK;
import com.example.LessonManagement.repository.AttendanceRepository;
import com.example.LessonManagement.repository.EmployeeRepository;
import com.example.LessonManagement.repository.HoldLessonRepository;
import com.example.LessonManagement.repository.LessonRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AttendaceController {

	private final HoldLessonRepository holdLessonRepository;
	private final AttendanceRepository attendanceRepository;
	private final LessonRepository lessonRepository;
	private final EmployeeRepository employeeRepository;

	@GetMapping("/attendance/hold-lesson-index")
	public String holdLessonIndexAfterToday(Model model) {
		model.addAttribute("holdLessons", holdLessonRepository.findHoldLessonsAfterToday(LocalDate.now()));
		return "attendance/hold-lesson-index";
	}


	@GetMapping("/attendance/hold-lesson/{lesson-id}/{hold-times}")
	public String showHoldLessonDetail(@PathVariable("lesson-id") Long lessonId, @PathVariable("hold-times") Long holdTimes, Model model) {
		PK holdLessonId = new PK(lessonId, holdTimes);
		model.addAttribute("holdLesson", holdLessonRepository.findById(holdLessonId).get());
		return "attendance/hold-lesson-detail";
	}

	@PostMapping("/attendance/hold-lesson/{lesson-id}/{hold-times}")
	public String attendHoldLesson(Authentication loginUser, @PathVariable("lesson-id") Long lessonId, @PathVariable("hold-times") Long holdTimes, Model model) {

		Attendance attendance = new Attendance(new Employee(loginUser.getName()), new HoldLesson(lessonRepository.findById(lessonId).get(), holdTimes));

		try {
			attendanceRepository.save(attendance);
		}catch(Exception e) {

			return "redirect:/attendance/hold-lesson/" + lessonId + "/" + holdTimes + "?error";
		}

		return "redirect:/?lesson_register";
	}

	@GetMapping("/attendance/history")
	public String showHistory(Authentication loginUser, Model model) {
		model.addAttribute("attendances", employeeRepository.findByEmployeeCode(loginUser.getName()).getAttendances());
		return "attendance/attendance-history";
	}

}
