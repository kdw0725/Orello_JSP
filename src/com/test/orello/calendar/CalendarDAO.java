package com.test.orello.calendar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.orello.DBUtil;

public class CalendarDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public CalendarDAO() {
		// DB연결

		DBUtil util = new DBUtil();
		conn = util.open();

	}

	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	//Calendar 서블릿 -> 프로젝트 리스트 
	public ArrayList<ProjectDTO> projectList(String seq, String pseq) {
		
		try {
			System.out.println(100);
			String sql = "select seq, content, title, startdate, enddate, completeflag from tbl_checklist_item where checklist_seq in (select seq from tbl_checklist where project_seq = ? and delflag = 0) and project_attend_seq = ? and delflag=0";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1,pseq);
			pstat.setString(2,getAttendSeq(seq, pseq));
			System.out.println("apseq" + pseq);
			System.out.println("aseq" + getAttendSeq(seq, pseq));
			rs = pstat.executeQuery();
			System.out.println(200);
			
			
			ArrayList<ProjectDTO> list = new ArrayList<ProjectDTO>();
			int i = 1;
			while(rs.next()) {
				ProjectDTO dto = new ProjectDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setStartdate(rs.getString("startdate").substring(0,10));
				dto.setEnddate(rs.getString("enddate").substring(0,10));
				
				dto.setColorcode(getColorcode(rs.getString("seq")));
				dto.setNum(i);
				i++;
				list.add(dto);
//				System.out.println("color"+getColorcode(rs.getString("seq")));
				
			}
			
			return list;
			
		} catch (Exception e) {

			System.out.println("CalendarDAO.projectList()");
			e.printStackTrace();
		
		}
		
		
		
		
		return null;
	}
	
	private String getColorcode(String ciseq) {
		
		try {
			
			String sql = "select colorcode from tbl_color co" + 
					"    inner join tbl_project p" + 
					"    on co.seq = p.color_seq" + 
					"        inner join tbl_checklist c" + 
					"        on p.seq = c.project_seq " + 
					"            inner join tbl_checklist_item ci" + 
					"            on c.seq = ci.checklist_seq where ci.seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, ciseq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				return rs.getString("colorcode");
			}
			
			
		} catch (Exception e) {

			System.out.println("CalendarDAO.getColorcode()");
			e.printStackTrace();
		
		}
		
		
		return null;
	}
	
	private String getAttendSeq(String mseq, String pseq) {

		try {
			
			PreparedStatement pstat2;
			ResultSet rs2;
			
			String sql = "select seq from tbl_project_attend "
					+ "where project_seq =? and member_seq =? and delflag = 0";
			
			pstat2 = conn.prepareStatement(sql);
			pstat2.setString(1, pseq);
			pstat2.setString(2, mseq);
			
			rs2 = pstat2.executeQuery();
			
			if(rs2.next()) {
				
				return rs2.getString("seq");
			}
			pstat2.close();
			//conn.close();
			
		} catch (Exception e) {
			
			System.out.println("BoardDAO.getAttendSeq()");
			e.printStackTrace();
		
		}
		
		return null;
	}
}
