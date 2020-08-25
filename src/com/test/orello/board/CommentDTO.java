package com.test.orello.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter@Setter
public class CommentDTO {
	
	private String mseq;
	private String name;
	private String content;
	private String regdate;
	private String email;
	private String bseq;
	private String psaq;

}