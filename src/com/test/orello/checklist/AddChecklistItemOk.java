package com.test.orello.checklist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/checklist/addchecklistitemok.do")
public class AddChecklistItemOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	
		//1. 데이터 받아오기 (제목, 담당자, 내용, 파일, 체크리스트번호..?)
		//2. DB작업(insert)
		//3. 결과 반환 + jsp 호출
		
		String path = req.getRealPath("/files/checklist");
		int size = 1024 * 1024 * 100;	//100MB
		Calendar now = Calendar.getInstance();
		String title = "";
		String paseq = "";
		String content = "";
		String cseq= "";
		String startdate= "";
		String enddate= "";
		String regdate= String.format("%tF", now);
				
		ArrayList<String> filename = new ArrayList<String>();
		ArrayList<String> orgfilename = new ArrayList<String>();
		
		try {
			
			req.setCharacterEncoding("UTF-8");
			
			MultipartRequest multi = new MultipartRequest(req		
					, path		//파일 저장 경로
					, size		//최대 파일 크기
					, "UTF-8"	//인코딩 방식
					, new DefaultFileRenamePolicy()	//똑같은 파일이 있는 경우 파일에 넘버링을 해줌.
					);
			//1.	
			title = multi.getParameter("title");
			paseq = multi.getParameter("paseq");
			content = multi.getParameter("content");
			cseq = multi.getParameter("cseq");
			startdate = multi.getParameter("startdate");
			enddate = multi.getParameter("enddate");			
			
			
			//상자에 담기
			ChecklistItemDTO dto = new ChecklistItemDTO();
			dto.setCseq(cseq);
			dto.setPaseq(paseq);
			dto.setTitle(title);
			dto.setContent(content);
			dto.setStartdate(startdate);
			dto.setEnddate(enddate);
			dto.setRegdate(regdate);
			
			//2. 
			//체크리스트 항목 추가 
			ChecklistDAO dao = new ChecklistDAO();
			int result = dao.addChecklistItem(dto);
			
			//체크리스트 항목 추가에 성공했을 경우에만
			if (result > 0 ) {
				
				//추가한 체크리트 항목의 seq 가져오기
				String ciseq = dao.getChecklistItemSeq();
				
				
				//첨부파일 DB에 저장
				//첨부파일 여러개의 파일명 알아내기 
				ArrayList<ChecklistAttachDTO> list = new ArrayList<ChecklistAttachDTO>();
				
				Enumeration e = multi.getFileNames();	
				
				while (e.hasMoreElements()) {
					
					String file = (String)e.nextElement();	//input type=file인 것만 찾아줌. (다른 태그들처럼 개발자가 직접 name을 지정하여 찾지 않아도 됨.)
					
					//첨부파일 dto를 만들어 배열에 저장해줌
					ChecklistAttachDTO adto = new ChecklistAttachDTO();
					adto.setCiseq(ciseq);
					adto.setRegdate(regdate);
					adto.setFilename(multi.getFilesystemName(file));
					adto.setOrgFilename(multi.getOriginalFileName(file));
					
					list.add(adto);
				}

				if (list.size() > 0) { //첨부파일이 있을 때에만
					
					//첨부파일 DB작업 위임해주기
					int result2 = dao.addChecklistAttach(list);
				}
			
			}//if-체크리스트 항목 추가 성공시

			
			//트랜잭션 처리방법
			//1. 각 쿼리별로 insert문 따로 돌리기
			//2. 오토커밋 끄기 
			//3. 각각의 result값에 따라 결과 확인
			//4. 모두 완료된 경우만 commit하기
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//list 다시 출력하기 
		//resp.sendRedirect("/orello/checklist/list.do");
	}
	
}
