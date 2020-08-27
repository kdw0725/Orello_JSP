package com.test.orello.checklist;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import org.json.simple.JSONArray;

import com.test.orello.DBUtil;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

public class ChecklistDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private PreparedStatement pstat2;
	private CallableStatement cstat;
	private ResultSet rs;
	private ResultSet rs2;
	private Random rnd;
	
	//생성자
	public ChecklistDAO() {
		//DB연결
		DBUtil util = new DBUtil();
		conn = util.open();
	}
	
	//DB종료
	public void close() {
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//프로젝트 번호(?) 넘겨받아 체크리스트 목록 출력하기
	public ArrayList<ChecklistDTO> getList(String pseq) {

		try {
			
			String sql = "select * from tbl_checklist where delflag = 0 and project_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pseq);
			
			rs = pstat.executeQuery();
			
			
			//체크리스트를 담아줄 ArrayList 생성하기
			ArrayList<ChecklistDTO> list = new ArrayList<ChecklistDTO>();
			
			//while문 안에서 돌릴 쿼리 작성
			String sql2 = "select i.*, (select name from tbl_member where a.member_seq = seq) as name, (select count(*) from tbl_checklist_item_comment where delflag = 0 and i.seq = checklist_item_seq) as ccnt, (select count(*) from tbl_checklist_item_attach where delflag = 0 and i.seq = checklist_item_seq) as acnt from tbl_checklist_item i inner join tbl_project_attend a on i.project_attend_seq = a.seq where i.delflag = 0 and i.checklist_seq = ?";
			pstat2 = conn.prepareStatement(sql2);
			
			while (rs.next()) {
				
				//일단 체크리스트에 정보 넣어주기
				ChecklistDTO dto = new ChecklistDTO();
				dto.setSeq(rs.getString("seq"));
				dto.setPseq(rs.getString("project_seq"));
				dto.setTitle(rs.getString("title"));
				
				//체크리스트 항목 넣어줄 ArrayList 만들기
				ArrayList<ChecklistItemDTO> ilist = new ArrayList<ChecklistItemDTO>();
				
				//체크리스트별로 체크리스트 항목 가져오기 
				pstat2.setString(1, rs.getString("seq"));
				rs2 = pstat2.executeQuery();
				
				
				while (rs2.next()) {
					
					//체크리스트 항목 한줄마다 dto 생성하여 정보 넣어주고 ArrayList에 넣기 
					ChecklistItemDTO idto = new ChecklistItemDTO();
					
					idto.setSeq(rs2.getString("seq"));
					idto.setCseq(rs2.getString("checklist_seq"));
					idto.setTitle(rs2.getString("title"));
					idto.setContent(rs2.getString("content"));
					idto.setStartdate(rs2.getString("startdate"));
					idto.setEnddate(rs2.getString("enddate"));
					idto.setPaseq(rs2.getString("project_attend_seq"));
					idto.setCompleteflag(rs2.getString("completeflag"));
					idto.setName(rs2.getString("name"));
					idto.setCommentcount(rs2.getInt("ccnt"));
					idto.setAttachcount(rs2.getInt("acnt"));
					
					ilist.add(idto);
				}//rs2
				
				//체크리스트 항목들 체크리스트 DTO에 넣어주기
				dto.setList(ilist);
				
				//arraylist에 체크리스트 넣어주기
				list.add(dto);
				
			}//rs
			
			rs2.close();
			pstat2.close();
			
			rs.close();
			pstat.close();
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//체크리스트 번호 넘겨받아 관련 항목들 delflag 1로 업데이트하기
	public int delChecklist(String cseq) {
		
		try {
		
			//체크리스트 지우기
			String sql = "update tbl_checklist set delflag=1 where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cseq);
			
			int result = pstat.executeUpdate();
			pstat.close();
			
			//체크리스트 항목 지우기
			sql = "update tbl_checklist_item set delflag=1 where checklist_seq = ?";
			pstat2 = conn.prepareStatement(sql);
			pstat2.setString(1, cseq);
			
			int result2 = pstat2.executeUpdate();
			pstat2.close();
			
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return 0;
	}

	public ChecklistDTO addChecklist(String pseq) {
		
		try {
			
			String sql = "insert into tbl_checklist(seq, title, delflag, project_seq) values (seq_checklist.nextVal, 'title', default, ?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pseq);
			
			int result = pstat.executeUpdate();
			pstat.close();
			
			if (result == 1) {
				
				//추가된 체크리스트 레코드의 seq 가져오기
				sql = "select max(seq) as seq from tbl_checklist where project_seq = ?";
				pstat2 = conn.prepareStatement(sql);
				pstat2.setString(1, pseq);
				rs = pstat2.executeQuery();
				
				if (rs.next()) {
					
					ChecklistDTO dto = new ChecklistDTO();
					dto.setSeq(rs.getString("seq"));
					dto.setPseq(pseq);
					dto.setTitle("title");
					dto.setDelflag("0");
					
					return dto;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<MemberDTO> getMemberList(String cseq) {

		try {
			
			String sql = "{call proc_project_member(?,?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, cseq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
			
			rs = (ResultSet)cstat.getObject(2);
						
			while (rs.next()) {
				
				MemberDTO dto = new MemberDTO();
				dto.setPaseq(rs.getString("paseq"));
				dto.setMseq(rs.getString("mseq"));
				dto.setName(rs.getString("name"));
				dto.setPosition(rs.getString("position"));
				dto.setProfilepic(rs.getString("profile_seq"));

				list.add(dto);
			}
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int addChecklistItem(ChecklistItemDTO dto) {
		
		try {
			
			String sql = "insert into tbl_checklist_item (seq, checklist_seq, project_attend_seq, title, content, startdate, enddate, regdate, completeflag, delflag) values (seq_checklist_item.nextVal, ?, ?, ?, ?, ?, ?, ?, default, default)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getCseq());
			pstat.setString(2, dto.getPaseq());
			pstat.setString(3, dto.getTitle());
			pstat.setString(4, dto.getContent());
			pstat.setString(5, dto.getStartdate());
			pstat.setString(6, dto.getEnddate());
			pstat.setString(7, dto.getRegdate());
			
			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public String getChecklistItemSeq() {
		
		try {

			String sql = "select max(seq) as seq from tbl_checklist_item";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				return rs.getString("seq");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int addChecklistAttach(ArrayList<ChecklistAttachDTO> list) {
		
		try {
			
			int count = 0;
			
			String sql = "insert into tbl_checklist_item_attach (seq, checklist_item_seq, filename, orgfilename, regdate, delflag) values (seq_checklist_item_attach.nextVal, ?, ?, ?, default, default)";
			pstat = conn.prepareStatement(sql);
			
			for (ChecklistAttachDTO dto : list) {
				
				pstat.setString(1, dto.getCiseq());
				pstat.setString(2, dto.getFilename());
				pstat.setString(3, dto.getOrgFilename());
				//pstat.setString(4, dto.getRegdate());
				count += pstat.executeUpdate();
			
			}
			
			return count;
			
			
		} catch (Exception e) {
			e.printStackTrace();		
		}
		
		return 0;
	}

	public ArrayList<MemberDTO> getSelectedMemberList(String ciseq) {
		
		try {
			
			String sql = "select seq, name, decode(profile_seq, null, 0, profile_seq) as profile_seq, (select count(*) from tbl_member m inner join tbl_project_attend a on m.seq = a.member_seq inner join tbl_checklist_item i on i.project_attend_seq = a.seq where m.seq = p.seq and i.seq = ?) as c from tbl_member p where seq in (select member_seq from tbl_project_attend where project_seq = (select project_seq from tbl_project_attend where seq = (select project_attend_seq from tbl_checklist_item where seq = ?)))";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, ciseq);
			pstat.setString(2, ciseq);
			
			rs = pstat.executeQuery();
			
			ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
			
			while(rs.next()) {
				
				MemberDTO dto = new MemberDTO();
				dto.setMseq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setProfilepic(rs.getString("profile_seq"));
				dto.setAssignFlag(rs.getString("c"));	//1이면 담당자
			
				list.add(dto);
			}
			
			return list;			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//체크리스트항목 번호 넘겨받아 제목, 기간, 내용 반환해주기
	public ChecklistItemDTO getChecklistItem(String ciseq) {

		try {
			
			String sql = "select seq, title, decode(content, null, ' ', content) as content, startdate, enddate from tbl_checklist_item where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, ciseq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				ChecklistItemDTO dto = new ChecklistItemDTO();
				dto.setSeq(rs.getString("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setStartdate(rs.getString("startdate").substring(0,10));
				dto.setEnddate(rs.getString("enddate").substring(0,10));
				
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<ChecklistCommentDTO> getCommentList(String ciseq) {

		try {
			
			String sql = "select c.*, m.name, decode(m.profile_seq, null, 0, m.profile_seq) as profile_seq from tbl_checklist_item_comment c inner join tbl_project_attend a on c.project_attend_seq = a.seq inner join tbl_member m on a.member_seq = m.seq where c.checklist_item_seq = ? and c.delflag = 0";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, ciseq);
			
			rs = pstat.executeQuery();
			
			ArrayList<ChecklistCommentDTO> list = new ArrayList<ChecklistCommentDTO>();
			
			while(rs.next()) {
				
				ChecklistCommentDTO dto = new ChecklistCommentDTO();
				dto.setSeq(rs.getString("seq"));
				dto.setCiseq(rs.getString("checklist_item_seq"));
				dto.setPaseq(rs.getString("project_attend_seq"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("name"));
				
				list.add(dto);
			}
			
			return list;		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<AttachmentDTO> getAttachList(String ciseq) {
	
		try {
			
			String sql = "select * from tbl_checklist_item_attach where delflag = 0 and checklist_item_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, ciseq);
			
			rs = pstat.executeQuery();
			
			ArrayList<AttachmentDTO> list = new ArrayList<AttachmentDTO>();
			
			while(rs.next()) {
				
				AttachmentDTO dto = new AttachmentDTO();
				dto.setSeq(rs.getString("seq"));
				dto.setCiseq(rs.getString("checklist_item_seq"));
				dto.setFilename(rs.getString("filename"));
				dto.setOrgfilename(rs.getString("orgfilename"));
				dto.setRegdate(rs.getString("regdate").substring(0,10));
			
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	public int addComment(ChecklistCommentDTO dto) {

		 try {
			 
			 Calendar now = Calendar.getInstance();
			 
			 String sql = "insert into tbl_checklist_item_comment (seq, checklist_item_seq, project_attend_seq, content, regdate, delflag) values (seq_checklist_item_comment.nextVal, ?, ?, ?, to_date(?, 'yyyy-mm-dd hh24:mi:ss'), default)";
			 pstat = conn.prepareStatement(sql);
			 pstat.setString(1, dto.getCiseq());
			 pstat.setString(2, dto.getPaseq());
			 pstat.setString(3, dto.getContent());
			 pstat.setString(4, String.format("%tF %tT", now, now));
			 
			 return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public ChecklistCommentDTO getNewComment(String ciseq) {
			
		try {
			
			String sql = "select c.*, m.name, decode(m.profile_seq, null, 0, m.profile_seq) as profile_seq from tbl_checklist_item_comment c inner join tbl_project_attend a on c.project_attend_seq = a.seq inner join tbl_member m on a.member_seq = m.seq where c.checklist_item_seq = ? and c.delflag = 0 and c.seq = (select max(seq) from tbl_checklist_item_comment where checklist_item_seq = ?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, ciseq);
			pstat.setString(2, ciseq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				ChecklistCommentDTO dto = new ChecklistCommentDTO();
				dto.setSeq(rs.getString("seq"));
				dto.setCiseq(rs.getString("checklist_item_seq"));
				dto.setPaseq(rs.getString("project_attend_seq"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("name"));
				
				return dto;		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int delChecklistItem(String ciseq) {

		try {
			
			String sql = "update tbl_checklist_item set delflag = 1 where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, ciseq);

			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public int modifyChecklistItem(ChecklistItemDTO dto) {

		try {
			
			String sql = "update tbl_checklist_item set title=?, content=?, startdate=?, enddate=?, project_attend_seq=? where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getContent());
			pstat.setString(3, dto.getStartdate());
			pstat.setString(4, dto.getEnddate());
			pstat.setString(5, dto.getPaseq());
			pstat.setString(6, dto.getSeq());
			
			return pstat.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public String getPaseq(String mseq, String ciseq) {

		try {
			
			String sql = "select seq from tbl_project_attend where project_seq = (select project_seq from tbl_checklist where seq = (select checklist_seq from tbl_checklist_item where seq = ?)) and member_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, ciseq);
			pstat.setString(2, mseq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				return rs.getString("seq");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int deleteAttach(String aseq) {

		try {
			
			String sql = "update tbl_checklist_item_attach set delflag = 1 where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, aseq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public int changeCheck(ChecklistItemDTO dto) {

		try {
			
			String sql = "update tbl_checklist_item set completeflag = ? where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getCompleteflag());
			pstat.setString(2, dto.getSeq());
			
			return pstat.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	
}
