<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/adminLeft.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/userList.css">
<style>
#title {

	font-size: 2.5em;
	border-left: 10px solid #5bc0de;
	padding-left: 10px;
	margin-left: 50px;
	margin-top: 100px; 
}
#select_title {
	
	width: 100px;
}


</style>
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<section id="content" class="container">
		<%
		out.flush();
	    RequestDispatcher dheader = request.getRequestDispatcher("/inc/admin.do");
	    dheader.include(request, response);
	%>
		<div id="contentRight">
        <div id="titleContainer">
            
            <div id="title">User</div>
        </div>

        <table id="projectlist" class="table table-hover">
            <tr>
                <th>User Name</th>
                <th>Regdate</th>
                <th>Email</th>
                <th>Point</th>
            </tr>
            <c:forEach items="${list}" var="dto">
             <tr>
                 <td class="username"><a href="/orello/amember/userview.do?seq=${dto.seq}">${dto.name}</a></td>
                 <td class="age">${dto.regdate}</td>
				 <td class="nickname">${dto.email}</td>
                 <td class="point">${dto.point}</td>
             </tr>
            </c:forEach>
        </table>

        <div id="typecontainer">
           <!--  <select name="type" class="form-control" id="type">
                <option value="name">User Name</option>
                <option value="email">Email</option>
            </select> -->

            <!-- 검색창 -->
            <form method="GET" action="/orello/amember/UserList.do" id="searchForm">
            	<div id="search_select">
            
                <div id="select_title">
                    <select name="soption" id="soption" class="form-control"
                    onchange="$('#soption').submit()">
                        <option value="0">Name</option>
                        <option value="1">Email</option>
                    </select>
                </div>
                <input type="text" class="form-control" name="search" id="search"
                required value="${search}"/>
                <button type="button" class="btn btn-info"
                 onclick="$('#searchForm').submit();">
                    <i class="glyphicon glyphicon-search"></i>
                </button>
            </div>
            </form>
        </div>
		${pagebar}
    </div>
	</section>

	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/adminLeft.js">
	
	var index = 0;
	
	
	
	if ("${sort}" == "seq") {
		index = 0;
	}

	$(".fbtns").children().eq(index).addClass("active");
	
/* 	function movePage() {
		
		//alert(event.srcElement.value);
		location.href = "/orello/amember/UserList.do?page=" + event.srcElement.value;
	} */
	
	$("#pagebar").val($(page));
	
	$("#soption option:nth-child(${soption+1})").attr("selected",true);
	</script>
</body>
</html>