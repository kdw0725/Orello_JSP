package com.test.orello.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.test.orello.checklist.ChecklistDTO;
import com.test.orello.checklist.ChecklistItemDTO;

@WebServlet("/project/index.do")
public class Index extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//회원번호 넘겨받았다고 가정
		//프로젝트 번호 넘겨받았다고 가정
		
		String mseq = "95";
		String pseq = "1";

		//DB에서 정보 받아오기
		ProjectDAO dao = new ProjectDAO();
		ArrayList<ChecklistDTO> list = dao.getChecklist(pseq);	//타임라인용
		list = dao.getChecklistItem(list);
		ArrayList<ChartDTO> clist = dao.getContribute(pseq);	//기여도 차트용
		HashMap<String, Integer> map = dao.getProcess(pseq);	//진행상황용
		
		dao.close();
		
		
		//타임라인 출력을 위해 정보 넘겨주기
		JSONArray arr = new JSONArray();
		/*
		[
			{
	            group: "화면설계",
	            data: 
	            [
	                {
	                    label: "메인",
	                    data: 
	                    [
	                        {
	                            timeRange: [new Date(2020,01,01), new Date(2020,02,01)],
	                            val: 1
	                        }
	                    ]
	                }
	            ]
	        }
        ]
		*/
		
		for(ChecklistDTO dto : list) {
			
			JSONObject obj = new JSONObject();
			obj.put("group", dto.getTitle());
			
			JSONArray dataArr = new JSONArray();
			
			for(ChecklistItemDTO idto : dto.getList()) {
			
				JSONObject dobj = new JSONObject();
				dobj.put("label", idto.getTitle());
				
				JSONArray darr = new JSONArray();
				
				JSONObject dataObject = new JSONObject();
				JSONArray tarr = new JSONArray();
				//tarr안에 new date 넣기..
				tarr.add(String.format("new Date(%s,%s,%s)", idto.getStartdate().substring(0,4)
															, idto.getStartdate().substring(5,7)
															, idto.getStartdate().substring(8,10)));
				tarr.add(String.format("new Date(%s,%s,%s)", idto.getEnddate().substring(0,4)
															, idto.getEnddate().substring(5,7)
															, idto.getEnddate().substring(8,10)));
				dataObject.put("timeRange", tarr);
				dataObject.put("val", idto.getName());
				
				darr.add(dataObject);
				
				dobj.put("data", darr);
				dataArr.add(dobj);
				
			}
			
			obj.put("data", dataArr);
			arr.add(obj);
		}
		
		//기여도 확인을 위해 정보 넘겨주기
		/*
			[{
	            name: 'Doyun Lee',
	            y: 22,
	            sliced: true,
	            selected: true
	        }]
		*/
		JSONArray chartArr = new JSONArray();
		for(ChartDTO cdto: clist) {
			
			JSONObject cobj = new JSONObject();
			cobj.put("name", cdto.getName());
			cobj.put("y", cdto.getData());
			chartArr.add(cobj);
		
		}
		//프로젝트 진행상황 넘겨주기
		
		
		
		//자료실 사용량 어떻게 받아오지..?

		
		req.setAttribute("arr", arr);
		req.setAttribute("chart", chartArr);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/project/index.jsp");
		dispatcher.forward(req, resp);
	
	}
	
	
}
