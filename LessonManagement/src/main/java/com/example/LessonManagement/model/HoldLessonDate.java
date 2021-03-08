package com.example.LessonManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Column;

import java.time.*;

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


	public HoldLessonDate(Long lessonId, Long holdTimes){
		this.lessonId = lessonId;
		this.holdTimes = holdTimes;
	}

	@Column(name="lesson_id")
	Long lessonId;

	@Column(name="hold_times")
	Long holdTimes;

	@ManyToOne
	@JoinColumns ({
		@JoinColumn(name="lesson_id", referencedColumnName="lesson_id"),
		@JoinColumn(name="hold_times", referencedColumnName="hold_times")
	})
	private HoldLesson holdLesson;

	private LocalDate holdDate;




}