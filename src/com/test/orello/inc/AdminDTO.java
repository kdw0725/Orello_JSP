package com.test.orello.inc;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 
 * @author 강혜림
 * 로그인한 관리자 정보를 저장하고 보내주는 class
 *
 */
@Getter@Setter
@ToString
public class AdminDTO {
	private int seq;
	private String id;
	private String pw;
}
