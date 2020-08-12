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
	            <form action="signinok.do" method="POST" enctype="multipart/form-data">
	                <input
	                    type="email"
	                    name="email"
	                    placeholder="이메일"
	                    class="form-control"
	                    id="email"
	                    required
	                />
	                <span id="emailResult" class="glyphicon"></span>
	                <input
	                    type="password"
	                    name="pw"
	                    placeholder="비밀번호 (4~16자)"
	                    class="form-control"
	                    id="pw"
	                    required
	                />
	                <span id="pwResult" class="glyphicon"></span>
	                <input
	                    type="password"
	                    placeholder="비밀번호 확인"
	                    class="form-control"
	                    id="pwCheck"
	                    required
	                />
	                <span id="pwcResult" class="glyphicon"></span>
	                <input
	                    type="tel"
	                    name="tel"
	                    class="form-control"
	                    placeholder="전화번호"
	                    id="tel"
	                    required
	                />
	                <span id="telResult" class="glyphicon"></span>
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
	                        name="file"
	                        id="ex_file"
	                        accept="image/*"
	                        name="profile"
	                    />
	                </div>
	                <input
	                    type="text"
	                    class="form-control"
	                    name="company"
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
		var emailChecked = false;
		var pwCheck = false;
		var pwcCheck = false;
		var telChecked = false;
	
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
		
		$("#email").keyup(function(){
			emailChecked = false;
	    	var emailPattern = /\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}/;
	    	if(emailPattern.test($("#email").val())){
	    		$.ajax({
		    		type: "POST",
		    		url: "/orello/member/emailcheck.do",
		    		data: "email="+$("#email").val(),
		    		dataType: "JSON",
		    		success: function(result){
		    			if(result.result == 0){
		    				emailChecked = true;
		    		        $("#emailResult").removeClass("glyphicon-remove");
		    	    		$("#emailResult").addClass("glyphicon-ok");
		    			} else if(result.result == 1){
		    				alert("이미 사용중인 이메일입니다.")
		    			} else{
		    				alert("오류가 발생하였습니다.\n잠시후에 다시 시도해주세요.")
		    			}
		    		},
		    		error: function(a, b, c){
		    			console.log(a, b, c);
		    		}
		    	});
	    	}else{
	    		$("#emailResult").removeClass("glyphicon-ok");
	    		$("#emailResult").addClass("glyphicon-remove");
	    	}
		});
		
		$("#pw").keyup(function(){
			pwCheck = false;
			if($("#pw").val().length > 3 && $("#pw").val().length < 17){
				$("#pwResult").removeClass("glyphicon-remove");
	    		$("#pwResult").addClass("glyphicon-ok");
	    		pwCheck = true;
			} else{
				$("#pwResult").removeClass("glyphicon-ok");
	    		$("#pwResult").addClass("glyphicon-remove");
			}
		});
		
		$("#pwCheck").keyup(function(){
			pwcCheck = false;
			if ($("#pw").val() != $("#pwCheck").val()) {
				$("#pwcResult").removeClass("glyphicon-ok");
	    		$("#pwcResult").addClass("glyphicon-remove");
			} else{
	    		pwcCheck = true;
				$("#pwcResult").removeClass("glyphicon-remove");
	    		$("#pwcResult").addClass("glyphicon-ok");
			}
		});
		
		
		$("#tel").keyup(function() {
			telChecked = false;
			this.value = autoHypenPhone(this.value);

			var telPattern = /^\d{3}-\d{3,4}-\d{4}$/;
			if (telPattern.test($("#tel").val())) {

				$.ajax({
					type : "POST",
					url : "/orello/member/telCheck.do",
					data : "tel=" + $("#tel").val(),
					dataType : "JSON",
					success : function(result) {
						if(result.result == 0){
							$("#telResult").removeClass("glyphicon-remove");
							$("#telResult").addClass("glyphicon-ok");
							telChecked = true;
						} else if(result.result == 1){
							alert("이미 사용중인 전화번호입니다.")
						} else{
							alert("오류가 발생하였습니다.\n잠시후에 다시 시도해주세요.")
						}
					},
					error : function(a, b, c) {
						console.log(a, b, c);
					}
				});

				
			} else {
				$("#telResult").removeClass("glyphicon-ok");
				$("#telResult").addClass("glyphicon-remove");
			}
		});

		var autoHypenPhone = function(str) {
			str = str.replace(/[^0-9]/g, '');
			var tmp = '';
			if (str.length < 4) {
				return str;
			} else if (str.length < 7) {
				tmp += str.substr(0, 3);
				tmp += '-';
				tmp += str.substr(3);
				return tmp;
			} else if (str.length < 11) {
				tmp += str.substr(0, 3);
				tmp += '-';
				tmp += str.substr(3, 3);
				tmp += '-';
				tmp += str.substr(6);
				return tmp;
			} else {
				tmp += str.substr(0, 3);
				tmp += '-';
				tmp += str.substr(3, 4);
				tmp += '-';
				tmp += str.substr(7);
				return tmp;
			}

			return str;
		}

		$("#submit").click(function() {
			if(emailChecked && pwCheck && pwcCheck && telChecked){
				if ($("input:checkbox[id='agree']").is(":checked") == false) {
					alert("약관을 확인해주시기 바랍니다!");
					return;
				}
				$("#emailCheck").modal("show");
			} else if(!emailChecked) {
				alert("이메일을 확인해주시기 바랍니다!");
				$("#email").focus();
				return;
			} else if(!pwCheck){
				alert("비밀번호를 확인해주시기 바랍니다!");
				$("#pw").focus();
				return;
			} else if(!pwcCheck){
				alert("비밀번호가 일치하지 않습니다!");
				$("#pwCheck").focus();
				return;
			} else if(!telChecked){
				alert("전화번호를 확인해주시기 바랍니다!");
				$("#tel").focus();
				return;
			}
		});
	</script>
</body>
</html>