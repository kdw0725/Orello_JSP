package com.test.orello.allsearch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.orello.DBUtil;

/**
 * 통합 검색을 하기 위해 DB 연결을 위한 DAO이다.
 * @author 강경원
 *
 */
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

	/**
	 * 자유 게시글에서 제목, 내용으로 검색을 하기 위해 필요한 메소드이다.
	 * @param map
	 * @return
	 */
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

	/**
	 *  통합 검색에서 공지사항을 검색하기 위해 필요한 메소드이다.
	 * @param map
	 * @return
	 */
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

	/**
	 * 통합 검색에서 검색 된 자유게시글의 갯수를 가져오기 위한 메소드이다.
	 * @param map
	 * @return
	 */
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

	/**
	 * 통합 검색에서 검색 된 공지사항 갯수를 가져오기 위해 필요한 메소드이다.
	 * @param map
	 * @return
	 */
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

	/**
	 * 통합 검색에서 프로젝트 내의 검색 결과를 가져오기위해 필요한 메소드이다.
	 * @param map
	 * @return
	 */
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

	/**
	 * 통합 검색에서 프로젝트 내의 검색 결과 갯수를 가져오기 위한 메소드이다.
	 * @param map
	 * @return
	 */
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
