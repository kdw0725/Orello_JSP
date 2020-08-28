package com.test.orello.inc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.orello.DBUtil;


/**
 * 
 * @author 강혜림
 * 관리자 seq를 가져오는 class
 *
 */
public class AdminDAO {

	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	/**
	 * JDBC와 연결하는 메소드
	 */
	public AdminDAO() {
		DBUtil util = new DBUtil();
		conn = util.open();
	}

	
	/**
	 * JDBC와 연결을 종료하는 메소드
	 */
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 
	 * @param aseq
	 * 로그인한 관리자의 seq 값 가져오기
	 * @return
	 * 가져온 seq값에 대한 관리자 id, pw 데이터 보내주기
	 * 
	 */
	public AdminDTO getAdminInfo(String aseq) {
		try {
			String sql = "select seq, id, pw from tbl_admin where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, aseq);
			rs = pstat.executeQuery();
			
			//ArrayList<AdminDTO> list= new ArrayList<>();
			
			if(rs.next()) {
				AdminDTO dto = new AdminDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				
				return dto;
			}
			//return list;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
