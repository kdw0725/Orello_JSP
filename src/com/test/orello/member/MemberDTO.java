package com.test.orello.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@ToString
public class MemberDTO {
	
	private String seq; 
	private String name;
	private String email;
	private String pw;
	private String regdate;
	private String company;
	private String tel;
	private String point;
	private String statusmsg;
	private String profile_seq;
	private String delflag;
	
	private String ori_file;
	private String file;
	
}
