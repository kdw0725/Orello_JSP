<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/memberLeft.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board_list.css">
<style>

</style>
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<section id="content" class="container">
	<%@ include file="/WEB-INF/views/inc/member.jsp"%>
	<div id="contentRight">
        <div id="main_list">
            <div id="main_title">
                <div id="titleicon"></div>
                <h1>Board List</h1>
            </div>
            <table id="tbl" class="table">
            <thead>
                <tr>
                    <th>no.</th>
                    <th>title</th>
                    <th>writer</th>
                    <th>date</th>
                    <th>view</th>
                </tr>
			</thead>
			
			<tbody>
				
			
				<c:forEach items="${list}" var="dto">
                <tr>
                    <td>${dto.seq}</td>
                    <td>
                    <c:if test="${empty search}">
                    <a href="/orello/board/view.do?seq=${dto.seq}&page=${page}">
                    ${dto.title}
                    </a>
                    </c:if>
                    
                    <c:if test="${not empty search}">
                    <a href="/orello/board/view.do?seq=${dto.seq}&page=${page}&search=${search}">
                    ${dto.title}
                    </a>
                    </c:if>
                    
                    
                    </td>
                    <td>${dto.name}</td>
                    <td>${dto.regdate}</td>
                    <td>${dto.readcount}</td>
                </tr>
                </c:forEach>
             </tbody> 
            </table>
            <!-- <hr style="border: 0.2px solid #bbb;" /> -->

            <button id="write" class="btn btn-info"
            	onclick="location.href='/orello/board/write.do'">
                <li class="glyphicon glyphicon-pencil"></li>
                write
            </button>
            <form method="GET" action="/orello/board/list.do" id="searchForm">
            <div id="search_select">
            
                <div id="select_title">
                    <select name="soption" id="soption" class="form-control"
                    onchange="$('#soption').submit()">
                        <option value="0">total</option>
                        <option value="1">title</option>
                        <option value="2">writer</option>
                        <option value="3">content</option>
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

            <!-- pagination -->
            
            ${pagebar}
            
        </div>
    </div>
	
	
	</section>
	
	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/memberLeft.js"></script>
	
	<script>
	
		
		$("#pagebar").val(${page});
		
		//alert(${soption});

		//console.log($("#soption option:nth-child(${soption+1})").val());
		//console.log($("#soption option:nth-child(${soption+1})"));
		
		$("#soption option:nth-child(${soption+1})").attr("selected",true);
		
		
	</script>
	
	
</body>
</html>