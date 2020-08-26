package com.test.orello.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

	public int emailCheck(String email) {
		try {
			String sql = "SELECT COUNT(*) AS CNT FROM TBL_MEMBER WHERE EMAIL = ? AND DELFLAG = 0";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, email);
			
			rs = pstat.executeQuery();
			int result = 0;
			if(rs.next()) {
				result = rs.getInt("CNT");
			}
			return result;
					
		} catch (Exception e) {
			System.out.println("MemberDAO.emailCheck()");
			e.printStackTrace();
		}
		
		return -1;
	}

	public int telCheck(String tel) {
		
		try {
			String sql = "SELECT COUNT(*) AS CNT FROM TBL_MEMBER WHERE TEL = ? AND DELFLAG = 0";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, tel);
			
			int result = 0;
			rs = pstat.executeQuery();
			if(rs.next()) {
				result = rs.getInt("CNT");
			}
			return result;
			
		} catch (Exception e) {
			System.out.println("MemberDAO.telCheck()");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int signIn(MemberDTO dto) {
		try {
			String sql = "INSERT INTO TBL_PROFILE(SEQ, ORGFILENAME, FILENAME, DELFLAG) VALUES(SEQ_PROFILE.NEXTVAL, ?, ?, DEFAULT)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getOri_file());
			pstat.setString(2, dto.getFile());
			
			pstat.executeUpdate();
			pstat.close();
			
			sql = "INSERT INTO TBL_MEMBER(SEQ, NAME, EMAIL, PW, REGDATE, COMPANY, TEL, POINT, STATUSMSG, PROFILE_SEQ, SOCIAL, DELFLAG) VALUES(SEQ_MEMBER.NEXTVAL, ?, ?, ?, DEFAULT, ?, ?, DEFAULT, NULL, SEQ_PROFILE.CURRVAL, NULL, DEFAULT )";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getEmail());
			pstat.setString(3, dto.getPw());
			pstat.setString(4, dto.getCompany());
			pstat.setString(5, dto.getTel());
			return  pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("MemberDAO.signIn()");
			e.printStackTrace();
		}
		return 0;
	}

	public MemberDTO login(MemberDTO dto) {
		try {
			
			String sql = "SELECT * FROM TBL_MEMBER WHERE EMAIL = ? AND PW = ? AND DELFLAG = 0 AND SOCIAL IS NULL";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getEmail());
			pstat.setString(2, dto.getPw());
			MemberDTO member = new MemberDTO();
			rs = pstat.executeQuery();
			if(rs.next()) {
				member.setSeq(rs.getString("seq"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setCompany(rs.getString("company"));
				member.setStatusmsg(rs.getString("statusmsg"));
				member.setProfile_seq(rs.getString("profile_seq"));
			}
			return member;
		} catch (Exception e) {
			System.out.println("MemberDAO.login()");
			e.printStackTrace();
		}
		return null;
	}

	public MemberDTO signInCheck(MemberDTO dto) {
		try {
			String sql = "SELECT * FROM TBL_MEMBER WHERE EMAIL = ? AND DELFLAG = 0 AND SOCIAL = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getEmail());
			pstat.setNString(2, dto.getSocial());
			rs = pstat.executeQuery();
			
			MemberDTO member = new MemberDTO(); 
			if(rs.next()) {
				member.setSeq(rs.getString("seq"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setCompany(rs.getString("company"));
				member.setStatusmsg(rs.getString("statusmsg"));
				member.setProfile_seq(rs.getString("profile_seq"));
			}
			return member;
			
			
		} catch (Exception e) {
			System.out.println("MemberDAO.signInCheck()");
			e.printStackTrace();
		}
		return null;
	}

	public void naverSignIn(MemberDTO dto) {
		
		try {
			String sql = "INSERT INTO TBL_PROFILE(SEQ, ORGFILENAME, FILENAME, DELFLAG) VALUES(SEQ_PROFILE.NEXTVAL, ?, ?, DEFAULT)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, "nopic.png");
			pstat.setString(2, "nopic.png");
			pstat.executeUpdate();
			pstat.close();
			
			sql = "INSERT INTO TBL_MEMBER(SEQ, NAME, EMAIL, PW, REGDATE, COMPANY, TEL, POINT, STATUSMSG, PROFILE_SEQ, SOCIAL, DELFLAG) VALUES(SEQ_MEMBER.NEXTVAL, ?, ?, ?, DEFAULT, NULL, ?, DEFAULT, NULL, SEQ_PROFILE.CURRVAL, ?, DEFAULT )";
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getEmail());
			pstat.setString(3, "1");
			pstat.setString(4, dto.getTel());
			pstat.setString(5, "NAVER");
			pstat.executeUpdate();
			pstat.close();
			
		} catch (Exception e) {
			System.out.println("MemberDAO.naverSignIn()");
			e.printStackTrace();
		}
		
	}

	public void kakaoSignIn(MemberDTO dto) {
		try {
			String sql = "INSERT INTO TBL_PROFILE(SEQ, ORGFILENAME, FILENAME, DELFLAG) VALUES(SEQ_PROFILE.NEXTVAL, ?, ?, DEFAULT)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, "nopic.png");
			pstat.setString(2, "nopic.png");
			pstat.executeUpdate();
			pstat.close();
			
			sql = "INSERT INTO TBL_MEMBER(SEQ, NAME, EMAIL, PW, REGDATE, COMPANY, TEL, POINT, STATUSMSG, PROFILE_SEQ, SOCIAL, DELFLAG) VALUES(SEQ_MEMBER.NEXTVAL, ?, ?, ?, DEFAULT, NULL, ?, DEFAULT, NULL, SEQ_PROFILE.CURRVAL, ?, DEFAULT )";
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getEmail());
			pstat.setString(3, "1");
			pstat.setString(4, dto.getTel());
			pstat.setString(5, "KAKAO");
			pstat.executeUpdate();
			pstat.close();
		} catch (Exception e) {
			System.out.println("MemberDAO.kakaoSignIn()");
			e.printStackTrace();
		}
	}
	
	public void socialImg() {
		try {
			String sql = "INSERT INTO TBL_PROFILE(SEQ, ORGFILENAME, FILENAME, DELFLAG) VALUES(SEQ_PROFILE.NEXTVAL, ?, ?, DEFAULT)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, "nopic.png");
			pstat.setString(2, "nopic.png");
			pstat.executeUpdate();
			pstat.close();
		} catch (Exception e) {
			System.out.println("MemberDAO.socialImg()");
			e.printStackTrace();
		}
	}
	
	public void socialSignIn(MemberDTO dto) {
		try {
			String sql = "INSERT INTO TBL_MEMBER(SEQ, NAME, EMAIL, PW, REGDATE, COMPANY, TEL, POINT, STATUSMSG, PROFILE_SEQ, SOCIAL, DELFLAG) VALUES(SEQ_MEMBER.NEXTVAL, ?, ?, ?, DEFAULT, NULL, ?, DEFAULT, NULL, SEQ_PROFILE.CURRVAL, ?, DEFAULT )";
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getEmail());
			pstat.setString(3, "1");
			pstat.setString(4, dto.getTel());
			pstat.setString(5, dto.getSocial());
			pstat.executeUpdate();
			pstat.close();
		} catch (Exception e) {
			System.out.println("MemberDAO.socialSignIn()");
			e.printStackTrace();
		}
	}

	public MemberDTO getProfile(String seq) {
		try {
			String sql = "SELECT * FROM TBL_MEMBER MEM INNER JOIN TBL_PROFILE PRO ON MEM.PROFILE_SEQ = PRO.SEQ WHERE MEM.SEQ = ? AND MEM.DELFLAG = 0 AND PRO.DELFLAG = 0";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			MemberDTO dto = new MemberDTO();
			if(rs.next()) {
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setCompany(rs.getString("company"));
				dto.setTel(rs.getString("tel"));
				dto.setStatusmsg(rs.getString("statusmsg"));
				dto.setOri_file(rs.getString("orgfilename"));
				dto.setSocial(rs.getString("social"));
			}
			
			return dto;
			
		} catch (Exception e) {
			System.out.println("MemberDAO.getProfile()");
			e.printStackTrace();
		}
		return null;
	}

	public int updateComment(MemberDTO dto) {
		
		try {
			String sql = "UPDATE TBL_MEMBER SET STATUSMSG = ? WHERE SEQ = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getStatusmsg());
			pstat.setString(2, dto.getSeq());
			
			return pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println("MemberDAO.updateComment()");
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<ProjectDTO> getProjectList(String seq) {
		try {
			String sql = "SELECT * FROM (SELECT PRO.SEQ AS SEQ, PRO.NAME AS NAME, PRO.TYPE AS TYPE, PRO.FIRSTPOPULAR AS FIRSTPOPULAR, PRO.SECONDPOPULAR AS SECONDPOPULAR, COL.COLORCODE AS COLOR FROM TBL_PROJECT PRO INNER JOIN TBL_PROJECT_ATTEND ATT ON PRO.SEQ = ATT.PROJECT_SEQ INNER JOIN TBL_COLOR COL ON COL.SEQ = PRO.COLOR_SEQ WHERE ATT.MEMBER_SEQ = ? AND PRO.DELFLAG = 0 AND ATT.DELFLAG = 0 AND COL.DELFLAG = 0 ORDER BY STARTDATE ASC) WHERE ROWNUM BETWEEN 1 AND 2";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			ArrayList<ProjectDTO> list = new ArrayList<ProjectDTO>();
			while(rs.next()) {
				ProjectDTO dto = new ProjectDTO();
				dto.setSeq(rs.getString("SEQ"));
				dto.setName(rs.getString("NAME").length() > 7 ? rs.getString("NAME").substring(0, 7) + "..." : rs.getString("NAME"));
				dto.setType(rs.getString("TYPE"));
				dto.setFirstpopular(rs.getString("FIRSTPOPULAR"));
				dto.setSecondpopular(rs.getString("SECONDPOPULAR"));
				dto.setColor(rs.getString("COLOR"));
				
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			System.out.println("MemberDAO.getProjectList()");
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ProjectDTO> getMoreProject(String seq) {
		try {
			String sql = "SELECT * FROM (SELECT ROWNUM AS RN, TB.* FROM (SELECT PRO.SEQ AS SEQ, PRO.NAME AS NAME, PRO.TYPE AS TYPE, PRO.FIRSTPOPULAR AS FIRSTPOPULAR, PRO.SECONDPOPULAR AS SECONDPOPULAR, COL.COLORCODE AS COLOR FROM TBL_PROJECT PRO INNER JOIN TBL_PROJECT_ATTEND ATT ON PRO.SEQ = ATT.PROJECT_SEQ INNER JOIN TBL_COLOR COL ON COL.SEQ = PRO.COLOR_SEQ WHERE ATT.MEMBER_SEQ = ? AND PRO.DELFLAG = 0 AND ATT.DELFLAG = 0 AND COL.DELFLAG = 0 ORDER BY STARTDATE ASC)TB) WHERE RN > 2";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			ArrayList<ProjectDTO> list = new ArrayList<ProjectDTO>();
			while(rs.next()) {
				ProjectDTO dto = new ProjectDTO();
				dto.setSeq(rs.getString("SEQ"));
				dto.setName(rs.getString("NAME").length() > 7 ? rs.getString("NAME").substring(0, 7) + "..." : rs.getString("NAME"));
				dto.setType(rs.getString("TYPE"));
				dto.setFirstpopular(rs.getString("FIRSTPOPULAR"));
				dto.setSecondpopular(rs.getString("SECONDPOPULAR"));
				dto.setColor(rs.getString("COLOR"));
				
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			System.out.println("MemberDAO.getMoreProject()");
			e.printStackTrace();
		}
		return null;
	}
	
	
}
