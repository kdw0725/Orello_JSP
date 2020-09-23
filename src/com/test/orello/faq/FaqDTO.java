package com.test.orello.faq;

/**
 * FAQ를 이용하기위한 데이터 객체들을 모아놓은 DTO이다.
 * @author 강경원
 *
 */
public class FaqDTO {

	private String content;		// FAQ 내용
	private String question;	// FAQ 제목
	private String fseq;		// FAQ 번호	
	private int cnt;		//faq 갯수
	
	
	public String getFseq() {
		return fseq;
	}
	public void setFseq(String fseq) {
		this.fseq = fseq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
}
