package com.example.LessonManagement.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.LessonManagement.validator.UniqueLogin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Employee {

    @Id
    @Size(min = 2, max = 20)
    @UniqueLogin
    private String employeeCode;

    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(min = 4, max = 255)
    private String password;

//
//    @NotBlank
//    @Email
//    private String email;

//    private int gender;
    private boolean admin;
    private String role;

    @OneToMany(mappedBy = "employee")
    private List<Attendance> attendances;

    public Employee(String employeeCode) {
    	this.employeeCode = employeeCode;
    }


}