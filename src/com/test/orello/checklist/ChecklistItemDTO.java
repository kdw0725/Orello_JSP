package com.test.orello.checklist;

import java.util.ArrayList;

public class ChecklistItemDTO {
	
	private String seq;		//체크리스트 항목 번호
	private String cseq;	//체크리스트 번호
	private String title;	//체크리스트 항목 제목
	private String content;	//체크리스트 항목 내용
	private String startdate;	//체크리스트 항목 시작일
	private String enddate;		//체크리스트 항목 종료일
	private String regdate;		//체크리스트 항목 등록일
	private String completeflag;//체크리스트 항목 완료 여부
	private String delflag;		//체크리스트 항목 삭제 여부
	private String paseq;		//체크리스트 항목 할당 멤버 (체크리스트 참여 번호)
	
	private String name;		//체크리스트 할당 멤버 이름
	private int commentcount;	//체크리스트 댓글 수
	private int attachcount;	//체크리스트 첨부파일 수
	private ArrayList<String> filename;		//체크리스트 첨부파일 이름 배열
	private ArrayList<String> orgfilename;	//체크리스트 첨부파일 원래이름 배열
	private String mseq;
	
	
	

	public ArrayList<String> getFilename() {
		return filename;
	}
	public void setFilename(ArrayList<String> filename) {
		this.filename = filename;
	}
	public ArrayList<String> getOrgfilename() {
		return orgfilename;
	}
	public void setOrgfilename(ArrayList<String> orgfilename) {
		this.orgfilename = orgfilename;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCseq() {
		return cseq;
	}
	public void setCseq(String cseq) {
		this.cseq = cseq;
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
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getPaseq() {
		return paseq;
	}
	public void setPaseq(String paseq) {
		this.paseq = paseq;
	}
	public String getCompleteflag() {
		return completeflag;
	}
	public void setCompleteflag(String completeflag) {
		this.completeflag = completeflag;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCommentcount() {
		return commentcount;
	}
	public void setCommentcount(int commentcount) {
		this.commentcount = commentcount;
	}
	public int getAttachcount() {
		return attachcount;
	}
	public void setAttachcount(int attachcount) {
		this.attachcount = attachcount;
	}
	public String getMseq() {
		return mseq;
	}
	public void setMseq(String mseq) {
		this.mseq = mseq;
	}	
	
}
