package com.test.orello.checklist;

/**
 * 체크리스트 항목 첨부파일을 위한 클래스입니다.
 * @author Doyun Lee
 *
 */
public class ChecklistAttachDTO {

	private String seq;
	private String ciseq;
	private String filename;
	private String orgFilename;
	private String regdate;
	private String delflag;
	
	
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getOrgFilename() {
		return orgFilename;
	}
	public void setOrgFilename(String orgFilename) {
		this.orgFilename = orgFilename;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	
}
