package com.test.orello.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.orello.DBUtil;



public class BoardDAO {
	

		private Connection conn;
		private Statement stat;
		private PreparedStatement pstat;
		private ResultSet rs;

		public BoardDAO() {
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

	//List서블릿 -> 리스트 목록 출력
	public ArrayList<BoardDTO> list(HashMap<String, String> map) {

		try {
			
			String where = "";
			
			
			if(map.get("search") != null) {
				
				if(map.get("soption").equals("0")) {
					//all saerch
					where = String.format("and (name like '%%%s%%' " 
					+ "or title like '%%%s%%')" 
					, map.get("search"),map.get("search"));
					
				}else if(map.get("soption").equals("1")) {
					//title search
					where = String.format("and title like '%%%s%%'" 
							, map.get("search"));
					
				}else if(map.get("soption").equals("2")) {
					//name search
					where = String.format("and name like '%%%s%%' " 
							, map.get("search"));
					
				}else if(map.get("soption").equals("3")) {
					//content search
					
				}
				
				
			}
			
			
			
			String sql =  String.format("select *" + 
							"from (select a.*, rownum as rnum from" + 
							"(select * from vwboardlist where pseq = ? %s order by regdate desc) a)" + 
							"    where rnum >= ? and rnum <= ?", where);
					
			System.out.println(sql);
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("pseq"));
			pstat.setString(2, map.get("begin"));
			pstat.setString(3, map.get("end"));
			
			rs = pstat.executeQuery();
			
			ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setTitle(rs.getString("title"));
//				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcount(rs.getString("readcount"));
				dto.setName(rs.getString("name"));
				
				list.add(dto);
				
			}
			
			return list;
			
			
		} catch (Exception e) {

		System.out.println("BoardDAO.list()");
		e.printStackTrace();
		
		}
		
		
		return null;
	}

	
	//View서블릿 > 글 내용 보여주기
	public BoardDTO view(String seq) {

		try {
			
			String sql = "select b.seq as seq, b.title as title"
					+ ", b.content as content, b.regdate as regdate, b.readcount as readcount"
					+ ", m.name as name, m.email as email from tbl_freeboard b" + 
					"    inner join tbl_project_attend pa" + 
					"    on pa.seq = b.project_attend_seq" + 
					"        inner join tbl_member m" + 
					"        on m.seq = pa.member_seq" + 
					"            inner join tbl_project p" + 
					"            on p.seq = pa.project_seq" + 
					"                where b.seq = ? and "
					+ "b.delflag=0 and pa.delflag=0 and m.delflag=0 and p.delflag=0";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcount(rs.getString("readcount"));
				
				return dto;
			}
			
			
			
		} catch (Exception e) {
			System.out.println("BoardDAO.view()");
			e.printStackTrace();
		
		}
		
		
		
		return null;
	}

	
	//List 서블릿 -> 게시글 개수 가져오기
	public int getTotalCount(HashMap<String, String> map) {

		try {
			String sql = "";
					
			if(map.get("search") == null) {
			
			sql = "select count(*) as cnt from tbl_freeboard where project_seq = ? and delflag = 0";
			
			}else {
				
				sql = String.format("select count(*) as cnt " + 
						"from" + 
						"(select a.*, rownum as rnum from" + 
						"(select * from vwboardlist where pseq =? order by regdate desc) a)" + 
						"where (name like '%%%s%%' or title like '%%%s%%')"
							, map.get("search")
							, map.get("search"));
				
				System.out.println(sql);
			}
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("pseq"));

			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				return rs.getInt("cnt");
			}
			
			
		} catch (Exception e) {

			System.out.println("BoardDAO.getTotalCount()");
		
		}
		
		
		return 0;
	}

	//Comment 서블릿 -> 댓글 내용 보여주기
	public ArrayList<CommentDTO> commentList(String bseq) {

		try {
			
			String sql = "select m.name as name, c.content as content, c.regdate as regdate, m.seq as mseq, m.email as email "
					+ "from tbl_freeboard_comment c" + 
					"            inner join tbl_project_attend pa" + 
					"            on c.project_attend_seq = pa.seq" + 
					"                inner join tbl_member m" + 
					"                on pa.member_seq = m.seq" + 
					"                    where c.freeboard_seq = ? "
					+ "and c.delflag =0 and pa.delflag=0 and m.delflag=0 "
					+ "order by regdate asc";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, bseq);
			
			rs = pstat.executeQuery();
			
			
			ArrayList<CommentDTO> clist = new ArrayList<CommentDTO>();
			
			while(rs.next()) {
				CommentDTO dto = new CommentDTO();
				
				dto.setMseq(rs.getString("mseq"));
				dto.setName(rs.getString("name"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setEmail(rs.getString("email"));
				
				clist.add(dto);
			}
			
			return clist;
			
		} catch (Exception e) {

			System.out.println("BoardDAO.commentList()");
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	//Comment 서블릿 -> 댓글 개수
	public String commentCount(String bseq) {

		try {
			
			String sql = "select count(*) as cnt from tbl_freeboard_comment where freeboard_seq = ? and delflag = 0 ";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, bseq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				return rs.getString("cnt");
			}
			
		} catch (Exception e) {

			System.out.println("BoardDAO.commentCount");
			e.printStackTrace();
		}
		
		return null;
	}

	//Comment 서블릿 -> 참여 번호 얻어오기
	private String getAttendSeq(String mseq, String pseq) {

		try {
			
			String sql = "select seq from tbl_project_attend "
					+ "where project_seq =? and member_seq =? and delflag = 0";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pseq);
			pstat.setString(2, mseq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				return rs.getString("seq");
			}
			
		} catch (Exception e) {
			
			System.out.println("BoardDAO.getAttendSeq()");
			e.printStackTrace();
		
		}
		
		return null;
	}


	public int writeComment(CommentDTO cdto) {
		try {
			
			String sql = "insert into tbl_freeboard_comment "
					+ "values (seq_freeboard_comment.nextVal,?,default,?,?,0)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cdto.getContent());
			pstat.setString(2, cdto.getBseq());
			pstat.setString(3, getAttendSeq(cdto.getMseq(), cdto.getPsaq()));
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {

			System.out.println("BoardDAO.writeComment()");
			e.printStackTrace();
		}
		
		return 0;
		
	}

	
	
	//board write 서블릿 -> 글쓰기
	public int write(BoardDTO dto) {

		try {
			
			String sql = "insert into tbl_freeboard values(seq_freeboard.nextVal, ?, ?, default, 0, ?, ?, 0)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getTitle());
			pstat.setString(1, dto.getContent());
			pstat.setString(1, dto.getPseq());
			pstat.setString(1, getAttendSeq(dto.getMseq(), dto.getPseq()));
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {

			System.out.println("BoardDAO.writer()");
			e.printStackTrace();
			
		
		}
		
		
		return 0;
	}

}
