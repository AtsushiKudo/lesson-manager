package com.example.LessonManagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.LessonManagement.model.Attendance;
import com.example.LessonManagement.model.HoldLesson;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	@Query(value = "SELECT * FROM attendance a WHERE a.employee_code = ?1" ,
			nativeQuery = true)
	public Page<Attendance> findByEmployeeCode(String String, Pageable pageble);

}
