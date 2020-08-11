<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<style>

</style>
<%@ include file="/inc/asset.jsp"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/signIn.css">
</head>
<body>
	<%@ include file="/inc/header.jsp"%>
	<section id="content" class="container">
		<div class="imgContainer">
        <img src="../images/Logo-wide.jpg" alt="Logo" id="logoBig" />
	    </div>
	    <div id="contentLeft">
	        <strong>이메일<span>&nbsp;*</span></strong>
	        <strong>비밀번호<span>&nbsp;*</span></strong>
	        <strong>비밀번호 확인<span>&nbsp;*</span></strong>
	        <strong>전화번호<span>&nbsp;*</span></strong>
	        <strong>프로필 사진</strong>
	        <strong>소속</strong>
	    </div>
	    <div id="contentRight">
	        <div id="inputArea">
	            <form action="#" method="POST">
	                <input
	                    type="email"
	                    placeholder="이메일"
	                    class="form-control"
	                    required
	                />
	                <input
	                    type="password"
	                    placeholder="비밀번호"
	                    class="form-control"
	                    id="pw"
	                    required
	                />
	                <input
	                    type="password"
	                    placeholder="비밀번호 확인"
	                    class="form-control"
	                    id="pwCheck"
	                    required
	                />
	                <input
	                    type="tel"
	                    class="form-control"
	                    placeholder="전화번호"
	                    required
	                />
	                <div id="fileBox">
	                    <label for="ex_file" class="btn btn-default"
	                        >파일 선택</label
	                    >
	                    <input
	                        class="form-control"
	                        disabled="disabled"
	                        placeholder="선택된 파일 없음"
	                    />
	                    <input
	                        style="width: 100%;"
	                        type="file"
	                        class="upload-hidden"
	                        id="ex_file"
	                        accept="image/*"
	                        name="profile"
	                    />
	                </div>
	                <input
	                    type="text"
	                    class="form-control"
	                    placeholder="소속(회사)"
	                    required
	                />
	
	                <input type="checkbox" id="agree" />
	                <label for="agree"
	                    ><a href="#">이용약관</a>을 확인하였고 이에
	                    동의합니다.</label
	                >
	                <div id="submitArea">
	                    <input
	                        type="button"
	                        value="회원가입"
	                        class="btn btn-info"
	                        id="submit"
	                        data-toggle="modal"
	                        data-target="emailCheck"
	                    />
	                    <input
	                        type="button"
	                        value="취소"
	                        class="btn btn-default"
	                        data-toggle="modal"
	                    />
	                </div>
	            </form>
	        </div>
	    </div>
	    <div
	        class="modal fade"
	        id="emailCheck"
	        tabindex="-1"
	        role="dialog"
	        aria-labelledby="emailCheckModal"
	        aria-hidden="true"
	    >
	        <div class="modal-dialog" role="document">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <h3
	                        class="modal-title"
	                        id="emailCheckModal"
	                        style="display: inline-block;"
	                    >
	                        이메일 인증
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
	                    <p>입력하신 메일로 이메일을 발송해드렸습니다.</p>
	                    인증번호 :
	                    <input
	                        type="text"
	                        class="form-control"
	                        style="width: 40%; display: inline-block;"
	                    />
	                    <strong> &nbsp;03:00</strong>
	                </div>
	                <div class="modal-footer">
	                    <button type="button" class="btn btn-info">
	                        인증 완료
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
		$(document).ready(function () {
	        var fileTarget = $("#fileBox .upload-hidden");
	
	        fileTarget.on("change", function () {
	            // 값이 변경되면
	            if (window.FileReader) {
	                // modern brows1er
	                var filename = $(this)[0].files[0].name;
	            } else {
	                var filename = $(this).val().split("/").pop().split("\\").pop(); // 파일명만 추출
	            }
	            $("#fileBox .form-control").val(filename);
	        });
	    });
	    $("#submit").click(function () {
	        if ($("#pw").val() != $("#pwCheck").val()) {
	            alert("비밀번호를 확인해주세요!!");
	            return;
	        }
	        if ($("input:checkbox[id='agree']").is(":checked") == false) {
	            alert("약관을 확인해주시기 바랍니다.");
	            return;
	        }
	        $("#emailCheck").modal("show");
	    });
	</script>
</body>
</html>