package com.test.orello.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.test.orello.DBUtil;
import com.test.orello.checklist.ChecklistDTO;
import com.test.orello.checklist.ChecklistItemDTO;
import com.test.orello.checklist.MemberDTO;

/**
 * 프로젝트 메인 화면 데이터베이스 작업을 위임받은 클래스입니다.
 * @author Doyun Lee
 *
 */
public class ProjectDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	private Random rnd;
	
	//생성자
	public ProjectDAO() {
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
	
	/**
	 * 프로젝트 정보를 반환하는 메소드입니다.
	 * @param pseq 프로젝트 번호
	 * @return 프로젝트DTO
	 */
	public ProjectDTO getProjectInfo(String pseq) {

		try {
			
			String sql = "select seq, name, startdate, enddate, decode(description, null, '등록된 설명이 없습니다.', description) as description from tbl_project where delflag = 0 and seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pseq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				ProjectDTO dto = new ProjectDTO();
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setDescription(rs.getString("description"));
				dto.setStartdate(rs.getString("startdate").substring(0,10));
				dto.setEnddate(rs.getString("enddate").substring(0,10));
			
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 프로젝트 참여 멤버를 반환하는 메소드입니다.
	 * @param pseq 프로젝트 번호
	 * @return 프로젝트 참여 멤버 DTO 리스트
	 */
	public ArrayList<MemberDTO> getMemberList(String pseq) {

		try {
			
			String sql = "select m.seq, m.name, decode((select filename from tbl_profile where seq = m.profile_seq), null, '0.png', (select filename from tbl_profile where seq = m.profile_seq)) as profile from tbl_project_attend a inner join tbl_member m on a.member_seq = m.seq where a.delflag = 0 and a.project_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pseq);
			rs = pstat.executeQuery();
			
			ArrayList<MemberDTO> mlist = new ArrayList<MemberDTO>();
			
			while(rs.next()) {
				
				MemberDTO dto = new MemberDTO();
				dto.setMseq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setProfilepic(rs.getString("profile"));
				
				mlist.add(dto);
			}
			
			return mlist;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	/**
	 * 체크리스트 객체 리스트를 반환하는 메소드입니다.
	 * @param pseq 프로젝트 번호
	 * @return 체크리스트 DTO 리스트
	 */
	public ArrayList<ChecklistDTO> getChecklist(String pseq) {
		
		try {
			
			String sql = "select * from tbl_checklist where delflag = 0 and project_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pseq);
			rs = pstat.executeQuery();
			
			ArrayList<ChecklistDTO> list = new ArrayList<ChecklistDTO>();
			
			while(rs.next()) {
				
				ChecklistDTO dto = new ChecklistDTO();
				dto.setSeq(rs.getString("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setPseq(rs.getString("project_seq"));
				
				list.add(dto);
			}
			
			return list;			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 체크리스트 항목 객체 리스트를 반환하는 메소드입니다.
	 * @param list 체크리스트 DTO 리스트
	 * @return 체크리스트 항목 DTO 리스트
	 */
	public ArrayList<ChecklistDTO> getChecklistItem(ArrayList<ChecklistDTO> list) {

		try {
			
			String sql = "select i.*, (select name from tbl_member where a.member_seq = seq) as name from tbl_checklist_item i inner join tbl_project_attend a on i.project_attend_seq = a.seq where i.delflag = 0 and i.checklist_seq = ?";
			pstat = conn.prepareStatement(sql);
			
			
			for (ChecklistDTO dto : list) {
				
				ArrayList<ChecklistItemDTO> ilist = new ArrayList<ChecklistItemDTO>();
				pstat.setString(1, dto.getSeq());
				rs = pstat.executeQuery();
				
				while(rs.next()) {
					ChecklistItemDTO idto = new ChecklistItemDTO();
					idto.setSeq(rs.getString("seq"));
					idto.setTitle(rs.getString("title"));
					idto.setStartdate(rs.getString("startdate"));
					idto.setEnddate(rs.getString("enddate"));
					idto.setName(rs.getString("name"));
					
					ilist.add(idto);
				}
				dto.setList(ilist);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 차트 출력을 위해 차트 DTO 리스트를 반환하는 메소드입니다.
	 * @param pseq 프로젝트 번호
	 * @return 차트 DTO 리스트
	 */
	public ArrayList<ChartDTO> getContribute(String pseq) {

		try {
			
			String sql = "select count(*) as cnt, (select name from tbl_member where pa.member_seq = seq) as name  from tbl_activity a inner join tbl_project_attend pa on a.project_attend_seq = pa.seq where a.delflag = 0 and pa.project_seq = ? group by pa.member_seq";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pseq);
			rs = pstat.executeQuery();
			
			ArrayList<ChartDTO> list = new ArrayList<ChartDTO>();
			while(rs.next()) {
				
				ChartDTO dto = new ChartDTO();
				dto.setName(rs.getString("name"));
				dto.setData(rs.getInt("cnt"));
				
				list.add(dto);
			}
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 프로젝트 경과 일수를 반환하는 메소드입니다.
	 * @param pseq 프로젝트 번호
	 * @return 총 날짜, 경과 날짜를 저장한 해시맵
	 */
	public HashMap<String, Integer> getProcess(String pseq) {

		try {
			
			String sql = "select enddate - startdate as days, round(sysdate - startdate) as progress from tbl_project where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pseq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				HashMap<String, Integer> map = new HashMap<String, Integer>();
				//progress 총날짜, 남은날짜
				int total = rs.getInt("days");
				int passed = rs.getInt("progress");
				
				if (passed < 0) {	//지난 날짜가 음수인 경우 (아직 시작하지 않은 경우)
					passed = 0;
				} else if (total <= passed) {	//지난 날짜가 총날짜보다 훌쩍 지난 경우(이미 끝난 경우)
					passed = total;
				} 
				
				map.put("total", total);	//총날짜
				map.put("passed", passed);	//지난날짜
				map.put("value", (int)(passed/total*100));
				return map;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 프로젝트 활동 내역을 반환하는 메소드입니다.
	 * @param pseq 프로젝트 번호
	 * @return 프로젝트 활동 내역 리스트
	 */
	public ArrayList<String> getActivity(String pseq) {

		try {
			
			String sql= "select a.content, (select name from tbl_member where p.member_seq = seq)as name from tbl_activity a inner join tbl_project_attend p on a.project_attend_seq = p.seq where p.project_seq = ? order by a.regdate desc";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pseq);
			rs = pstat.executeQuery();
			
			ArrayList<String> list = new ArrayList<String>();
			
			while(rs.next()) {
				
				String temp = String.format("'%s'님이 %s", rs.getString("name"), rs.getString("content"));
				list.add(temp);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}




}
