package com.test.orello.notice;

public class NoticeDTO {

	private String nseq;		//공지사항 번호
	private String title;		//공지사항 제목
	private String content;		//공지사항 내용
	private String regdate;		//공지사항 작성일
	private String writer;
	
	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getNseq() {
		return nseq;
	}
	public void setNseq(String nseq) {
		this.nseq = nseq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
}
