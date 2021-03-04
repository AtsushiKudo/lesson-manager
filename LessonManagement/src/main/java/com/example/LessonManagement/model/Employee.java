package com.example.LessonManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import validator.UniqueLogin;

@Getter
@Setter
@Entity
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
    private int deleteFlag = 0;

}