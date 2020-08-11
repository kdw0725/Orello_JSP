package com.test.orello.checklist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.orello.DBUtil;

public class ChecklistDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private PreparedStatement pstat2;
	private ResultSet rs;
	private ResultSet rs2;
	
	//생성자
	public ChecklistDAO() {
		//DB연결
		DBUtil util = new DBUtil();
		conn = util.open();
	}
	
	//DB종료
	public void close() {
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//프로젝트 번호(?) 넘겨받아 체크리스트 목록 출력하기
	public ArrayList<ChecklistDTO> getList(String pseq) {

		try {
			
			String sql = "select * from tbl_checklist where delflag = 0 and project_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pseq);
			
			rs = pstat.executeQuery();
			
			
			//체크리스트를 담아줄 ArrayList 생성하기
			ArrayList<ChecklistDTO> list = new ArrayList<ChecklistDTO>();
			
			//while문 안에서 돌릴 쿼리 작성
			String sql2 = "select i.*, (select name from tbl_member where a.member_seq = seq) as name, (select count(*) from tbl_checklist_item_comment where delflag = 0 and i.seq = checklist_item_seq) as ccnt, (select count(*) from tbl_checklist_item_attach where delflag = 0 and i.seq = checklist_item_seq) as acnt from tbl_checklist_item i inner join tbl_project_attend a on i.project_attend_seq = a.seq where i.delflag = 0 and i.checklist_seq = ?";
			pstat2 = conn.prepareStatement(sql2);
			
			while (rs.next()) {
				
				//일단 체크리스트에 정보 넣어주기
				ChecklistDTO dto = new ChecklistDTO();
				dto.setSeq(rs.getString("seq"));
				dto.setPseq(rs.getString("project_seq"));
				dto.setTitle(rs.getString("title"));
				
				//체크리스트 항목 넣어줄 ArrayList 만들기
				ArrayList<ChecklistItemDTO> ilist = new ArrayList<ChecklistItemDTO>();
				
				//체크리스트별로 체크리스트 항목 가져오기 
				pstat2.setString(1, rs.getString("seq"));
				rs2 = pstat2.executeQuery();
				
				
				while (rs2.next()) {
					
					//체크리스트 항목 한줄마다 dto 생성하여 정보 넣어주고 ArrayList에 넣기 
					ChecklistItemDTO idto = new ChecklistItemDTO();
					
					idto.setSeq(rs2.getString("seq"));
					idto.setCseq(rs2.getString("checklist_seq"));
					idto.setTitle(rs2.getString("title"));
					idto.setContent(rs2.getString("content"));
					idto.setStartdate(rs2.getString("startdate"));
					idto.setEnddate(rs2.getString("enddate"));
					idto.setPaseq(rs2.getString("project_attend_seq"));
					idto.setCompleteflag(rs2.getString("completeflag"));
					idto.setName(rs2.getString("name"));
					idto.setCommentcount(rs2.getInt("ccnt"));
					idto.setAttachcount(rs2.getInt("acnt"));
					
					ilist.add(idto);
				}//rs2
				
				//체크리스트 항목들 체크리스트 DTO에 넣어주기
				dto.setList(ilist);
				
				//arraylist에 체크리스트 넣어주기
				list.add(dto);
				
			}//rs
			
			rs2.close();
			pstat2.close();
			
			rs.close();
			pstat.close();
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	
	
	
}
