package com.test.orello.project;

import java.util.ArrayList;

import com.test.orello.checklist.MemberDTO;

public class ProjectDTO {

	private String seq;
	private String archive;				//자료실 사용 용량
	private String progress;			//진행률
	private String startdate;
	private String enddate;
	private String name;
	private String description;	
	private ArrayList<MemberDTO> mlist;	//멤버리스트
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getArchive() {
		return archive;
	}
	public void setArchive(String archive) {
		this.archive = archive;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public ArrayList<MemberDTO> getMlist() {
		return mlist;
	}
	public void setMlist(ArrayList<MemberDTO> mlist) {
		this.mlist = mlist;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
