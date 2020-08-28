<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<style>
<%@ include file="/css/aproject/projectlist.css" %>
</style>


<%@ include file="/WEB-INF/views/inc/asset.jsp"%>
</head>
<body>
	<jsp:useBean id="now" class="java.util.Date"/>
	<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="nowdate"/>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	
	<section id="content" class="container">

		<%
		out.flush();
	    RequestDispatcher dheader = request.getRequestDispatcher("/inc/admin.do");
	    dheader.include(request, response);
	%>
		<div id="contentRight">
			<div id="main_list">
				<div id="main_title">
					<li class="glyphicon glyphicon-folder-open"></li> Project
				</div>

				<div id="hidden_container">
					<table id="tbl" class="table">
						<tr>
							<th>no.</th>
							<th>Project Name</th>
							<th>Start Date</th>
							<th>End Date</th>
							<th>State</th>
						</tr>

						<c:forEach items="${plist }" var="pdto">
						<c:if test="${empty searchtxt}">
							<tr
								onclick="location.href='/orello/aproject/projectlistsub.do?seq=${pdto.seq}&page=${page}';">
						</c:if>
						<c:if test="${not empty searchtxt}">
							<tr onclick="location.href='/orello/aproject/projectlistsub.do?seq=${pdto.seq}&page=${page}&searchtxt=${searchtxt}';">
						</c:if>
								<td>${pdto.seq }</td>
								<td>${pdto.name }</td>
								<td>${pdto.startdate }</td>
								<td>${pdto.enddate }</td>
								<td>
								<c:if test="${nowdate >= pdto.startdate }">
									<c:if test="${nowdate > pdto.enddate}">
										End
									</c:if>
									<c:if test="${nowdate <= pdto.enddate}">
										Ing
									</c:if>
								</c:if>
								<c:if test="${nowdate < pdto.startdate}">
									Yet
								</c:if>
								</td>
							</tr>
						</c:forEach>
					</table>
					<hr style="border: 0.2px solid #bbb;" />

				<form method="GET" action="/orello/aproject/projectlist.do" id="searchForm">
					<div id="search">	
 						<div id="select_title">
							<select name="soption" id="soption" class="form-control" onchange="$('#soption').submit()">
								<option value="0" selected="selected">Total</option>
								<option value="1">Project Name</option>
								<option value="2">Start Date</option>
								<option value="3">End Date</option>
							</select>
						</div> 
						<input type="text" class="form-control" name="searchtxt" id="searchtxt" required value="${searchtxt}"/>
						<button type="submit" class="btn btn-info" >
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</div>
				</form>
					

					<!-- pagination -->
					<div style="margin-left: 100px;">${pagebar }</div>
				</div>
			</div>
		</div>


	</section>

	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
	
	<script>
	
/* 	function movePage() {
		//alert(event.srcElement.value);
		location.href = "/orello/aproject/projectlist.do?page=" + event.srcElement.value;
	}
	 */
	$("#pagebar").val(${page});
	$("#soption option:nth-child(${soption+1})").attr("selected",true);
	</script>


</body>
</html>