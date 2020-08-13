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
<%@ include file="/inc/asset.jsp"%>
</head>
<body>
	<%@ include file="/inc/header.jsp"%>
	<section id="content" class="container">
	<%@ include file="/inc/member.jsp"%>
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
                    <a href="/orello/board/view.do?seq=${dto.seq}">
                    ${dto.title}
                    </a>
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
            <div id="search">
                <div id="select_title">
                    <select name="" id="" class="form-control">
                        <option value="0">total</option>
                        <option value="1">title</option>
                        <option value="2">writer</option>
                        <option value="3">date</option>
                    </select>
                </div>
                <input type="text" class="form-control" />
                <button class="btn btn-info">
                    <i class="glyphicon glyphicon-search"></i>
                </button>
            </div>

            <!-- pagination -->
            <div id="paging">
                <nav>
                    <ul class="pagination">
                        <li>
                            <a href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <li class="active"><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li>
                            <a href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
	
	
	</section>
	
	<%@ include file="/inc/footer.jsp"%>
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/memberLeft.js">
	
	
	</script>
</body>
</html>