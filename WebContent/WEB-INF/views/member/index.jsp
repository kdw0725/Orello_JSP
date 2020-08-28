<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<style>
</style>
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/calendar/css/tui-date-picker.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/calendar/css/tui-time-picker.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/calendar/css/tui-calendar.css"/>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/calendar/css/icons.css" />
<script src="<%= request.getContextPath() %>/calendar/js/tui-code-snippet.js"></script>
<script src="<%= request.getContextPath() %>/calendar/js/tui-time-picker.js"></script>
<script src="<%= request.getContextPath() %>/calendar/js/tui-date-picker.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.1/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/chance/1.0.13/chance.min.js"></script>

<script src="<%= request.getContextPath() %>/calendar/js/tui-calendar.js"></script>
<script src="<%= request.getContextPath() %>/calendar/js/data/calendars.js"></script>
<script src="<%= request.getContextPath() %>/calendar/js/data/schedules.js"></script>

<link
    rel="stylesheet"
    href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
    integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP"
    crossorigin="anonymous"
/>
<link
    href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
    rel="stylesheet"
/>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/memberMain.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<section id="content" class="container">
		<div id="contentLeft">
			<div id="profile">
				<div id="imgArea">
					<img src="/orello/images/${member.ori_file}" />
				</div>
				<div id="infoArea">
					<div id="name">${member.name}</div>
					<div id="commentArea">
						<div id="commentInput">
							<input type="text" class="form-control" id="profileComment" />
							<input type="button" class="btn btn-info btn-xs" value="저장"
								id="commentSave" /> <input
								type="button" class="btn btn-default btn-xs" value="취소"
								id="commentCancel" />
						</div>
						<div id="comment">
							<span>&nbsp;${member.statusmsg}</span>
							<input type="button"
								class="btn btn-info btn-xs" value="edit" id="editComment" />
						</div>
					</div>
					
					<c:if test="${empty member.social}">
						<div class="info">
							<i class="fas fa-user"></i>&nbsp;${member.email}
						</div>
						<div class="info">
							<i class="fas fa-mobile-alt"></i>&nbsp;${member.tel}
						</div>
						<div class="info">
							<i class="far fa-building"></i>&nbsp;${member.company}
						</div>
					</c:if>
					<c:if test="${not empty member.social}">
						<div class="info">
							<c:if test="${member.social == 'KAKAO' }">
								<img class="icon-logo" alt="KAKAO" src="/orello/images/kakao-icon.png">
							</c:if>
							<c:if test="${member.social == 'NAVER' }">
								<img class="icon-logo" alt="naver" src="/orello/images/naver-icon.PNG">
							</c:if>
							<c:if test="${member.social == 'GOOGLE' }">
								<img class="icon-logo" alt="google" src="/orello/images/google-icon.png">
							</c:if>
							계정으로 로그인 하였습니다.
						</div>
					</c:if>
				</div>
			</div>
			<div id="timeLine">
				<h4>
					<i class="fas fa-user-clock"></i> TimeLine
				</h4>
				<div id="timeLineContent">
					<table>
						<tr>
							<td class="timeLineList"><img src="../images/2.png"
								alt="profile" />
								<div class="timeLineAction">
									<strong>김동욱</strong> <span>1시간전</span>
									<div>김동욱님이 조윤경님 팔로우를 취소 하였습니다.</div>
								</div></td>
						</tr>
						<tr>
							<td class="timeLineList"><img src="../images/1.png"
								alt="profile" />
								<div class="timeLineAction">
									<strong>조윤경</strong> <span>2시간전</span>
									<div>조윤경님이 김동욱님을 팔로우 하였습니다.</div>
								</div></td>
						</tr>

						<tr>
							<td class="timeLineList"><img src="../images/3.png"
								alt="profile" />
								<div class="timeLineAction">
									<strong>강경원</strong> <span>4시간전</span>
									<div>강경원님이 Orello프로젝트에 참여하였습니다.</div>
								</div></td>
						</tr>

						<tr>
							<td class="timeLineList"><img src="../images/1.png"
								alt="profile" />
								<div class="timeLineAction">
									<strong>조윤경</strong> <span>5시간전</span>
									<div>조윤경님이 양치를 하였습니다.</div>
								</div></td>
						</tr>
						<tr>
							<td class="timeLineList"><img src="../images/4.png"
								alt="profile" />
								<div class="timeLineAction">
									<strong>강혜림</strong> <span>5시간전</span>
									<div>강혜림님이 칼퇴를 하였습니다.</div>
								</div></td>
						</tr>
						<tr>
							<td class="timeLineList"><img src="../images/5.png"
								alt="profile" />
								<div class="timeLineAction">
									<strong>이도윤</strong> <span>6시간전</span>
									<div>이도윤님이 채팅프로그램을 완성하였습니다!</div>
								</div></td>
						</tr>
						<tr>
							<td class="timeLineList"><img src="../images/3.png"
								alt="profile" />
								<div class="timeLineAction">
									<strong>강경원</strong> <span>10시간전</span>
									<div>강경원님이 40분 지각을 하였습니다.</div>
								</div></td>
						</tr>
					</table>
					<input type="button" class="btn btn-default" value="더보기"
						id="timeLineMore" />
				</div>
			</div>
		</div>
		<div id="contentRight">
			<div id="notice">
				<i class="glyphicon glyphicon-exclamation-sign"></i>
			</div>

			<div id="calendarArea">
				<div id="menu">
					<span id="menu-navi">
						<button type="button" class="btn btn-default btn-sm move-day"
							data-action="move-prev">
							<i class="calendar-icon ic-arrow-line-left"
								data-action="move-prev"></i>
						</button> <span id="renderRange" class="render-range"></span>
						<button type="button" class="btn btn-default btn-sm move-day"
							data-action="move-next">
							<i class="calendar-icon ic-arrow-line-right"
								data-action="move-next"></i>
						</button>
						<button type="button" class="btn btn-default btn-sm move-today"
							data-action="move-today">Today</button>
					</span>
				</div>
				<div id="calendar" style="height: 600px;"></div>
			</div>
			<hr />
			<div id="projectArea">
				<h2>Project</h2>
				<hr />
				<c:forEach var="list" items="${projectList}">
					<div class="projectBox">
						<div class="index" style="background-color: ${list.color}" onclick="location.href = '/orello/project/index.do?pseq=${list.seq}';"></div>
						<p class="detail">
							자세히 보기 <i class="glyphicon glyphicon-play"></i>
						</p>
						<div class="projectTitle">
							<strong class="title">${list.name}</strong>
							<p>
								<c:if test="${list.type eq '웹 프로젝트'}">
									<i class="fab fa-internet-explorer"></i> 웹 프로젝트
								</c:if>
								<c:if test="${list.type eq '안드로이드 프로젝트'}">
									<i class="fab fa-android"></i> 안드로이드 프로젝트
								</c:if>
								<c:if test="${list.type eq 'IOS 프로젝트'}">
									<i class="fab fa-apple"></i> IOS 프로젝트
								</c:if>
								<c:if test="${list.type eq '기타 프로젝트'}">
									<i class="fas fa-cat"></i> 기타 프로젝트
								</c:if>
							</p>
						</div>
						<div class="languagebox">
							<c:if test="${list.firstpopular eq 'html' }">
								<i class="fab fa-html5" aria-hidden="true"></i> <label for="fab">HTML</label>
							</c:if>
							<c:if test="${list.firstpopular eq 'js' }">
								<i class="fab fa-js" aria-hidden="true"></i> <label for="fab">JS</label>
							</c:if>
							<c:if test="${list.firstpopular eq 'css' }">
								<i class="fab fa-css3" aria-hidden="true"></i> <label for="fab">CSS3</label>
							</c:if>
							<c:if test="${list.firstpopular eq 'css' }">
								<i class="fab fa-css3" aria-hidden="true"></i> <label for="fab">CSS3</label>
							</c:if>
							<c:if test="${list.firstpopular eq 'python' }">
								<i class="fab fa-python" aria-hidden="true"></i> <label for="fab">Python</label>
							</c:if>
							<c:if test="${list.firstpopular eq 'swift' }">
								<i class="fab fa-swift" aria-hidden="true"></i> <label for="fab">Swift</label>
							</c:if>
							<c:if test="${list.firstpopular eq 'c' }">
								<i class="fab fa-cuttlefish" aria-hidden="true"></i> <label for="fab">C</label>
							</c:if>
							<c:if test="${list.firstpopular eq '기타' }">
								<i class="fab fa-cat" aria-hidden="true"></i> <label for="fab">기타</label>
							</c:if>
							
							<c:if test="${list.secondpopular eq 'html' }">
								<i class="fab fa-html5" aria-hidden="true"></i> <label for="fab">HTML</label>
							</c:if>
							<c:if test="${list.secondpopular eq 'js' }">
								<i class="fab fa-js" aria-hidden="true"></i> <label for="fab">JS</label>
							</c:if>
							<c:if test="${list.secondpopular eq 'css' }">
								<i class="fab fa-css3" aria-hidden="true"></i> <label for="fab">CSS3</label>
							</c:if>
							<c:if test="${list.secondpopular eq 'python' }">
								<i class="fab fa-python" aria-hidden="true"></i> <label for="fab">Python</label>
							</c:if>
							<c:if test="${list.secondpopular eq 'swift' }">
								<i class="fab fa-swift" aria-hidden="true"></i> <label for="fab">Swift</label>
							</c:if>
							<c:if test="${list.secondpopular eq 'c' }">
								<i class="fab fa-cuttlefish" aria-hidden="true"></i> <label for="fab">C</label>
							</c:if>
							<c:if test="${list.secondpopular eq '기타' }">
								<i class="fab fa-cat" aria-hidden="true"></i> <label for="fab">기타</label>
							</c:if>
						</div>
					</div>
				</c:forEach>
				

				<div class="projectBox" id="newProject" data-toggle="modal"
					data-target="createProject">
					<i class="glyphicon glyphicon-plus-sign"></i> <br /> Create a new
					Project
				</div>
			</div>
			<input type="button" value="더보기" class="btn btn-default"
				id="moreProject" />
		</div>
		<div class="modal fade" id="createProject" tabindex="-1" role="dialog"
			aria-labelledby="createProjectModal" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title" id="createProjectModal"
							style="display: inline-block;">프로젝트 생성
						</h3>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form action="/orello/member/createproject.do" method="POST" id="projectForm">
							<div>
								<h4>프로젝트 이름</h4>
								<input type="text" class="form-control" placeholder="프로젝트 이름" name="name"
									required />
								<h4>프로젝트 설명</h4>
								<input type="text" class="form-control" placeholder="프로젝트 설명" name="description" 
									required />
								<h4>프로젝트 기간</h4>
								<div id="datePicker">
									<div
										class="tui-datepicker-input tui-datetime-input tui-has-focus">
										<input id="startpicker-input" type="text" aria-label="Date" name="startdate" />
										<span class="tui-ico-date"></span>
										<div id="startpicker-container" style="margin-left: -1px;"></div>
									</div>
									<span>to</span>
									<div
										class="tui-datepicker-input tui-datetime-input tui-has-focus">
										<input id="endpicker-input" type="text" aria-label="Date" name="enddate" />
										<span class="tui-ico-date"></span>
										<div id="endpicker-container" style="margin-left: -1px;"></div>
									</div>
								</div>
								<h4>프로젝트 유형</h4>
								<div id="checkProjectType">
									<input type="radio" id="web" name="projectType" value="web"/>
									<label for="web"><i
										class="fab fa-internet-explorer"></i> 웹 프로젝트</label> <input
										type="radio" id="android" value="android" name="projectType" />
									<label for="android"><i
										class="fab fa-android"></i>안드로이드 프로젝트</label> <input type="radio"
										id="ios" value="ios" name="projectType" />
									<label for="ios"><i
										class="fab fa-apple"></i> IOS 프로젝트</label> <br /> <input type="radio"
										id="extra" value="extra" name="projectType" />
									<label for="extra"><i
										class="fas fa-cat"></i> 기타 프로젝트</label>
								</div>

								<h4>참여인원</h4>
								<div id="searchArea">
									<input type="text" class="form-control" placeholder="인원 검색" id="searchUser" />
									<div id="searchUserResult"></div>
								</div>
								<div style="height: 70px; padding: 10px;" id="workers">
									<div class="new-worker">
										<i class="glyphicon glyphicon-plus"></i>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-info" id="createProjectBtn">생성 완료</button>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">취소</button>
					</div>
				</div>
			</div>
		</div>
	</section>

	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
	<script src="<%= request.getContextPath() %>/js/memberMain.js"></script>
	<script src="<%= request.getContextPath() %>/calendar/js/myDefault.js"></script>
	<script>
	
	function signOut() {
		console.log(gapi.auth2);
        var auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(function () {
          console.log('User signed out.');
        });
		location.href="/orello/member/signout.do";
      }
	function onLoad() {
	      gapi.load('auth2', function() {
	        gapi.auth2.init();
	      });
	    }
	$("#moreProject").click(function () {
		$.ajax({
			type : "GET",
			url : "/orello/member/moreproject.do",
			dataType : "JSON",
			success: function(result){
				console.log(result);
				for(var i=0;i<result.length;i++){
					var moreProjectBox = document.createElement("div");
			        moreProjectBox.className = "projectBox";

			        var index = document.createElement("div");
			        index.className = "index";
			       
			        index.style.backgroundColor = result[i].color;
			        var pseq = result[i].seq;
			        index.onclick = function(){
			        	location.href = '/orello/project/index.do?pseq='+pseq;
			        }
			        moreProjectBox.appendChild(index);

			        var detail = document.createElement("p");
			        detail.className = "detail";
			        detail.innerHTML =
			            "자세히 보기<i class='glyphicon glyphicon-play'></i>";

			        moreProjectBox.appendChild(detail);

			        var moreProjectTitle = document.createElement("div");
			        moreProjectTitle.className = "projectTitle";
			        var title = document.createElement("strong");
			        title.className = "title";
			        title.innerText = result[i].name;
			        moreProjectTitle.appendChild(title);

			        var moreProjectType = document.createElement("p");

			        if (result[i].type == "웹 프로젝트") {
			            moreProjectType.innerHTML =
			                "<i class='fab fa-internet-explorer'></i> 웹 프로젝트";
			        } else if (result[i].type == "안드로이드 프로젝트") {
			            moreProjectType.innerHTML =
			                "<i class='fab fa-android'></i> 안드로이드 프로젝트";
			        } else if (result[i].type == "IOS 프로젝트") {
			            moreProjectType.innerHTML =
			                "<i class='fab fa-apple'></i> IOS 프로젝트";
			        } else if (result[i].type == "기타 프로젝트") {
			            moreProjectType.innerHTML =
			                "<i class='fas fa-cat'></i> 기타 프로젝트";
			        }
			        moreProjectTitle.appendChild(moreProjectType);
			        moreProjectBox.appendChild(moreProjectTitle);
					
			        var moreLanguageBox = document.createElement("div");
			        
			        var firstpopular = '';
			        if(result[i].firstpopular == 'html'){
			        	firstpopular = '<i class="fab fa-html5" aria-hidden="true"></i> <label for="fab">HTML</label>';
			        } else if(result[i].firstpopular == 'css'){
			        	firstpopular = '<i class="fab fa-css3" aria-hidden="true"></i> <label for="fab">CSS</label>';
			        } else if(result[i].firstpopular == 'js'){
			        	firstpopular = '<i class="fab fa-js" aria-hidden="true"></i> <label for="fab">JS</label>';
			        } else if(result[i].firstpopular == 'java'){
			        	firstpopular = '<i class="fab fa-java" aria-hidden="true"></i> <label for="fab">Java</label>';
			        } else if(result[i].firstpopular == 'python'){
			        	firstpopular = '<i class="fab fa-python" aria-hidden="true"></i> <label for="fab">Python</label>';
			        } else if(result[i].firstpopular == 'swift'){
			        	firstpopular = '<i class="fab fa-swift" aria-hidden="true"></i> <label for="fab">Swift</label>';
			        } else if(result[i].firstpopular == 'c'){
			        	firstpopular = '<i class="fab fa-cuttlefish" aria-hidden="true"></i> <label for="fab">C</label>';
			        } else if(result[i].firstpopular == '기타') {
			        	firstpopular = '<i class="fas fa-cat" aria-hidden="true"></i> <label for="fab">기타</label>';
			        }
			        
			        moreLanguageBox.insertAdjacentHTML('beforeend',firstpopular);
			        
			        var secondpopular = '';
			        if(result[i].secondpopular == 'html'){
			        	secondpopular = '<i class="fab fa-html5" aria-hidden="true"></i> <label for="fab">HTML</label>';
			        } else if(result[i].secondpopular == 'css'){
			        	secondpopular = '<i class="fab fa-css3" aria-hidden="true"></i> <label for="fab">CSS</label>';
			        } else if(result[i].secondpopular == 'js'){
			        	secondpopular = '<i class="fab fa-js" aria-hidden="true"></i> <label for="fab">JS</label>';
			        } else if(result[i].secondpopular == 'java'){
			        	secondpopular = '<i class="fab fa-java" aria-hidden="true"></i> <label for="fab">Java</label>';
			        } else if(result[i].secondpopular == 'python'){
			        	secondpopular = '<i class="fab fa-python" aria-hidden="true"></i> <label for="fab">Python</label>';
			        } else if(result[i].secondpopular == 'swift'){
			        	secondpopular = '<i class="fab fa-swift" aria-hidden="true"></i> <label for="fab">Swift</label>';
			        } else if(result[i].secondpopular == 'c'){
			        	secondpopular = '<i class="fab fa-cuttlefish" aria-hidden="true"></i> <label for="fab">C</label>';
			        } else if(result[i].secondpopular == '기타') {
			        	secondpopular = '<i class="fas fa-cat" aria-hidden="true"></i> <label for="fab">기타</label>';
			        }
			        moreLanguageBox.insertAdjacentHTML('beforeend',secondpopular);
			        
			        moreLanguageBox.className = "languagebox";
			        moreProjectBox.appendChild(moreLanguageBox);
			        			        
			        let moreTemp = moreProjectBox;
			        moreProjectBox.onmouseover = function () {
			            if (onmouseFlag == 0) {
			                var innerDiv = document.createElement("div");
			                moreTemp.children[0].style.width = "250px";
			                moreTemp.children[0].style.height = "130px";
			                var innerIcon = document.createElement("i");
			                innerIcon.className = "glyphicon glyphicon-zoom-in";
			                innerIcon.style.fontSize = "80px";
			                innerIcon.style.color = "#fff";
			                innerIcon.style.margin = "10px";
			                innerDiv.appendChild(innerIcon);
			                innerDiv.style.color = "#fff";
			                innerDiv.style.textAlign = "center";

			                var innerContent = document.createElement("p");
			                innerContent.innerText = "자세히 보러가기";
			                innerContent.style.fontWeight = "bold"
			                innerDiv.appendChild(innerContent);
			                

			                moreTemp.children[0].appendChild(innerDiv);
			                moreTemp.children[0].style.cursor = "pointer";

			                onmouseFlag = 1;
			            }
			        };
			        moreProjectBox.onmouseleave = function () {
			            if (onmouseFlag == 1) {
			                moreTemp.children[0].style.width = "50px";
			                moreTemp.children[0].style.height = "25px";
			                moreTemp.children[0].removeChild(
			                    moreTemp.children[0].children[0]
			                );
			                moreTemp.children[0].style.cursor = "none";
			                onmouseFlag = 0;
			            }
			        };
			        $("#newProject").before(moreProjectBox);
				}
				$("#moreProject").css("display", "none");
			    $("#contentLeft").css("height", $("#contentRight").height());
			},
			error : function(a, b, c){
				console.log(a, b, c)
			}
		});
	    
	});
	</script>
</body>
</html>