<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<style>

</style>
<%@ include file="/inc/asset.jsp"%>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/logIn.css">
<link
    rel="stylesheet"
    href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css"
/>
</head>
<body>
	<%@ include file="/inc/header.jsp"%>
	<section id="content" class="container">
		<div id="inputArea">
        <img src="<%= request.getContextPath() %>/images/Logo-big.png" alt="Logo" id="logoBig" />
        <form action="/orello/member/loginok.do" method="POST">
	        <input type="text" class="form-control" placeholder="아이디 (이메일)" name="email" />
	        <input type="password" class="form-control" placeholder="비밀번호" name="pw" />
	        <input 
	            type="submit"
	            class="btn btn-info btn-logIn"
	            value="로그인"
	            id="logIn"
	        />
	        <input
	            type="button"
	            class="btn btn-default"
	            value="회원가입"
	            id="signIn"
	        />
        </form>
        <hr />
        <div id="findArea">
            <a href="#" id="findId">아이디 찾기</a>
            <a href="#" id="findPw">비밀번호 찾기</a>
        </div>
        <div id="socialLogIn">
            <button
                class="btn-social-login"
                style="background: #d93025;"
                title="구글 계정으로 로그인"
            >
                <i class="xi-2x xi-google"></i>
            </button>
			<a title="네이버 아이디로 로그인" onclick="window.open('${naverApiURL}','네이버 아이디로 로그인','width=500, height=700, toolbar=no, menubar=no, location=no, status=no, scrollbars=no')">
				<img height="40" src="/orello/images/naver.PNG" />
			</a> 
			<a title="카카오 아이디로 로그인" href="javascript:loginWithKakao()">
           		<img height="40" src="/orello/images/kakao.png">
           	</a>
           	
        </div>
    </div>
    <div
        class="modal fade"
        id="idFind"
        tabindex="-1"
        role="dialog"
        aria-labelledby="idFindMoal"
        aria-hidden="true"
    >
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h3
                        class="modal-title"
                        id="idFindMoal"
                        style="display: inline-block;"
                    >
                        아이디 찾기
                    </h3>
                    <button
                        type="button"
                        class="close"
                        data-dismiss="modal"
                        aria-label="Close"
                    >
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p>가입하실때 사용한 계정 정보를 입력해주시기 바랍니다.</p>
                    회원이름 :
                    <input
                        type="text"
                        class="form-control"
                        style="
                            width: 40%;
                            display: inline-block;
                            margin-bottom: 10px;
                        "
                    />
                    <br />
                    전화번호 :
                    <input
                        type="tel"
                        class="form-control"
                        style="width: 40%; display: inline-block;"
                    />
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-info">
                        ID 찾기
                    </button>
                    <button
                        type="button"
                        class="btn btn-secondary"
                        data-dismiss="modal"
                    >
                        취소
                    </button>
                </div>
            </div>
        </div>
    </div>
    <div
        class="modal fade"
        id="pwFind"
        tabindex="-1"
        role="dialog"
        aria-labelledby="pwFindModal"
        aria-hidden="true"
    >
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h3
                        class="modal-title"
                        id="pwFindModal"
                        style="display: inline-block;"
                    >
                        비밀번호 찾기
                    </h3>
                    <button
                        type="button"
                        class="close"
                        data-dismiss="modal"
                        aria-label="Close"
                    >
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p>가입하실때 사용한 계정 정보를 입력해주시기 바랍니다.</p>
                    회원이름 :
                    <input
                        type="text"
                        class="form-control"
                        style="
                            width: 40%;
                            display: inline-block;
                            margin-bottom: 10px;
                        "
                    />
                    <br />
                    ID&nbsp;(메일) :
                    <input
                        type="text"
                        class="form-control"
                        style="
                            width: 40%;
                            display: inline-block;
                            margin-bottom: 10px;
                        "
                    />
                    <br />
                    전화번호 :
                    <input
                        type="tel"
                        class="form-control"
                        style="width: 40%; display: inline-block;"
                    />
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-info">
                        비밀번호 찾기
                    </button>
                    <button
                        type="button"
                        class="btn btn-secondary"
                        data-dismiss="modal"
                    >
                        취소
                    </button>
                </div>
            </div>
        </div>
    </div>
	</section>
	
	<%@ include file="/inc/footer.jsp"%>
	<script>
		$("#findId").click(function() {
			$("#idFind").modal("show");
		});
		$("#findPw").click(function() {
			$("#pwFind").modal("show");
		});
		
		Kakao.init('b4f0786dfb7b457b31c6d0ff1df873f0');
		
		function loginWithKakao(){
			Kakao.Auth.login({
				success: function(authObj){
					Kakao.API.request({
						url : '/v2/user/me',
						success: function(res) {
							alert(authObj.access_token);
							alert(res.id);
							var temp = Object.assign(authObj, res);
							alert(JSON.stringify(temp));
							
							
							$.ajax({
								type : "POST",
								url : "/orello/member/kakaologin.do",
								data : temp,
								dataType : "JSON",
								beforeSend: function(){
									alert("dfdf");
								},
								success : function(result){
									console.log(result)
								},
								error : function(a, b, c){
									console.log(a, b, c);
								}
							});
						},
						fail: function(error) {
						  alert('login success, but failed to request user information: ' +JSON.stringify(error));
						}
					})
				},
				fail: function(err){
					alert('failed to login: ' + JSON.stringify(err))
				}
			})
		}
	</script>
</body>
</html>