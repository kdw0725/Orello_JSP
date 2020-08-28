<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/memberLeft.css">
<style>

</style>
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<section id="content" class="container">
	<%
		out.flush();
	    RequestDispatcher dheader = request.getRequestDispatcher("/inc/member.do?pseq=" + request.getAttribute("pseq"));
	    dheader.include(request, response);
	%>
	<div>삭제 완료!</div>
	<button type="button" onclick="location.href='/orello/board/list.do';">목록으로</button>
	
	</section>
	
	
	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/memberLeft.js">
	
	
	</script>
</body>
</html>