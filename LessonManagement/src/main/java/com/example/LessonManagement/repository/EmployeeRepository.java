package com.example.LessonManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.LessonManagement.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

	Employee findByEmployeeCode(String employeeCode);
    boolean existsByEmployeeCode(String employeeCode);
}
