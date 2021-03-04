package com.example.LessonManagement.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.LessonManagement.model.Employee;
import com.example.LessonManagement.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String employeeCode)
            throws UsernameNotFoundException {

        Employee employee = employeeRepository.findByEmployeeCode(employeeCode);
        if (employee == null) {
            throw new UsernameNotFoundException(employeeCode + " not found");
        }
        return createUserDetails(employee);
    }

    public User createUserDetails(Employee employee) {
        Set<GrantedAuthority> grantedAuthories = new HashSet<>();
        grantedAuthories.add(new SimpleGrantedAuthority("ROLE_" + employee.getRole()));

        return new User(employee.getEmployeeCode(), employee.getPassword(), grantedAuthories);
    }
}
