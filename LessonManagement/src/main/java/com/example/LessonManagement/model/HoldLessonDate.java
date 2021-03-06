package com.example.LessonManagement.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class HoldLessonDate {


	public static class HLDPK implements Serializable{

		private String holdDate;

	}

	@EmbeddedId
	private HLDPK hldpk;




}