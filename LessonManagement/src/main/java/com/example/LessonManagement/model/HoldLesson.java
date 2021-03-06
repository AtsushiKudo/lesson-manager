package com.example.LessonManagement.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class HoldLesson {

	@Getter
	@Setter
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PK implements Serializable{
	    private Long lessonId;
	    @Column(name="hold_times")
	    private Long holdTimes;
	}

	@EmbeddedId
	@Getter
	@Setter
	private PK pk;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @MapsId("lessonId")
    @Getter
	@Setter
    private Lesson lesson;


    public HoldLesson(@NonNull final Lesson lesson,
    		@NonNull final Long holdTimes) {
    	this.pk = new PK(lesson.getId(), holdTimes);
    	this.lesson = lesson;
    }

    public void setLesson(@NonNull final Lesson lesson) {
    	this.pk.setLessonId(lesson.getId());
    	this.lesson = lesson;
    }
}
