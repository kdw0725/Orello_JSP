<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<meta http-equiv="Content-Style-Type" content="text/css">

<title></title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/memberLeft.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board_write.css">
<style>

</style>
<style type="text/css">
	body { margin: 10px; }
</style>

<script type="text/javascript" src="/orello/js/jindo2.all.js" charset="utf-8"></script>
<script type="text/javascript" src="/orello/js/lib/jindo2.all.js" charset="utf-8"></script>
<script type="text/javascript" src="/orello/js/lib/jindo_component.js" charset="utf-8"></script>
<script type="text/javascript" src="/orello/js/service/SE2M_Configuration.js" charset="utf-8"></script>	<!-- 설정 파일 -->
<script type="text/javascript" src="/orello/js/service/SE2BasicCreator.js" charset="utf-8"></script>


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
	<%@ include file="/WEB-INF/views/inc/member.jsp"%>
	
	<div id="contentRight">
	
		<form method="POST" action="/orello/board/writeok.do">
		<div id="main"> 
            <div id="main_title">
                <div id="titleicon"></div>
                <h1>Write Content</h1>
            </div>
            <div id="board_set">
                <div id="select_type">
                    <span class="board_txt">Board</span>
                    <select name="" id="board_type" class="form-control">
                        <option value="0">Select Board Type</option>
                        <option value="1">Code Upload</option>
                        <option value="2">File Upload</option>
                        <option value="3">Notice</option>
                    </select>
                </div>
                <div id="title_set">
                    <span class="board_txt">Title</span
                    ><input type="text" class="form-control" name="title"/>
                </div>
            </div>
	 	<div class="body">
                <textarea
                    id="board_content"
                    name="contnet"
                    class="form-control"
                    style="resize: none"
                ></textarea>

                <button type="submit" id="sendBtn" class="btn btn-info">save</button>
            </div>
	</form>
	
	</div>
	</div>
	
	
	</section>
	
	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/memberLeft.js">
	
	</script>
	
</body>
</html>