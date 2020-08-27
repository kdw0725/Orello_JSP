package com.test.orello.checklist;

import java.util.ArrayList;

public class ChecklistDTO {

	private String seq;		//체크리스트 번호
	private String pseq;	//프로젝트 번호
	private String title;	//체크리스트 제목
	private String delflag;	//체크리스트 삭제 여부
	
	private ArrayList<ChecklistItemDTO> list;	//각 체크리스트에 딸린 체크리스트 항목을 담을 ArrayList

	
	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getPseq() {
		return pseq;
	}

	public void setPseq(String pseq) {
		this.pseq = pseq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public ArrayList<ChecklistItemDTO> getList() {
		return list;
	}

	public void setList(ArrayList<ChecklistItemDTO> list) {
		this.list = list;
	}
	
}
