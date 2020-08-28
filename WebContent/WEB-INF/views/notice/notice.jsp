<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<%-- <link rel="stylesheet" href="<%= request.getContextPath() %>/css/notice.css"> --%>
<style>
#main_list {
	width: 800px;
	margin-top: 30px;
	margin-left: 50px;
	border-bottom: 1px solid #ddd;
}

#main_title {
	font-size: 30px;
	font-weight: bold;
	color: #444;
}

#tbl {
	margin-top: 10px;
	border: none;
}

#tbl tr {
	color: #444;
}

#tbl tr th {
	font-size: 15px;
	text-align: center;
	border: none;
	border-bottom: 1px solid #eee;
	border-top: 1px solid #ddd;
	background-color: #5bc0de;
	color: white;
}

#tbl td {
	border: none;
	border-bottom: 1px solid #eee;
	/* height: 30px; */
	padding: 10px;
	text-align: center;
	font-size: 15px;
	padding-top: 8px;
	/* padding-left: 10px; */
}

/* #tbl tr:first-child:hover {
	cursor: auto;
} */

#tbl tr:hover {
	background-color: rgb(216, 235, 252);
	cursor: pointer;
}

#tbl td:first-child {
	font-size: 11px;
	font-weight: bold;
}

#tbl td:nth-child(2) {
	text-align: center;
}

#write {
	float: right;
	margin-bottom: 20px;
}

#search {
	/* border: 1px solid black; */
	text-align: center;
	margin-left: 250px;
	/* margin-top: 70px; */
	height: 30px;
	width: 350px;
}

#search>input.form-control {
	outline: none;
	display: block;
	float: left;
	width: 200px;
	height: 30px;
	margin-right: 10px;
}

#select_title>select.form-control {
	outline: none;
	display: block;
	float: left;
	width: 80px;
	height: 30px;
	margin-right: 10px;
}

#search button {
	display: block;
	/* float: right; */
	float: left;
	height: 30px;

	/* margin-top: 20px; */
}

#paging {
	margin-top: 0px;
	text-align: center;
}

#paging nav {
	height: 80px;
}

.pagination {
	height: 60px;
	margin-top: 25px;
	margin-left: 100px;
}

.pagination>li>a {
	padding-top: 9px;
	width: 36px;
	border: none;
	color: #444;
}

.pagination>li:first-child>a {
	padding-top: 8px;
	width: 35px;
}

.pagination>li:last-child>a {
	padding-top: 8px;
	width: 35px;
}

.pagination>li>a:hover {
	color: white;
	background-color: #ccc;
}

.pagination>.active>a {
	background-color: #5bc0de;
	color: white;
}

.pagination>.active>a:hover {
	background-color: #5bc0de;
}

.btn {
	outline: none;
}
</style>
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<section id="content" class="container">
	
		<div id="main_list">
            <div id="main_title">
                <li class="glyphicon glyphicon-th-list"></li>
                Board List
            </div>
            <table id="tbl" class="table">
                <tr>
                    <th>No</th>
                    <th>title</th>
                    <th>Content</th>
                    <th>Regdate</th>
                </tr>

				<c:forEach items="${nList}" var="dto">
                <tr>
                    <td>${dto.nseq}</td>
                    <td>${dto.title}</td>
                    <td>admin${dto.writer}</td>
                    <td>${(dto.regdate).substring(0, 10)}</td>
                </tr>
                </c:forEach>
            </table>


            <button id="write" class="btn btn-info">
                <li class="glyphicon glyphicon-pencil"></li>
                write
            </button>
            <form method="GET" action="/orello/notice/notice.do" id="searchForm">
            	<div id="search_select">
            
                <div id="select_title">
                    <select name="soption" id="soption" class="form-control"
                    onchange="$('#soption').submit()">
                        <option value="0">title</option>
                        <option value="1">writer</option>
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
	</section>
	
	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
	<script>
	$("#pagebar").val($(page));
	
	$("#soption option:nth-child(${soption+1})").attr("selected",true);
	</script>
</body>
</html>