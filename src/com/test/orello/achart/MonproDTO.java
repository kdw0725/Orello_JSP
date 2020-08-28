package com.test.orello.achart;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author 강혜림
 * 날짜별로 프로젝트 생성 차트를 표현하기 위한 날짜 데이터 가져오고 보내주는 class
 *
 */
@Setter@Getter
@ToString
public class MonproDTO {
	private int cnt;
	private String name;
	private String regdate;
}
