package com.test.allsearch;

public class SearchDTO {

	private String bseq;		// 게시글 번호
	private String title;		// 게시글 제목
	private String content;		// 게시글 내용
	private String regdate;		// 게시글 등록일
	
	private String nseq;		// 공지사항 번호
	private String ntitle;		// 공지사항 제목
	private String ncontent;	// 공지사항 내용
	
	private String pseq;		// 프로젝트 공지사항 번호
	private String ptitle;		// 프로젝트 공지사항 제목
	private String pcontent;	// 프로젝트 공지사항 내용
	
	
	
	
	public String getPseq() {
		return pseq;
	}
	public void setPseq(String pseq) {
		this.pseq = pseq;
	}
	public String getPtitle() {
		return ptitle;
	}
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}
	public String getPcontent() {
		return pcontent;
	}
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}
	public String getNseq() {
		return nseq;
	}
	public void setNseq(String nseq) {
		this.nseq = nseq;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getBseq() {
		return bseq;
	}
	public void setBseq(String bseq) {
		this.bseq = bseq;
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
