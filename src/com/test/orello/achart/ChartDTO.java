package com.test.orello.achart;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@ToString
public class ChartDTO {
	private int seq;
	private String regdate;
	private String name;
	private String email;
	private int amount;
	private int totalPoint;
	private int LoginMemberCnt;
	private int ChatingCnt;
}
