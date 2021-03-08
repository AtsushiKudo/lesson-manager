package com.example.LessonManagement.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
@NoArgsConstructor
public class HoldLesson {

	@Getter
	@Setter
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PK implements Serializable{

		@NonNull
		private Long lessonId;
		@NonNull
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

    @OneToMany(mappedBy = "holdLesson", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<HoldLessonDate> holdLessonDates;

    public HoldLesson(@NonNull final Lesson lesson,
    		@NonNull final Long holdTimes) {
    	this.pk = new PK(lesson.getId(), holdTimes);
    	this.lesson = lesson;
    }

    public HoldLesson(@NonNull final Lesson lesson,
    		@NonNull final Long holdTimes, @NonNull List<HoldLessonDate> holdLessonDates) {
    	this.pk = new PK(lesson.getId(), holdTimes);
    	this.lesson = lesson;
    	this.holdLessonDates = holdLessonDates;
    }

    public void setLesson(@NonNull final Lesson lesson) {
    	this.pk.setLessonId(lesson.getId());
    	this.lesson = lesson;
    }
}
