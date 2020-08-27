package com.test.orello.chat;

import java.util.ArrayList;

import com.test.orello.checklist.MemberDTO;

public class ChatRoomDTO {

	private String seq;			//채팅방 번호
	private String pseq;		//프로젝트 번호
	private String name;		//프로젝트?? 채팅방 이름
	private ArrayList<MemberDTO> list;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<MemberDTO> getList() {
		return list;
	}
	public void setList(ArrayList<MemberDTO> list) {
		this.list = list;
	}
	
}
