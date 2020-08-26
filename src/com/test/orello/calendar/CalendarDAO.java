package com.test.orello.calendar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	public ArrayList<ProjectDTO> projectList(String seq) {
		
		try {

			String sql = "select p.seq as seq, p.name as name, p.startdate as startdate, p.enddate as enddate, c.colorcode as colorcode"
					+ " from tbl_project p" + 
					"        inner join tbl_color c" + 
					"        on p.color_seq = c.seq" + 
					"    where p.seq in (select project_seq from tbl_project_attend "
					+ "where member_seq=?) and p.delflag=0 and c.delflag=0";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1,seq);
			rs = pstat.executeQuery();
			
			ArrayList<ProjectDTO> list = new ArrayList<ProjectDTO>();
			int i = 1;
			while(rs.next()) {
				ProjectDTO dto = new ProjectDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setStartdate(rs.getString("startdate").substring(0,10));
				dto.setEnddate(rs.getString("enddate").substring(0,10));
				dto.setColorcode(rs.getString("colorcode"));
				dto.setNum(i);
				i++;
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {

			System.out.println("ProjectDAO.projectList()");
			e.printStackTrace();
		
		}
		
		
		
		
		return null;
	}
}
