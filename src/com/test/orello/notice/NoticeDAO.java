package com.test.orello.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.orello.DBUtil;
import com.test.orello.amember.MemberDTO;


public class NoticeDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public NoticeDAO() {
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

	//공지사항 글 목록 가져오기
	public ArrayList<NoticeDTO> nList(HashMap<String, String> map) {
		
		try {
			
			String sql = "select * from tbl_notice order by seq desc";
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			ArrayList<NoticeDTO> nList = new ArrayList<NoticeDTO>();
			
			while (rs.next()) {
				
				NoticeDTO dto = new NoticeDTO();
				
				dto.setNseq(rs.getString("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setWriter(rs.getString("admin_seq"));
				
				nList.add(dto);
				
			}
			
			return nList;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int getTotalCount(HashMap<String, String> map) {
		
		try {
			String where = "";
			
			if (map.get("search") != null) {
				// 이름 & 이메일 - 포괄 검색
				where = String.format("where (title like '%%%s%%' or admin like '%%%s%%')", map.get("search"), map.get("search"));
			}
			
			//where절을 만들어야함
			//String sql = "select count(*) as cnt from tblBoard %s";
			String sql = String.format("select count(*) as cnt from tbl_notice %s", where);
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
				return rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<NoticeDTO> nSearch(HashMap<String, String> map) {
			try {
			
			//목록 or 검색
			String where = "";
			
			
			if (map.get("search") != null) { // 이름 & 제목 & 내용 - 포괄 검색
				System.out.println(map.get("search"));
				System.out.println(map.get("soption"));
				
				if(map.get("soption").equals("0")) {
					where = String.format("where (title like '%%%s%%')", map.get("search"));
				} else if(map.get("soption").equals("1")) {
					where = String.format("where (email like '%%%s%%')", map.get("search"));
			}
			}
			
			String sql = String.format("select * from (select a.*, rownum as rnum from (select * from tbl_notice %s) a) where rnum >= %s and rnum <= %s  order by %s desc",   where, map.get("begin"), map.get("end"), map.get("sort"));
			
			stat = conn.prepareStatement(sql);
			rs = stat.executeQuery(sql);
			
			ArrayList<NoticeDTO> nSearch = new ArrayList<NoticeDTO>();
			
			
			while (rs.next()) {
				
				NoticeDTO dto = new NoticeDTO();
				
				dto.setNseq(rs.getString("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("admin_seq"));
				dto.setRegdate(rs.getString("regdate").substring(0,10));
				
				nSearch.add(dto);
			}
			return nSearch;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
