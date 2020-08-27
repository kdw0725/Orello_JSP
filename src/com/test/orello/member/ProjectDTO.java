package com.test.orello.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@ToString
public class ProjectDTO {
	private String seq;
	private String name;
	private String startdate;
	private String enddate;
	private String regdate;
	private String description;
	private String type;
	private String color_seq;
	private String delflag;
	private String firstpopular;
	private String secondpopular;
	
	private String color;
	
}
