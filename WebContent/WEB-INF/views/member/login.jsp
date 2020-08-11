<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<style>

</style>
<%@ include file="/inc/asset.jsp"%>
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
        <input type="text" class="form-control" placeholder="아이디 (이메일)" />
        <input type="password" class="form-control" placeholder="비밀번호" />
        <input
            type="button"
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
            <button
                class="btn-social-login"
                style="background: #1fc700;"
                title="네이버 계정으로 로그인"
            >
                <i class="xi-2x xi-naver"></i>
            </button>
            <button
                class="btn-social-login"
                style="background: #ffeb00;"
                title="카카오 계정으로 로그인"
            >
                <i class="xi-2x xi-kakaotalk text-dark"></i>
            </button>
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
	</script>
</body>
</html>