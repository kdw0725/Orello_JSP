package com.test.orello.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.orello.DBUtil;



public class BoardDAO {
	

		private Connection conn;
		private Statement stat;
		private PreparedStatement pstat;
		private ResultSet rs;

		public BoardDAO() {
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

	//List서블릿 -> 리스트 목록 출력
	public ArrayList<BoardDTO> list(String pseq) {

		try {
			
			String sql = "select b.seq as seq, b.title as title, b.regdate as regdate"
					+ ", b.readcount as readcount, m.name as name  from tbl_freeboard b" + 
					"    inner join tbl_project_attend pa" + 
					"    on pa.seq = b.project_attend_seq" + 
					"        inner join tbl_member m" + 
					"        on m.seq = pa.member_seq" + 
					"            inner join tbl_project p" + 
					"            on p.seq = pa.project_seq" + 
					"                where p.seq = ? order by b.regdate desc";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pseq);
			
			rs = pstat.executeQuery();
			
			ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setTitle(rs.getString("title"));
//				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcount(rs.getString("readcount"));
				dto.setName(rs.getString("name"));
				
				list.add(dto);
				
			}
			
			return list;
			
			
		} catch (Exception e) {

		System.out.println("BoardDAO.list()");
		e.printStackTrace();
		
		}
		
		
		return null;
	}

	
	//View서블릿 > 글 내용 보여주기
	public BoardDTO view(String seq) {

		try {
			
			String sql = "select b.seq as seq, b.title as title"
					+ ", b.content as content, b.regdate as regdate, b.readcount as readcount"
					+ ", m.name as name, m.email as email from tbl_freeboard b" + 
					"    inner join tbl_project_attend pa" + 
					"    on pa.seq = b.project_attend_seq" + 
					"        inner join tbl_member m" + 
					"        on m.seq = pa.member_seq" + 
					"            inner join tbl_project p" + 
					"            on p.seq = pa.project_seq" + 
					"                where b.seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcount(rs.getString("readcount"));
				
				System.out.println(dto.getTitle());
				return dto;
			}
			
			
			
		} catch (Exception e) {
			System.out.println("BoardDAO.view()");
			e.printStackTrace();
		
		}
		
		
		
		return null;
	}

}
