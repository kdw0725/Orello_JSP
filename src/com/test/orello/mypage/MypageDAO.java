package com.test.orello.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.orello.DBUtil;

public class MypageDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public MypageDAO() {
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

	//mypage 서블릿 -> 프로필 리스트 위임
	public MypageDTO getProfile(String seq) {

		try {
			
			String sql = "select * from tbl_member where seq = ? and delflag = 0";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			MypageDTO dto = new MypageDTO();
			while(rs.next()) {
				
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setTel(rs.getString("tel"));
				dto.setPoint(rs.getString("point"));
				dto.setStatusmsg(rs.getString("statusmsg"));
				
			}
			return dto;
			
		} catch (Exception e) {
			System.out.println("MypageDAO.profileList()");
		
		
		}
		
		
		
		
		
		
		
		return null;
	}

	//main 서블릿 -> 친구리스트 위임
	public ArrayList<MyFriendDTO> getFriendList(String seq) {

		try {
			
			String sql = "select * from tbl_member where seq "
					+ "in (select friend_seq from tbl_friend where member_seq = ? and delflag = 0) "
					+ "and delflag = 0 order by name"; 
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			
			ArrayList<MyFriendDTO> list = new ArrayList<MyFriendDTO>();
			
			while(rs.next()) {
				MyFriendDTO dto = new MyFriendDTO();
				
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setStatusmsg(rs.getString("statusmsg"));
				dto.setTel(rs.getString("tel"));
				dto.setFriend_seq(rs.getString("seq"));
				
				list.add(dto);
				
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("MypageDAO.getFriendList()");
			e.printStackTrace();
		}
		
		
		return null;
	}

	//Main 서블릿 -> 프로젝트 목록 위임
	public ArrayList<MyProjectDTO> getProjectList(String seq) {

		try {
			
			String sql = "select p.seq as pseq, pa.seq as paseq, p.name, p.type, p.startdate, p.enddate, p.regdate, pa.position from tbl_member m" + 
					"    inner join tbl_project_attend pa" + 
					"    on m.seq = pa.member_seq" + 
					"        inner join tbl_project p" + 
					"        on p.seq = pa.project_seq" + 
					"            where m.seq = ? "
					+ "and m.delflag =0 and pa.delflag=0 and p.delflag=0"
					+ " order by p.startdate desc";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<MyProjectDTO> list = new ArrayList<MyProjectDTO>();
			
			while(rs.next()) {
				
				MyProjectDTO dto = new MyProjectDTO();
				
				dto.setName(rs.getString("name"));
				dto.setType(rs.getString("type"));
				dto.setStartdate(rs.getString("startdate").substring(0,10));
				dto.setEnddate(rs.getString("enddate").substring(0,10));
				dto.setRegdate(rs.getString("regdate").substring(0,10));
				dto.setPosition(rs.getString("position"));
				dto.setPseq(rs.getString("pseq"));
				dto.setPaseq(rs.getString("paseq"));
				
				list.add(dto);
				
			}
			
			return list;
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("MypageDAO.getProjectList()");
			e.printStackTrace();
			
		
		}
		
		
		
		return null;
	}

	//Main 서블릿 -> 친구삭제 위임
	public int deleteFriend(String seq, String fseq) {

		try {
			
			String sql = "update tbl_friend set delflag = 1 where member_seq =? and friend_seq=?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			pstat.setString(2, fseq);
			
			
			return pstat.executeUpdate();
			
			
			
		} catch (Exception e) {

			System.out.println("MypageDAO.getProjectList()");
			e.printStackTrace();
		
		}
		
		return 0;
	}




}
