package com.example.LessonManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String addHoldLesson(@PathVariable Long id, Model model) {

        return "/lesson/hold-lesson-form";
    }

}
