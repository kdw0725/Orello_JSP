<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/memberLeft.css">
<style>

</style>
<%@ include file="/inc/asset.jsp"%>
</head>
<body>
	<%@ include file="/inc/header.jsp"%>
	<section id="content" class="container">
	<%@ include file="/inc/member.jsp"%>
	
	</section>
	
	<%@ include file="/inc/footer.jsp"%>
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/memberLeft.js">
	
	
	</script>
</body>
</html>