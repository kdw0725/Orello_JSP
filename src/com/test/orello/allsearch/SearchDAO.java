package com.test.orello.allsearch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.orello.DBUtil;

public class SearchDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public SearchDAO() {
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

	public ArrayList<SearchDTO> free(HashMap<String, String> map) {

		try {

			String where = String.format("title like '%%%s%%' or content like '%%%s%%'", map.get("searchAll"),
					map.get("searchAll"));

			String sql = String.format(
					"select * from (select * from (select a.*, rownum as rnum from (select seq, title, content from (select * from tbl_member m\r\n" + 
					"    inner join tbl_project_attend pa\r\n" + 
					"        on pa.member_seq = m.seq\r\n" + 
					"            inner join tbl_freeboard fb\r\n" + 
					"                on fb.project_attend_seq = pa.seq\r\n" + 
					"            where pa.member_seq = ?) where %s order by seq desc) a) where rnum >= ? and rnum <= ?)",
					where);

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("mseq"));
			pstat.setString(2, map.get("begin"));
			pstat.setString(3, map.get("end"));

			rs = pstat.executeQuery();

			ArrayList<SearchDTO> free = new ArrayList<SearchDTO>();

			while (rs.next()) {

				SearchDTO dto = new SearchDTO();

				dto.setBseq(rs.getString("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content").substring(0, 80) + "...");

				free.add(dto);
			}

			return free;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<SearchDTO> notice(HashMap<String, String> map) {

		try {

			String where = String.format("title like '%%%s%%' or content like '%%%s%%'", map.get("searchAll"),
					map.get("searchAll"));

			String sql = String.format(
					"select * from (select * from (select a.*, rownum as rnum from (select seq, title, content from (select * from tbl_member m\r\n" + 
					"    inner join tbl_project_attend pa\r\n" + 
					"        on pa.member_seq = m.seq\r\n" + 
					"            inner join tbl_freeboard fb\r\n" + 
					"                on fb.project_attend_seq = pa.seq\r\n" + 
					"            where pa.member_seq = ?) where %s order by seq desc) a) where rnum >= ? and rnum <= ?)",
					where);

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("mseq"));
			pstat.setString(2, map.get("begin"));
			pstat.setString(3, map.get("end"));

			rs = pstat.executeQuery();

			ArrayList<SearchDTO> notice = new ArrayList<SearchDTO>();

			while (rs.next()) {

				SearchDTO dto = new SearchDTO();

				dto.setNseq(rs.getString("seq"));
				dto.setNtitle(rs.getString("title"));
				dto.setNcontent(rs.getString("content").substring(0, 40) + "...");

				notice.add(dto);
			}

			System.out.println(notice);
			return notice;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public int countFree(HashMap<String, String> map) {

		try {
			
			String where = String.format("title like '%%%s%%' or content like '%%%s%%'", map.get("searchAll"),
					map.get("searchAll"));

			String sql = String.format(
					"select count(*) as cnt from (select b.* from (select a.*, rownum as rnum from (select seq, title, content from ((select * from tbl_member m\r\n" + 
					"    inner join tbl_project_attend pa\r\n" + 
					"        on pa.member_seq = m.seq\r\n" + 
					"            inner join tbl_freeboard fb\r\n" + 
					"                on fb.project_attend_seq = pa.seq\r\n" + 
					"            where pa.member_seq = ?)) where %s order by seq desc) a)b)",
					where);
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("mseq"));
			
			rs = pstat.executeQuery();
			
			if (rs.next());
				
			return rs.getInt("cnt");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int countNotice(HashMap<String, String> map) {

try {
			
			String where = String.format("title like '%%%s%%' or content like '%%%s%%'", map.get("searchAll"),
					map.get("searchAll"));

			String sql = String.format(
					"select count(*) as cnt from (select b.* from (select a.*, rownum as rnum from (select seq, title, content from (select * from tbl_member m\r\n" + 
					"    inner join tbl_project_attend pa\r\n" + 
					"        on pa.member_seq = m.seq\r\n" + 
					"            inner join tbl_freeboard fb\r\n" + 
					"                on fb.project_attend_seq = pa.seq\r\n" + 
					"            where pa.member_seq = ?) where %s order by seq desc) a)b)",
					where);
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("mseq"));
			
			rs = pstat.executeQuery();
			
			if (rs.next());
				
			return rs.getInt("cnt");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<SearchDTO> pNotice(HashMap<String, String> map) {
		
		try {

			String where = String.format("title like '%%%s%%' or content like '%%%s%%'", map.get("searchAll"),
					map.get("searchAll"));

			String sql = String.format(
					"select * from (select * from (select a.*, rownum as rnum from (select seq, title, content from (select * from tbl_member m\r\n" + 
					"    inner join tbl_project_attend pa\r\n" + 
					"        on pa.member_seq = m.seq\r\n" + 
					"            inner join tbl_project_notice pn\r\n" + 
					"                on pn.project_attend_seq = pa.seq\r\n" + 
					"            where pa.member_seq = ?) where %s order by seq desc) a) where rnum >= ? and rnum <= ?)",
					where);

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("mseq"));
			pstat.setString(2, map.get("begin"));
			pstat.setString(3, map.get("end"));

			rs = pstat.executeQuery();

			ArrayList<SearchDTO> pNotice = new ArrayList<SearchDTO>();

			while (rs.next()) {

				SearchDTO dto = new SearchDTO();

				dto.setPseq(rs.getString("seq"));
				dto.setPtitle(rs.getString("title"));
				dto.setPcontent(rs.getString("content").substring(0, 20) + "...");

				pNotice.add(dto);
			}

			
			return pNotice;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int countPNotice(HashMap<String, String> map) {
		
try {
			
			String where = String.format("title like '%%%s%%' or content like '%%%s%%'", map.get("searchAll"),
					map.get("searchAll"));

			String sql = String.format(
					"select count(*) as cnt from (select b.* from (select a.*, rownum as rnum from (select seq, title, content from (select * from tbl_member m\r\n" + 
					"    inner join tbl_project_attend pa\r\n" + 
					"        on pa.member_seq = m.seq\r\n" + 
					"            inner join tbl_project_notice pn\r\n" + 
					"                on pn.project_attend_seq = pa.seq\r\n" + 
					"            where pa.member_seq = ?) where %s order by seq desc) a)b)",
					where);
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("mseq"));
			
			rs = pstat.executeQuery();
			
			if (rs.next());
				
			return rs.getInt("cnt");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

}
