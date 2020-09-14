package com.test.orello.checklist;

import java.io.IOException;
import java.io.PrintWriter;
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

/**
 * 체크리스트 항목 수정을 위한 서블릿입니다.
 * @author Doyun Lee
 *
 */
@WebServlet("/checklist/modifychecklistitemok.do")
public class ModifyChecklistItemOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 데이터 받아오기
		//2. DB작업(update)
		//3. 결과 반환
		
		String path = req.getRealPath("/files/checklist");
		int size = 1024 * 1024 * 100;	//100MB
		
		String title = "";
		String mseq = "";
		String startDate = "";
		String endDate = "";
		String content = "";
		String ciseq="";
		
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
			title = multi.getParameter("title");
			mseq = multi.getParameter("mseq");
			startDate = multi.getParameter("startDate");
			endDate = multi.getParameter("endDate");
			content = multi.getParameter("content");
			ciseq = multi.getParameter("ciseq");
			
			//첨부파일 여러개의 파일명 알아내기 
			Enumeration e = multi.getFileNames();	
			
			ArrayList<ChecklistAttachDTO> list = new ArrayList<ChecklistAttachDTO>();
			ChecklistAttachDTO adto = new ChecklistAttachDTO();
			
			adto.setCiseq(ciseq);
			
			adto.setRegdate(String.format("%tF %tT", Calendar.getInstance(), Calendar.getInstance()));
			
			while (e.hasMoreElements()) {
				String file = (String)e.nextElement();
				adto.setFilename(multi.getFilesystemName(file));
				adto.setOrgFilename(multi.getOriginalFileName(file));
				//filename.add(multi.getFilesystemName(file));
				//orgfilename.add(multi.getOriginalFileName(file));
				list.add(adto);
			}

			//DTO에 담아 넘겨주기
			ChecklistItemDTO dto = new ChecklistItemDTO();
			dto.setSeq(ciseq);
			dto.setTitle(title);
			dto.setMseq(mseq);
			dto.setContent(content);
			dto.setEnddate(endDate);
			dto.setStartdate(startDate);
			dto.setFilename(filename);
			dto.setOrgfilename(orgfilename);
			
			
			//2.
			ChecklistDAO dao = new ChecklistDAO();
			
			//담당자 참여번호 알아오기..
			String paseq = dao.getPaseq(mseq, ciseq);
			dto.setPaseq(paseq);
			
			//업데이트 실행
			int result = dao.modifyChecklistItem(dto);
			
			int fresult = -1;
			//파일 있는경우 업로드
			if (list.size() > 0) { 
				fresult = dao.addChecklistAttach(list);				
			}
			
			dao.close();
			
			//3.
			PrintWriter writer = resp.getWriter();
			if (result == 1) {
				
				if (fresult == list.size() || fresult == -1) {
					resp.sendRedirect("/orello/checklist/list.do");
				} else {
					writer.print("<html>");
					writer.print("<body>");
					writer.print("<script>");
					writer.print("alert('file upload failed'); history.back();");
					writer.print("</script>");
					writer.print("</body>");
					writer.print("</html>");
					writer.close();
				}
				
			} else {
				
				writer.print("<html>");
				writer.print("<body>");
				writer.print("<script>");
				writer.print("alert('failed'); history.back();");
				writer.print("</script>");
				writer.print("</body>");
				writer.print("</html>");
				writer.close();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
