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
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/version.css">

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/docs.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/codemirror.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/show-hint.css" />

<script src="<%= request.getContextPath() %>/js/codemirror.js"></script>
<script src="<%= request.getContextPath() %>/js/show-hint.js"></script>
<script src="<%= request.getContextPath() %>/js/css-hint.js"></script>
<script src="<%= request.getContextPath() %>/js/xml-hint.js"></script>
<script src="<%= request.getContextPath() %>/js/html-hint.js"></script>
<script src="<%= request.getContextPath() %>/js/javascript-hint.js"></script>
<script src="<%= request.getContextPath() %>/js/xml.js"></script>
<script src="<%= request.getContextPath() %>/js/javascript.js"></script>
<script src="<%= request.getContextPath() %>/js/css.js"></script>
<script src="<%= request.getContextPath() %>/js/htmlmixed.js"></script>
<script src="<%= request.getContextPath() %>/js/python.js"></script>
<script src="<%= request.getContextPath() %>/js/markdown.js"></script>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/jquery-ui.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/zTreeStyle.css" />

</head>
<body>
	<%@ include file="/inc/header.jsp"%>
	<section id="content" class="container">
		<%@ include file="/inc/member.jsp"%>
	    <div id="contentRight">
	        <div id="projectName">
	            <h3><i class="fas fa-code-branch"></i> Orello</h3>
	        </div>
	        <div id="fileArea">
	            <button
	                type="button"
	                class="btn btn-default"
	                title="새 파일"
	                id="newFile"
	            >
	                <i class="fa fa-file-o"></i>
	            </button>
	            <button type="button" class="btn btn-default" title="새 폴더">
	                <i class="far fa-folder"></i>
	            </button>
	            <button type="button" class="btn btn-default" title="새로고침">
	                <i class="fas fa-sync-alt"></i>
	            </button>
	            <button type="button" class="btn btn-default" title="삭제">
	                <i class="far fa-trash-alt"></i>
	            </button>
	            <div id="fileList">
	                <ul id="treeDemo" class="ztree"></ul>
	            </div>
	            <h4>Commit History</h4>
	            <div id="commitHistory"></div>
	        </div>
	
	        <div id="codeArea">
	            <ul>
	                <li>
	                    <a href="#tabs-1">Lorem, ipsum.</a>
	                    <span><i class="fas fa-times"></i></span>
	                </li>
	            </ul>
	            <div id="tabs-1">
	                <div id="code"></div>
	                <!-- <textarea cols="67" rows="22"></textarea> -->
	                <input
	                    type="text"
	                    class="form-control"
	                    placeholder="commit할 메시지 입력"
	                />
	                <button class="btn btn-info">
	                    <i class="fas fa-arrow-right"></i>
	                </button>
	            </div>
	        </div>
	    </div>
	</section>
	
	<%@ include file="/inc/footer.jsp"%>
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="<%= request.getContextPath() %>/js/jquery-ui.js"></script>
	<script src="<%=request.getContextPath()%>/js/memberLeft.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.ztree.core.js"></script>
	<script src="<%=request.getContextPath()%>/js/version.js"></script>
</body>
</html>