package com.example.LessonManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.LessonManagement.model.Lesson;
import com.example.LessonManagement.repository.LessonRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LessonController {

	private final LessonRepository lessonRepository;

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

}
