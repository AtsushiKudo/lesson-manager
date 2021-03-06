package com.example.LessonManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
public class HoldLessonDate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumns ({
		@JoinColumn(name="lesson_id", referencedColumnName="lesson_id"),
		@JoinColumn(name="hold_times", referencedColumnName="hold_times")
	})
	private HoldLesson holdLesson;

	private Long holdDate;




}