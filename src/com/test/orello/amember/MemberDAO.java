package com.test.orello.amember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.orello.DBUtil;



public class MemberDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public MemberDAO() {
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

	//UserView 서블릿 -> 회원번호를 주고 회원 정보를 반환 받기 위해 위임
	public MemberDTO get(String seq) {

		try {
			
			String sql = "select * from tbl_Member where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq); // 회원 번호
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				MemberDTO dto = new MemberDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setRegdate(rs.getString("regdate").substring(0, 10));
				dto.setEmail(rs.getString("email"));
				dto.setPoint(rs.getString("point"));
				
				
				return dto;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//
	public ArrayList<MemberDTO> list(HashMap<String, String> map) {

		try {
			
			//목록 or 검색
			String where = "";
			
			
			if (map.get("search") != null) { // 이름 & 제목 & 내용 - 포괄 검색
				System.out.println(map.get("search"));
				System.out.println(map.get("soption"));
				
				if(map.get("soption").equals("0")) {
				where = String.format("where (name like '%%%s%%') or (email like '%%%s%%')", map.get("search"), map.get("search"));
				} else if(map.get("soption").equals("0")) {
					where = String.format("where (name like '%%%s%%')", map.get("search"));
				} else if(map.get("soption").equals("1")) {
					where = String.format("where (email like '%%%s%%')", map.get("search"));
			}
			}
			
			String sql = String.format("select * from (select a.*, rownum as rnum from (select * from tbl_member %s) a) where rnum >= %s and rnum <= %s  order by %s desc",   where, map.get("begin"), map.get("end"), map.get("sort"));
			
			stat = conn.prepareStatement(sql);
			rs = stat.executeQuery(sql);
			
			ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
			
			
			while (rs.next()) {
				
				MemberDTO dto = new MemberDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setRegdate(rs.getString("regdate").substring(0,10));
				dto.setEmail(rs.getString("email"));
				dto.setPoint(rs.getString("point"));
				
				list.add(dto);
			}
			return list;
			
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
				where = String.format("where (name like '%%%s%%' or email like '%%%s%%')", map.get("search"), map.get("search"));
			}
			
			//where절을 만들어야함
			//String sql = "select count(*) as cnt from tblBoard %s";
			String sql = String.format("select count(*) as cnt from tbl_member %s", where);
			
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

	//UserView 서블릿에서 회원의 프로젝트 목록 가져오기
	public ArrayList<MemberDTO> info(String seq) {

		try {
			
			String sql = "SELECT PA.PROJECT_SEQ, (SELECT TM.NAME FROM TBL_MEMBER TM INNER JOIN TBL_PROJECT_ATTEND TPA ON TM.SEQ = TPA.MEMBER_SEQ WHERE TPA.POSITION = '팀장' AND PA.PROJECT_SEQ = TPA.PROJECT_SEQ AND TM.DELFLAG = 0 AND TPA.DELFLAG = 0) AS LEADER, P.NAME, P.STARTDATE, P.ENDDATE FROM TBL_MEMBER M INNER JOIN TBL_PROJECT_ATTEND PA ON PA.MEMBER_SEQ = M.SEQ INNER JOIN TBL_PROJECT P ON P.SEQ = PA.PROJECT_SEQ WHERE M.SEQ = ? AND M.DELFLAG = 0 AND PA.DELFLAG = 0 AND P.DELFLAG = 0";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<MemberDTO> info = new ArrayList<MemberDTO>();
			
			while (rs.next()) {
				
				MemberDTO dto = new MemberDTO();
				
				dto.setPseq(seq);
				dto.setProjectname(rs.getString("name"));
				dto.setStartdate(rs.getString("startdate").substring(0,10));
				dto.setEnddate(rs.getString("enddate").substring(0,10));
				dto.setLeader(rs.getString("leader"));
				
				info.add(dto);
				
			}
			
			return info;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int project(String seq) {
		
		try {
			
			String sql = "select count(*) as pcnt from tbl_project_attend where member_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				return rs.getInt("pcnt");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}

	public int board(String seq) {

		try {
			
			String sql = "select count(*) as bcnt from tbl_freeboard tf\r\n" + 
					"    inner join tbl_project_attend tpa\r\n" + 
					"        on tpa.seq = tf.project_attend_seq\r\n" + 
					"            where tpa.member_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				return rs.getInt("bcnt");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<MemberDTO> data(String seq) {
		
		try {
			
			String sql = "select * from tbl_member where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<MemberDTO> data = new ArrayList<MemberDTO>();
			
			while (rs.next()) {
				
				MemberDTO dto = new MemberDTO();
				
				dto.setName(rs.getString("name"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setEmail(rs.getString("email"));
				dto.setPoint(rs.getString("point"));
				dto.setTel(rs.getString("tel"));
				
				data.add(dto);
				
			}
			return data;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	//회원의 프로젝트당 작성한 게시글 수
	public ArrayList<String> boardCnt(String seq) {
		//System.out.println("등록");
		try {
			String sql = "select pa.seq as paseq from tbl_member m inner join tbl_project_attend pa on m.seq = pa.member_seq inner join tbl_project p on pa.project_Seq = p.seq where m.seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<String> list = new ArrayList<>();
			
			while(rs.next()) {
				//MemberDTO dto = new MemberDTO();
				
				//dto.setPaseq(rs.getString("paseq"));
				list.add(rs.getString("paseq"));
			}
			
			//System.out.println(list);
			
			String sql2 = "select count(*) as cnt from tbl_freeboard group by project_attend_seq having project_attend_seq = ?";
			
			pstat = conn.prepareStatement(sql2);
			ArrayList<String> result = new ArrayList<>();
			
			for(String s : list) {
				pstat.setString(1, s);
				rs = pstat.executeQuery();
				if(rs.next()) {
//					MemberDTO dto = new MemberDTO();
//					dto.setBoardCnt(rs.getInt("cnt"));
					result.add(rs.getString("cnt"));
				}
				//System.out.println(result);
			}
			return result;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	//회원의 프로젝트 이름 목록
	public ArrayList<String> projectName(String seq) {
		try {
			String sql = "select p.name as pname from tbl_member m inner join tbl_project_attend pa on m.seq = pa.member_seq inner join tbl_project p on pa.project_Seq = p.seq where m.seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			
			ArrayList<String> list = new ArrayList<>();
			
			while(rs.next()) {
//				MemberDTO dto = new MemberDTO();
//				
//				dto.setProjectname(rs.getString("pname"));
				list.add(rs.getString("pname"));
			}
			
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}



	
	
}
