package com.test.orello.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter@Setter
public class BoardDTO {
	private String seq;
	private String title;
	private String content;
	private String regdate;
	private String readcount;
	private String name;
	private String Email;
}
