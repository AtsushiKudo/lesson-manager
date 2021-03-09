package com.example.LessonManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
		   uniqueConstraints = {@UniqueConstraint(columnNames = {"employee_code", "lesson_id", "hold_times"})}
		)
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Attendance(Employee employee, HoldLesson holdLesson) {
		super();
		this.employee = employee;
		this.holdLesson = holdLesson;
	}

	@ManyToOne
	@JoinColumn(name="employee_code")
    private Employee employee;

	@ManyToOne
	 @JoinColumns ({
			@JoinColumn(name="lesson_id", referencedColumnName="lesson_id"),
			@JoinColumn(name="hold_times", referencedColumnName="hold_times")
		})
	private HoldLesson holdLesson;
}
