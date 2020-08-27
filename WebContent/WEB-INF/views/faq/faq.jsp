<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/faq/faq.css">
<style>
</style>
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<section id="content" class="container">
		<div id="titleContainer">
			<section class="faq-section">
				<div class="container">
				
				<form method="GET" action="/orello/faq/faqadd.do" id="faqForm">
					<div class="row">
						<!-- ***** FAQ Start ***** -->
						<div class="col-md-6 offset-md-3" style="margin-left: 228px;">
							<div class="QnA_title_pic" id="QnA_pic">
								<h1>FAQ</h1>
							</div>
						</div>
						<!-- <form method="GET" action="/orello/faq/faqadd.do" id="faqForm"> -->
							<div class="col-md-6 offset-md-3" id="faq_content">
								<div class="faq" id="accordion">
									<c:forEach items="${faq}" var="dto">
										<div class="card">
											<div class="card-header" id="faqHeading-${dto.cnt}">
												<div class="mb-0">
													<h5 class="faq-title" data-toggle="collapse"
														data-target="#faqCollapse-${dto.cnt}"
														data-aria-expanded="true"
														data-aria-controls="faqCollapse-${dto.cnt}"
														id="faq_title" style="height: 65px;">
														<span class="badge">${dto.cnt}</span>${dto.question}
													</h5>
												</div>
											</div>
											<div id="faqCollapse-${dto.cnt}" class="collapse"
												aria-labelledby="faqHeading-${dto.cnt}"
												data-parent="#accordion">
												<div class="card-body">
													<p>${dto.content}</p>
												</div>
											</div>
										</div>
										<div class="checkBox">
											<input type="checkbox" class="cbbox" name="checkbox" id="deletetxt"
												value="${dto.fseq}">
										</div>
									</c:forEach>

								<!-- <form method="GET" action="/orello/faq/faqadd.do" id="faqForm"> -->
									<div id="addtext"></div>
								<!-- </form> -->
								</div>
								
							</div>


							<div id="footerbtn">
								<button type="button" class="btn btn-info" id="addbtn" onclick="add_text()">추가</button>
								<button class="btn btn-info" id="delbtn" type="submit">삭제</button>
							</div>
						<!-- </form>  -->
					</div>
					</form>
				</div>
			</section>
		</div>
	</section>

	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script>
		var addtext = document.getElementById("addtext");
		var addbtn = document.getElementById("addbtn");
		var footerbtn = document.getElementById("footerbtn");
		//var list = document.getElementById("deletetxt")

		function add_text() {
			document.getElementById("footerbtn").innerHTML = "";
			document.getElementById("addtext").innerHTML += "<input type='text' id='qna_title' placeholder='제목을 입력하세요.' name='txtTitle'><br><input type='text' id='qna_content' placeholder='내용을 입력하세요.' name='txtContent'><br><button type='submit' id='qnaadd_btn' class='btn btn-info qna_add'>추가</button><button type='submit' id='qnadelete_btn' class='btn btn-info qna_add' onclick='href.location='/orello/faq/faq.do';'>취소</button>"
		}
	
	</script>
</body>
</html>