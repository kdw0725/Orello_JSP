package com.test.orello.checklist;

/**
 * 체크리스트 항목 댓글 객체 클래스입니다.
 * @author Doyun Lee
 *
 */
public class ChecklistCommentDTO {
	
	private String seq;
	private String ciseq; 
	private String paseq;
	private String regdate;
	private String writer;
	private String content;
	private String profilepic;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCiseq() {
		return ciseq;
	}
	public void setCiseq(String ciseq) {
		this.ciseq = ciseq;
	}
	public String getPaseq() {
		return paseq;
	}
	public void setPaseq(String paseq) {
		this.paseq = paseq;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getProfilepic() {
		return profilepic;
	}
	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}
		

}
