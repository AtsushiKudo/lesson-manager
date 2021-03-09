package com.example.LessonManagement.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.LessonManagement.model.HoldLesson;
import com.example.LessonManagement.model.HoldLessonDate;
import com.example.LessonManagement.model.Lesson;
import com.example.LessonManagement.repository.HoldLessonDateRepository;
import com.example.LessonManagement.repository.HoldLessonRepository;
import com.example.LessonManagement.repository.LessonRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LessonController {

	private final LessonRepository lessonRepository;
	private final HoldLessonRepository holdLessonRepository;
	private final HoldLessonDateRepository holdLessonDateRepository;

	@GetMapping("/admin/lesson/add")
	public String addLesson(@ModelAttribute  Lesson lesson) {
		return "lesson/lesson-form";
	}

	@PostMapping("/admin/lesson/add")
	public String postAddLesson(@Validated @ModelAttribute Lesson lesson,
			BindingResult result) {

		if (result.hasErrors()) {
			return "lesson/lesson-form.html";
		}
		lessonRepository.save(lesson);
		return "redirect:/?lesson_register";
	}

	@GetMapping("/admin/lesson/index")
	public String showList(Model model) {
		model.addAttribute("lessons", lessonRepository.findAll());
		return "lesson/lesson-index";
	}

	@GetMapping("/admin/lesson/edit/{id}")
	public String editLesson(@PathVariable Long id, Model model) {
		model.addAttribute("lesson", lessonRepository.findById(id));
		return "/lesson/lesson-form";
	}

	@GetMapping("/admin/lesson/delete/{id}")
	public String deleteLesson(@PathVariable Long id) {
		lessonRepository.deleteById(id);
		return "redirect:/?lesson_delete";
	}

	@GetMapping("/admin/hold-lesson/add/{id}")
	public String addHoldLesson(@PathVariable Long id, @ModelAttribute  HoldLessonDate holdLessonDate, ModelMap modelMap) {

		try {
			modelMap.addAttribute("lesson", lessonRepository.findById(id).get());
			modelMap.addAttribute("holdTimes", lessonRepository.findById(id).get().getHoldLessons().size() + 1);

		}catch(Exception e){
			return "redirect:/admin/lesson/index";
		}

		return "lesson/hold-lesson-form";
	}

	@PostMapping("/admin/hold-lesson/add/{id}")
	public String postAddHoldLesson(@PathVariable Long id, @RequestParam("hold-lesson-dates") String[] holdDates, @RequestParam("hold-lesson-dates") String oneHoldDate,
			@RequestParam("hold-times") String holdTimes) {

		if(oneHoldDate.equals("")) {
			return "redirect:/admin/hold-lesson/add/"+id + "/?error";
		}
		List<HoldLessonDate> holdDateList = new ArrayList<>();

		Long holdTimesInt;

		try {
			  holdTimesInt = Long.parseLong(holdTimes);
			}catch(Exception e) {
				return "redirect:/admin/hold-lesson/add/"+id + "/?error";
			}

		Lesson lesson = lessonRepository.findById(id).get();
		try {

			for(String holdDate : holdDates) {
				System.out.println("aa"+holdDate);
				if(oneHoldDate.equals("")) {
					return "redirect:/admin/hold-lesson/add/"+id + "/?error";
				}
				LocalDate localHoldDate = LocalDate.parse(holdDate, DateTimeFormatter.BASIC_ISO_DATE);
				HoldLessonDate holdLessonDate = new HoldLessonDate(new HoldLesson(lesson, holdTimesInt), localHoldDate);
				holdDateList.add(holdLessonDate);
			}

		}catch(Exception e) {
			return "redirect:/admin/hold-lesson/add/"+id + "/?error";
		}
		HoldLesson holdLesson = new HoldLesson(lesson, holdTimesInt, holdDateList);
		holdLessonRepository.save(holdLesson);
		return "redirect:/?hold_lesson_register";
	}


}
