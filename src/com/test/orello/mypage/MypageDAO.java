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

	/**
	 * 회원의 정보를 불러오는 메서드
	 * @param seq
	 * 회원 번호
	 * @return
	 * 회원의 이름, 이메일, 전화번호, 포인트, 상태메시지를 담은 dto 객체
	 */
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

	/**
	 * 회원과 친구인 다른 회원의 목록을 불러오는 메서드
	 * @param seq
	 * 회원번호
	 * @return
	 * 친구의 이름, 이메일, 상태메시지, 전화번호, 회원번호가 담긴 dto의 ArrayList
	 */
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
	
	/**
	 * 회원이 참여한 프로젝트의 목록을 불러오는 메서드
	 * @param seq
	 * 회원 번호
	 * @return
	 * 회원이 참여한 프로젝트의 이름, 타입, 시작일, 종료일, 직책, 프로젝트번호, 프로젝트 참여번호가 담긴  dto의 ArrayList
	 */
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

	/**
	 * 친구 목록 중 선택한 친구를 삭제하는 메서드
	 * @param seq
	 * 회원 번호
	 * @param fseq
	 * 선택한 친구의 회원 번호
	 * @return
	 * 삭제 성공시 1, 실패시 0 반환
	 */
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

	/**
	 * 프로젝트의 세부사항을 출력하는 메서드
	 * @param pseq
	 * 프로젝트 번호
	 * @return
	 * 해당 프로젝트의 이름, 시작일, 종료일, 등록일, 타입을 담은 dto 객체
	 */
	//ProjectDetail 서블릿 -> 프로젝트 세부사항 출력
	public MyProjectDTO getProjectDetail(String pseq) {

		try {
			
			String sql = "select * from tbl_project where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pseq);
			
			rs = pstat.executeQuery();
			
			MyProjectDTO dto = new MyProjectDTO();
			
			if(rs.next()) {
				dto.setName(rs.getString("name"));
				dto.setStartdate(rs.getString("startdate"));
				dto.setEnddate(rs.getString("enddate"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setType(rs.getString("type"));
				
				return dto;
				
			}
			
		} catch (Exception e) {
			
			System.out.println("MypageDAO.getProjectDetail()");
			e.printStackTrace();
		
		}
		
		return null;
	}

	/**
	 * 프로젝트의 세부사항 중 프로젝트 참여자의 명단을 출력하는 메서드
	 * @param pseq
	 * 프로젝트 번호
	 * @return
	 * 프로젝트에 참여한 사람의 이름과 회원번호를 담은 dto 객체
	 */
	//ProjectDetail 서블릿 -> 프로젝트 참여자 명단
	public ArrayList<MemberDTO> getAttendant(String pseq) {
		try {
			
			String sql = "select m.seq, m.name from tbl_member m" + 
					"    inner join tbl_project_attend pa" + 
					"    on m.seq = pa.member_seq" + 
					"        inner join tbl_project p" + 
					"        on pa.project_seq = p.seq" + 
					"            where p.seq = ?";
			
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pseq);
			
			
			rs = pstat.executeQuery();
			ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
			while(rs.next()) {
				MemberDTO dto =  new MemberDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				
				list.add(dto);
			}
			
			return list;
			
			
			
		} catch (Exception e) {

			System.out.println("MypageDAO.getAttendant()");
			e.printStackTrace();
		}
		
		return null;
	}




}
