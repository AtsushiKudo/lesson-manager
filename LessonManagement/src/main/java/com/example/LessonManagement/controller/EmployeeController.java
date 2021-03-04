package com.example.LessonManagement.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.LessonManagement.model.Employee;
import com.example.LessonManagement.repository.EmployeeRepository;

public class EmployeeController {

	private EmployeeRepository repository;

	@PostMapping("/process")
    public String process(@Validated @ModelAttribute Employee employee,
            BindingResult result) {

        if (result.hasErrors()) {
            return "form";
        }
        repository.save(employee);
        return "redirect:/";
    }
}
