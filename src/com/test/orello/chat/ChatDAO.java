package com.test.orello.chat;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.test.orello.DBUtil;
import com.test.orello.checklist.MemberDTO;

/**
 * 채팅에 필요한 데이터베이스 조회, 수정, 삽입 작업에 관련된 메소드를 구현한 클래스입니다.
 * @author Doyun Lee
 *
 */
public class ChatDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	private Random rnd;
	
	/**
	 * 데이터베이스와 연결해주는 생성자 메소드입니다.
	 */
	public ChatDAO() {
		//DB연결
		DBUtil util = new DBUtil();
		conn = util.open();
	}
	
	/**
	 * 데이터베이스 연결을 끊는 메소드입니다.
	 */
	public void close() {
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 채팅창을 열어 로그인한 유저의 친구 목록을 가져와 반환하는 메소드입니다.
	 * @param mseq	로그인한 유저의 회원번호(기본키)
	 * @return	로그인한 유저의 친구 목록
	 */
	public ArrayList<MemberDTO> getFriendList(String mseq) {

		try {

			String sql = "select m.seq, m.name, decode((select filename from tbl_profile where seq = m.profile_seq), null, '0.png', (select filename from tbl_profile where seq = m.profile_seq)) as profile from tbl_friend f inner join tbl_member m on f.friend_seq = m.seq where f.delflag = 0 and f.member_seq =? order by m.name";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, mseq);
			
			rs = pstat.executeQuery();
			
			ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
			
			while(rs.next()) {
				
				MemberDTO dto = new MemberDTO();
				dto.setMseq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setProfilepic(rs.getString("profile"));
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 선택한 친구와의 대화 목록을 가져와 반환하는 메소드입니다.
	 * @param map	로그인한 유저의 회원번호와 선택한 친구의 회원번호
	 * @return 로그인한 유저와 선택한 친구의 대화 목록
	 */
	public ArrayList<MessageDTO> getChatList(HashMap<String, String> map) {

		try {
			
			String sql = "select l.*, ca.chat_seq, (select member_seq from tbl_chat_attend where l.chat_attend_seq = seq) as mseq from tbl_chat_log l inner join tbl_chat_attend ca on l.chat_attend_seq = ca.seq where chat_attend_seq in (select a.seq from tbl_chat_attend a inner join tbl_chat c on c.seq = a.chat_seq where c.project_seq is null and a.chat_seq in (select chat_seq from (select count(*) as cnt, chat_seq from tbl_chat_attend where member_seq in (?, ?) group by chat_seq) where cnt = 2) and chat_seq = (select min(chat_seq) as cseq from (select * from tbl_chat_attend a inner join tbl_chat c on c.seq = a.chat_seq where c.project_seq is null and a.chat_seq in (select chat_seq from (select count(*) as cnt, chat_seq from tbl_chat_attend where member_seq in (?, ?) group by chat_seq) where cnt = 2)))) order by regdate asc";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("mseq"));
			pstat.setString(2, map.get("fmseq"));
			pstat.setString(3, map.get("mseq"));
			pstat.setString(4, map.get("fmseq"));
			
			rs = pstat.executeQuery();
			
			ArrayList<MessageDTO> list = new ArrayList<MessageDTO>();

			while (rs.next()) {
			
				MessageDTO dto = new MessageDTO();
				dto.setSeq(rs.getString("seq"));
				dto.setMseq(rs.getString("mseq"));
				dto.setCseq(rs.getString("chat_seq"));
				dto.setCaseq(rs.getString("chat_attend_seq"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 채팅 내역 헤더 출력을 위해 선택한 유저의 정보를 가져와 반환하는 메소드입니다.
	 * @param fmseq 선택한 유저의 회원번호
	 * @return 선택한 유저의 정보
	 */
	public MemberDTO getMemberInfo(String fmseq) {

		try {
			
			String sql = "select m.*, decode((select filename from tbl_profile where seq = m.profile_seq), null, '0.png', (select filename from tbl_profile where seq = m.profile_seq)) as profile from tbl_member m  where m.delflag = 0 and m.seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, fmseq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
			
				MemberDTO dto = new MemberDTO();
				dto.setMseq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setProfilepic(rs.getString("profile"));
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 채팅의 프로젝트 탭 클릭 시 프로젝트 리스트와 채팅방 번호를 받아와 반환하는 메소드입니다.
	 * @param mseq	로그인한 유저의 회원번호
	 * @return	로그인한 유저가 참여한 프로젝트 리스트와 채팅방 번호
	 */
	public ArrayList<ChatRoomDTO> getProjectList(String mseq) {
		
		try {
			String sql = "select c.*, (select name from tbl_project where seq = c.project_seq) as name from tbl_chat c where seq in (select chat_seq from tbl_chat_attend where member_seq = ?) and project_seq is not null";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, mseq);
			rs = pstat.executeQuery();
			
			ArrayList<ChatRoomDTO> list = new ArrayList<ChatRoomDTO>();
			
			while(rs.next()) {
				ChatRoomDTO dto = new ChatRoomDTO();
				dto.setSeq(rs.getString("seq"));
				dto.setPseq(rs.getString("project_seq"));
				dto.setName(rs.getString("name"));
				list.add(dto);
			}
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 프로젝트의 참여 멤버를 썸네일로 출력해주기 위해 프로젝트 참여 멤버를 받아와 반환하는 메소드입니다.
	 * @param list	로그인한 유저가 참여한 프로젝트 채팅방 목록
	 * @return	로그인한 유저가 참여한 프로젝트 채팅방의 참여 멤버 목록
	 */
	public ArrayList<ChatRoomDTO> getChatMember(ArrayList<ChatRoomDTO> list) {

		try {
			
			String sql = "select a.project_seq, m.name, m.seq as mseq, decode((select filename from tbl_profile where m.profile_seq = seq), null, '0.png', (select filename from tbl_profile where m.profile_seq = seq)) as profile from tbl_project_attend a inner join tbl_member m on a.member_seq = m.seq where a.project_seq = ?";
			pstat = conn.prepareStatement(sql);
			
			for(ChatRoomDTO dto : list) {
				
				pstat.setString(1, dto.getPseq());
				rs = pstat.executeQuery();
				
				ArrayList<MemberDTO> mlist = new ArrayList<MemberDTO>();
				
				while(rs.next()) {
					MemberDTO mdto = new MemberDTO();
					mdto.setMseq(rs.getString("mseq"));
					mdto.setName(rs.getString("name"));
					mdto.setProfilepic(rs.getString("profile"));
					mlist.add(mdto);
				}
				
				dto.setList(mlist);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 채팅내역 헤더 출력을 위해 선택한 프로젝트 채팅방의 정보를 받아와 반환하는 메소드입니다.
	 * @param pseq 선택한 프로젝트의 프로젝트 번호
	 * @return	선택한 프로젝트의 채팅방 정보
	 */
	public ChatRoomDTO getMultiChatInfo(String pseq) {

		try {
			
			String sql = "select p.*, c.seq as cseq from tbl_project p inner join tbl_chat c on p.seq = c.project_seq where p.seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pseq);
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				ChatRoomDTO dto = new ChatRoomDTO();
				dto.setSeq(rs.getString("cseq"));
				dto.setPseq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<MemberDTO> getMultiChatMember(String pseq) {
		
		try {

			String sql = "select m.*, decode((select filename from tbl_profile where m.profile_seq = seq), null, '0.png', (select filename from tbl_profile where m.profile_seq = seq)) as profile from tbl_chat_attend a inner join tbl_member m on a.member_seq = m.seq where a.chat_seq = (select seq from tbl_chat where project_seq = ?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pseq);
			
			rs = pstat.executeQuery();
			ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setMseq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setProfilepic(rs.getString("profile"));
				list.add(dto);
			}
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<MessageDTO> getMultiChatList(HashMap<String, String> map) {

		try {
			
			String sql = "select a.chat_seq, a.member_seq, (select name from tbl_member where a.member_seq = seq) as name, l.* from tbl_chat_attend a inner join tbl_chat_log l on a.seq = l.chat_attend_seq where a.chat_seq = (select seq from tbl_chat where project_seq = ?) order by l.regdate";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("pseq"));
			rs = pstat.executeQuery();
			
			ArrayList<MessageDTO> list = new ArrayList<MessageDTO>();
			while(rs.next()) {

				MessageDTO dto = new MessageDTO();
				dto.setCaseq(rs.getString("chat_seq"));
				dto.setMseq(rs.getString("member_seq"));
				dto.setCaseq(rs.getString("chat_attend_seq"));
				dto.setName(rs.getString("name"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setMyflag(rs.getString("member_seq").equals(map.get("mseq")) ? "1" : "0");
				
				list.add(dto);
			}
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public String getCaseq(HashMap<String, String> map) {

		try {
			String sql = "select * from tbl_chat_attend where member_seq = ? and chat_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("mseq"));
			pstat.setString(2, map.get("cseq"));
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				return rs.getString("seq");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int addChatLog(MessageDTO dto) {

		try {
			String sql = "insert into tbl_chat_log (seq, content, regdate, delflag, chat_attend_seq) values (seq_chat_log.nextVal, ?, default, default, ?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getContent());
			pstat.setString(2, dto.getCaseq());
			
			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
}
