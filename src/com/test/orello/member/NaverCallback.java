package com.test.orello.member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

@WebServlet("/member/navercallback.do")
public class NaverCallback extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String clientId = "HMv69t53qZKJkFt2qZqb";
		String clientSecret = "BaFFR4Rayc";
		 String code = req.getParameter("code");
		    String state = req.getParameter("state");
		    String redirectURI = URLEncoder.encode("YOUR_CALLBACK_URL", "UTF-8");
		    String apiURL;
		    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		    apiURL += "client_id=" + clientId;
		    apiURL += "&client_secret=" + clientSecret;
		    apiURL += "&redirect_uri=" + redirectURI;
		    apiURL += "&code=" + code;
		    apiURL += "&state=" + state;
		    String accessToken = "";
		    String refresh_token = "";
		    try {
		        URL url = new URL(apiURL);
		        HttpURLConnection con = (HttpURLConnection)url.openConnection();
		        con.setRequestMethod("GET");
		        int responseCode = con.getResponseCode();
		        BufferedReader br;
		        if(responseCode==200) { // 정상 호출
		          br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		        } else {  // 에러 발생
		          br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		        }
		        String inputLine;
		        StringBuffer res = new StringBuffer();
		        while ((inputLine = br.readLine()) != null) {
		          res.append(inputLine);
		        }
		        br.close();
		        if(responseCode==200) {
		        	HttpSession session = req.getSession();
		        	
		        	session.setAttribute("naver_res", res.toString());
		        	
		        	Object objlist = JSONValue.parse(res.toString());
		        	JSONObject jsonObject = (JSONObject)objlist;
		        	
		        	accessToken = (String) jsonObject.get("access_token");
		        	String refreshToken = (String) jsonObject.get("refresh_token");
		        	String tokenType = (String) jsonObject.get("token_type");
		        	String expiresIn = (String) jsonObject.get("expires_in");
		        	
		        	HashMap<String, String> memberInfo = new HashMap<String, String>();
		        	memberInfo = getNaverProfile(accessToken);
		        	String name = memberInfo.get("name");
		        	String email = memberInfo.get("email");
		        	
		        	MemberDAO dao = new MemberDAO();
		        	MemberDTO dto = new MemberDTO();
		        	dto.setName(name);
		        	dto.setEmail(email);
		        	String rndtel = "";
		        	Random rnd = new Random();
		        	for (int i = 0; i < rnd.nextInt(4) + 5; i++) {
		        		rndtel += (char) (rnd.nextInt(26) + 97);
					}
					for (int i = 0; i < rnd.nextInt(10) + 3; i++) {
						rndtel += rnd.nextInt(10);
					}
		        	dto.setTel(rndtel);
		        	
		        	int signInResult = dao.signInCheck(email);
		        	if(signInResult == 0) {
		        		dao.naverSignIn(dto);
		        		System.out.println("회원가입 완료!");
		        	}
		        	dao.close();
		        	
		        	System.out.println("로그인 구현 완료!");
		        	
		        }
		      } catch (Exception e) {
		        System.out.println(e);
		      }
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/navercallback.jsp");
		dispatcher.forward(req, resp);
	}

	private HashMap<String, String> getNaverProfile(String accessToken) {
		String token = accessToken; // 네이버 로그인 접근 토큰;accessToken
        String header = "Bearer " + token; // Bearer 다음에 공백 추가

        String apiURL = "https://openapi.naver.com/v1/nid/me";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = get(apiURL,requestHeaders);

        //System.out.println(responseBody);
        
        Object objlist = JSONValue.parse(responseBody);
        JSONObject jsonObject = (JSONObject)objlist;
    	
    	String resultcode = (String) jsonObject.get("resultcode");
    	if(resultcode.equals("00")) {
    		HashMap<String, String> memberInfo = new HashMap<String, String>();
    		Object responseList = jsonObject.get("response");
    		
    		
//    		Object responselist = JSONValue.parse(response);
    		JSONObject jsonResponse = (JSONObject)responseList;
    		
    		memberInfo.put("email", (String) jsonResponse.get("email"));
    		memberInfo.put("name", (String) jsonResponse.get("name"));
    		
    		return memberInfo;
    				
    	} else {
    		return null;
    	}
	}
	
	private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

	
}
