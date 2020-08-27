package com.test.orello.chat;

public class MessageDTO {
	
	private String seq;
	private String regdate;
	private String content;
	private String mseq;	//member_seq
	private String caseq;	//chat_attend_seq
	
	private String cseq;	//chat_seq
	private String name;	//발신인 이름
	private String myflag;	//내가 보낸내역인지 아닌지?
	
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMseq() {
		return mseq;
	}
	public void setMseq(String mseq) {
		this.mseq = mseq;
	}
	public String getCaseq() {
		return caseq;
	}
	public void setCaseq(String caseq) {
		this.caseq = caseq;
	}
	public String getMyflag() {
		return myflag;
	}
	public void setMyflag(String myflag) {
		this.myflag = myflag;
	}
	public String getCseq() {
		return cseq;
	}
	public void setCseq(String cseq) {
		this.cseq = cseq;
	}
	
	

}
