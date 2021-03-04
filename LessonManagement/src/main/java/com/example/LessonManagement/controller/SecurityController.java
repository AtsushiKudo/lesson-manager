package com.example.LessonManagement.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.LessonManagement.model.Employee;
import com.example.LessonManagement.repository.EmployeeRepository;
import com.example.LessonManagement.util.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SecurityController {

    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String showList(Authentication loginEmployee, Model model) {
//        model.addAttribute("employeeName", loginEmployee.getName());
//        model.addAttribute("role", loginEmployee.getAuthorities());
        return "top";
    }

    @GetMapping("/admin/list")
    public String showAdminList(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "list";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("employee") Employee employee) {
        return "register";
    }

    @PostMapping("/register")
    public String process(@Validated @ModelAttribute("employee") Employee employee,
            BindingResult result) {

        if (result.hasErrors()) {
            return "register";
        }

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        if (employee.isAdmin()) {
            employee.setRole(Role.ADMIN.name());
        } else {
            employee.setRole(Role.USER.name());
        }
        employeeRepository.save(employee);

        return "redirect:/login?register";
    }
}
