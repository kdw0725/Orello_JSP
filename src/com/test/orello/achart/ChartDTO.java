package com.test.orello.achart;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 
 * @author 강혜림
 * 차트를 표현하기 위한 데이터를 저장하고 보내주는 class
 *
 */
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
