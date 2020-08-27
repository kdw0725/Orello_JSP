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

		<div id="contentLeft">
			<div id="projectInfo">
				<div id="projectName"></div>
				<div id="projectPeriod">Manager Name</div>
				<div id="projectContent">lorem1234@gmail.com</div>
				<div id="projectMember"></div>
			</div>
			<div id="menu">
				<ul class="accordion">
					<div>
						<i class="glyphicon glyphicon-home"></i>Home
					</div>
				</ul>
				<ul class="accordion">
					<div onclick="location.href='/orello/aproject/projectlist.do';">
						<i class="glyphicon glyphicon-calendar"></i>Project
					</div>

				</ul>
				<ul class="accordion">
					<div>
						<i class="glyphicon glyphicon-check"></i>User
					</div>
				</ul>
				<ul class="accordion parent">
					<div>
						<i class="glyphicon glyphicon-list-alt"></i>Chart
					</div>
					<li class="panel">
						<div>
							<i class="glyphicon glyphicon-minus"></i>자료실(사용량)
						</div>
					</li>
					<li class="panel">
						<div>
							<i class="glyphicon glyphicon-minus"></i>포인트(결제)
						</div>
					</li>
					<li class="panel">
						<div>
							<i class="glyphicon glyphicon-minus"></i>프로젝트 신규
						</div>
					</li>
					<li class="panel">
						<div>
							<i class="glyphicon glyphicon-minus"></i>유저 신규
						</div>
					</li>
				</ul>
				<ul class="accordion">
					<div>
						<i class="glyphicon glyphicon-hdd"></i>QnA
					</div>
				</ul>

				<ul class="accordion">
					<div>
						<i class="glyphicon glyphicon-user"></i>FAQ
					</div>
				</ul>
				<ul class="accordion">
					<div>
						<i class="glyphicon glyphicon-user"></i>공지사항
					</div>
				</ul>


			</div>
		</div>
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
		//클릭한 메뉴만 표시되도록
		$(".list-group > a").click(function() {
			$(".list-group > a").removeClass("active");
			$(this).addClass("active");
		});

		//아코디언 메뉴
		var acc = document.getElementsByClassName("parent");
		for (var i = 0; i < acc.length; i++) {
			acc[i].addEventListener("click", function() {
				var li = $(this).children("li");
				li.slideToggle(1000);
				this.classList.toggle("active");
			});
		}
	</script>
n
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