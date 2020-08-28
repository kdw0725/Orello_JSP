<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<style>

</style>
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="380478341630-8p93j9l00a6tq6s1vlb7tnjlnqts1vj2.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>



<link rel="stylesheet" href="<%= request.getContextPath() %>/css/logIn.css">
<link
    rel="stylesheet"
    href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css"
/>
</head>
<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<section id="content" class="container">
		<div id="inputArea">
        <img src="<%= request.getContextPath() %>/images/Logo-big.png" alt="Logo" id="logoBig" />
        <form action="/orello/adminloginok.do" method="POST">
	        <input type="text" class="form-control" placeholder="아이디" name="id" />
	        <input type="password" class="form-control" placeholder="비밀번호" name="pw" />
	        <input 
	            type="submit"
	            class="btn btn-info btn-logIn"
	            value="로그인"
	            id="logIn"
	        />
        </form>
        <hr />
    </div>
    
	</section>
	
	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
	
</body>
</html>