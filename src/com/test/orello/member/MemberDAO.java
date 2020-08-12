package com.test.orello.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			String sql = "SELECT COUNT(*) AS CNT FROM TBL_MEMBER WHERE EMAIL = ?";
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
			String sql = "SELECT COUNT(*) AS CNT FROM TBL_MEMBER WHERE TEL = ?";
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
	
	
}
