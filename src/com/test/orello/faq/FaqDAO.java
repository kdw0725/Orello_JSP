package com.test.orello.faq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.orello.DBUtil;

public class FaqDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public FaqDAO() {
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

	public ArrayList<FaqDTO> faq() {
		int cnt = 1;
		try {
			
			String sql = "select * from tbl_faq order by seq";
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			ArrayList<FaqDTO> faq = new ArrayList<FaqDTO>();
			
			while (rs.next()) {
				
				FaqDTO dto = new FaqDTO();
					
				dto.setCnt(cnt++);
					dto.setFseq(rs.getString("seq"));
					dto.setQuestion(rs.getString("title"));
					dto.setContent(rs.getString("content"));
				
				faq.add(dto);
			}
			
			return faq;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}



	
	//FAQ 내용 추가 메소드
	public void addContent(HashMap<String, String> map) {
		System.out.println(map.get("title"));
		System.out.println(map.get("content"));
		try {
			String sql = "insert into tbl_faq (seq, title, content, admin_seq, delflag) values (seq_faq.nextVal, ?, ?, 1, default)";
		
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("title"));
			pstat.setString(2, map.get("content"));
		
			
			pstat.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	//select box content 삭제하기
	public void deleteContent(String[] value) {
		try {
			String sql = "DELETE FROM TBL_FAQ WHERE SEQ = ?";
			
			pstat = conn.prepareStatement(sql);
			
			for(String v : value) {
				if(v != null) {
					pstat.setString(1, v);
					pstat.executeUpdate();
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}


}
