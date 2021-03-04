package com.example.LessonManagement.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.LessonManagement.model.Lesson;
import com.example.LessonManagement.repository.LessonRepository;

public class LessonController {

	private LessonRepository lessonRepository;

	@GetMapping("/lesson-add")
    public String addEmployee(@ModelAttribute  Lesson lesson) {
        return "lesson-form";
    }

    @PostMapping("/lesson/add")
    public String process(@Validated @ModelAttribute Lesson lesson,
            BindingResult result) {

        if (result.hasErrors()) {
            return "lesson/lesson-form.html";
        }
        lessonRepository.save(lesson);
        return "redirect:/top?lesson-register";
    }

}
