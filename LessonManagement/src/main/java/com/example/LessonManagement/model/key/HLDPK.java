package com.example.LessonManagement.model.key;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public  class HLDPK implements Serializable{

	private String holdDate;

	 @ManyToOne
	    @JoinColumns({
	        @JoinColumn(name="lesson_id", referencedColumnName="lessonId"),
	        @JoinColumn(name="hold_times", referencedColumnName="holdTimes")
	    })
}
