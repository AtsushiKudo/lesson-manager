package com.example.LessonManagement.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
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

	private LocalDate holdDate;

	public HoldLessonDate(HoldLesson holdLesson, LocalDate holdDate) {
		super();
		this.holdLesson = holdLesson;
		this.holdDate = holdDate;
	}


}