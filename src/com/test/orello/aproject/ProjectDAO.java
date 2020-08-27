package com.test.orello.aproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.orello.DBUtil;

public class ProjectDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public ProjectDAO() {
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

	// 프로젝트 리스트 가져오기 & 페이지 바 & 검색
	public ArrayList<ProjectDTO> projectlist(HashMap<String, String> map) {

		try {
			// 목록 or 검색
			String where = "";

			if (map.get("search") != null) { // 이름 & 제목 & 내용 - 포괄 검색
				System.out.println(map.get("search"));
				System.out.println(map.get("soption"));
				if(map.get("soption").equals("0")) {
				where = String.format("where (name like '%%%s%%') or (startdate like '%%%s%%') or (enddate like '%%%s%%')", map.get("search"), map.get("search"), map.get("search"));
				} else if(map.get("soption").equals("1")) {
					where = String.format("where (name like '%%%s%%')", map.get("search"));
				} else if(map.get("soption").equals("2")) {
					where = String.format("where (startdate like '%%%s%%')", map.get("search"));
				} else if(map.get("soption").equals("3")) {
					where = String.format("where (enddate like '%%%s%%')", map.get("search"));
				}
			}

			String sql = String.format(
					"select * from (select a.*, rownum as rnum from (select seq, name, startdate, enddate, regdate from tbl_project %s order by seq) a) where rnum >= %s and rnum <= %s",
					where, map.get("begin"), map.get("end"));

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<ProjectDTO> list = new ArrayList<ProjectDTO>();

			while (rs.next()) {
				ProjectDTO dto = new ProjectDTO();

				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setStartdate(rs.getString("startdate").substring(0, 10));
				dto.setEnddate(rs.getString("enddate").substring(0, 10));
				dto.setRegdate(rs.getString("regdate").substring(0, 10));

				list.add(dto);

			}

			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	// 프로젝트 서브페이지 데이터 받아오기
	public ProjectDTO projectsublist(String sort) {

		try {
			String sql = "select name, startdate, enddate, regdate from tbl_project where seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sort);

			rs = pstat.executeQuery();

			if (rs.next()) {
				ProjectDTO dto = new ProjectDTO();

				dto.setName(rs.getString("name"));
				dto.setStartdate(rs.getString("startdate").substring(0, 10));
				dto.setEnddate(rs.getString("enddate").substring(0, 10));
				dto.setRegdate(rs.getString("regdate").substring(0, 10));
				return dto;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	public int getTotalCount(HashMap<String, String> map) {
		try {

			String where = "";

			if (map.get("search") != null) { // 이름 & 제목 & 내용 - 포괄 검색
				if(map.get("soption") == "0") {
				where = String.format("where (name like '%s') or (startdate like '%s') or (enddate like '%s')", map.get("search"), map.get("search"), map.get("search"));
				} else if(map.get("soption") == "1") {
					where = String.format("where (name like '%s')", map.get("search"));
				} else if(map.get("soption") == "2") {
					where = String.format("where (startdate like '%s')", map.get("search"));
				} else if(map.get("soption") == "3") {
					where = String.format("where (enddate like '%s')", map.get("search"));
				}
			}

			String sql = String.format("select count(*) as cnt from tbl_project %s", where);

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			if (rs.next()) {
				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			System.out.println("BoardDAO.getTotalCount()");
			e.printStackTrace();
		}

		return 0;
	}

	// 해당 프로젝트에 참여한 멤버 정보 가져오기
	public ArrayList<ProjectDTO> memberlist(String sort) {
		try {
			String sql = "select m.name as name, (select enddate from tbl_project where seq = ?) as deadline, email, position from tbl_project_attend pa inner join tbl_member m on pa.member_seq = m.seq where project_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sort);
			pstat.setString(2, sort);

			rs = pstat.executeQuery();

			ArrayList<ProjectDTO> list = new ArrayList<ProjectDTO>();

			while (rs.next()) {
				ProjectDTO dto = new ProjectDTO();

				dto.setMemberName(rs.getString("name"));
				dto.setEnddate(rs.getString("deadline").substring(0, 10));
				dto.setEmail(rs.getString("email"));
				dto.setPosition(rs.getString("position"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// 해당 프로젝트의 리더 정보 가져오기
	public ProjectDTO memberLeader(String sort) {
		try {
			String sql = "select name, tel from tbl_project_attend pa inner join tbl_member m on pa.member_seq = m.seq where project_seq = ? and position = '팀장'";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sort);

			rs = pstat.executeQuery();

			if (rs.next()) {
				ProjectDTO dto = new ProjectDTO();

				dto.setLeadername(rs.getString("name"));
				dto.setLeaderemail(rs.getString("tel"));

				return dto;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// 멤버별 참여율의 멤버이름 계산하는 메소드
	public ArrayList<String> joinmemberlist(String seq) {
		try {
			String sql = "select name, a.* from (select member_seq, count(*) as cnt from tbl_archive a inner join tbl_project_attend pa on a.project_attend_seq = pa.seq where pa.project_seq = ? group by member_Seq ) a inner join tbl_member m on a.member_seq = m.seq where seq = member_seq order by name";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			ArrayList<String> namelist = new ArrayList<>();
	

			while (rs.next()) {
				// namelist : 권원용 백동혜 정용희 최수택 최희완 하봉경
				namelist.add("\"" + rs.getString("name") + "\"");

			}
			return namelist;


		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	
	//멤버별 참여율 데이터 계산하는 메소드
	public ArrayList<Integer> joinlist(String seq) {
		try {
			String sql = "select name, a.* from (select member_seq, count(*) as cnt from tbl_archive a inner join tbl_project_attend pa on a.project_attend_seq = pa.seq where pa.project_seq = ? group by member_Seq ) a inner join tbl_member m on a.member_seq = m.seq where seq = member_seq order by name";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			ArrayList<Integer> list1 = new ArrayList<>();

			while (rs.next()) {

				list1.add(rs.getInt("cnt"));
			}

			//System.out.println(list1 + " list1");
			
			String sql2 = "select name, a.* from (select member_seq, count(*) as cnt from tbl_commit_history ch inner join tbl_project_attend pa on ch.project_attend_seq = pa.seq where project_seq = ? group by member_seq order by member_Seq) a inner join tbl_member m on a.member_seq = m.seq where seq = member_seq order by name";

			pstat = conn.prepareStatement(sql2);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			ArrayList<Integer> list2 = new ArrayList<>();

			while (rs.next()) {
				list2.add(rs.getInt("cnt"));
			}

			//System.out.println(list2 + " list2");
			
			String sql3 = "select name, a.* from (select member_seq, count(*) as cnt from tbl_code_board cb inner join tbl_project_attend pa on cb.project_attend_seq = pa.seq where pa.project_seq = ? group by member_seq order by member_seq) a inner join tbl_member m on a.member_seq = m.seq where seq = member_seq order by name";

			pstat = conn.prepareStatement(sql3);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			ArrayList<Integer> list3 = new ArrayList<>();

			while (rs.next()) {
				list3.add(rs.getInt("cnt"));
			}
			
			//System.out.println(list3 + " list3");
			
			ArrayList<Integer> result = new ArrayList<>();
			for(int i=0; i<list1.size();i++) {
				int a = list1.get(i) + list2.get(i) + list3.get(i);
				
				result.add(a);
			}
			
			//System.out.println(result + " result");
			
			return result;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	
	//날짜별 활성화율 차트에서 날짜 리스트 출력 메소드
	public ArrayList<String> pDatelist(String seq) {
		try {
			String sql = "select regdate, count(*) as cnt from tbl_freeboard where project_seq = ? group by regdate order by regdate asc";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			
			ArrayList<String> list = new ArrayList<>();
			
			while(rs.next()) {
				list.add("\"" + rs.getString("regdate").substring(0,10) + "\"");
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	//날짜별 활성화율 전체 데이터 출력 메소드
	public ArrayList<Integer> pUselistTotal(String seq) {
		try {
			//자유게시판 데이터
			double totalCnt1 = 0;
			String sql2 = "select count(*) as cnt from tbl_freeboard where project_seq = ?";
			
			pstat = conn.prepareStatement(sql2);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				totalCnt1 = rs.getInt("cnt");
			}
			
			String sql1 = "select count(*) as cnt from tbl_freeboard where project_seq = ? group by regdate order by regdate asc";
			
			pstat = conn.prepareStatement(sql1);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<Integer> list1 = new ArrayList<>();
			
			while(rs.next()) {
				list1.add(rs.getInt("cnt"));
			}
			
			//자료실 데이터
			double totalCnt2 = 0;
			String sql3 = "select count(*) as cnt from tbl_archive where project_seq = ?";
			
			pstat = conn.prepareStatement(sql3);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				totalCnt2 = rs.getInt("cnt");
			}
			//System.out.println(totalCnt);
			
			String sql4 = "select count(*) as cnt from tbl_archive where project_seq = ? group by regdate order by regdate asc";
			
			pstat = conn.prepareStatement(sql4);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<Integer> list2 = new ArrayList<>();
			
			while(rs.next()) {
				list2.add(rs.getInt("cnt"));
			}
			
			//두 데이터 값 합치는 부분
			ArrayList<Integer> result = new ArrayList<>();
			
			for(int i=0;i<list1.size();i++) {
				int a = (int)((list1.get(i) + list2.get(i))/(totalCnt1+totalCnt2)*100);
				//System.out.println(a);
				result.add(a);
			}
			
			return result;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	//날짜별 활성화율 자유게시판 데이터 메소드
	public ArrayList<Integer> pUselistBoard(String seq) {
		try {
			double totalCnt = 0;
			String sql2 = "select count(*) as cnt from tbl_freeboard where project_seq = ?";
			
			pstat = conn.prepareStatement(sql2);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				totalCnt = rs.getInt("cnt");
			}
			//System.out.println(totalCnt);
			
			String sql = "select count(*) as cnt from tbl_freeboard where project_seq = ? group by regdate order by regdate asc";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<Integer> list = new ArrayList<>();
			
			while(rs.next()) {
				list.add((int)(rs.getInt("cnt")/totalCnt*100));
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	//날짜별 활성화율 자료실 데이터 메소드
	public ArrayList<Integer> pUselistArchive(String seq) {
		try {
			double totalCnt = 0;
			String sql2 = "select count(*) as cnt from tbl_archive where project_seq = ?";
			
			pstat = conn.prepareStatement(sql2);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				totalCnt = rs.getInt("cnt");
			}
			//System.out.println(totalCnt);
			
			String sql = "select count(*) as cnt from tbl_archive where project_seq = ? group by regdate order by regdate asc";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<Integer> list = new ArrayList<>();
			
			while(rs.next()) {
				list.add((int)(rs.getInt("cnt")/totalCnt*100));
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
