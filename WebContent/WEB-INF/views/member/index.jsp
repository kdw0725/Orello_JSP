<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<style>
</style>
<%@ include file="/inc/asset.jsp"%>
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

<!-- 헤더에 넣어야할것 -->
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="380478341630-8p93j9l00a6tq6s1vlb7tnjlnqts1vj2.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
<!-- 헤더헤더 -->
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
	<%@ include file="/inc/header.jsp"%>
	<section id="content" class="container">
		<div id="contentLeft">
			<div id="profile">
				<div id="imgArea">
					<img src="../images/${member.ori_file}" />
				</div>
				<!-- 이것도 헤더 -->
				<a id="signout" onclick="signOut()">Sign out</a>
				<!-- 헤더헤더 -->
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
							<td class="timeLineList"><img src="../images/man_02.png"
								alt="profile" />
								<div class="timeLineAction">
									<strong>김동욱</strong> <span>1시간전</span>
									<div>김동욱님이 조윤경님 팔로우를 취소 하였습니다.</div>
								</div></td>
						</tr>
						<tr>
							<td class="timeLineList"><img src="../images/man_01.png"
								alt="profile" />
								<div class="timeLineAction">
									<strong>조윤경</strong> <span>2시간전</span>
									<div>조윤경님이 김동욱님을 팔로우 하였습니다.</div>
								</div></td>
						</tr>

						<tr>
							<td class="timeLineList"><img src="../images/man_03.png"
								alt="profile" />
								<div class="timeLineAction">
									<strong>강경원</strong> <span>4시간전</span>
									<div>강경원님이 Orello프로젝트에 참여하였습니다.</div>
								</div></td>
						</tr>

						<tr>
							<td class="timeLineList"><img src="../images/man_01.png"
								alt="profile" />
								<div class="timeLineAction">
									<strong>조윤경</strong> <span>5시간전</span>
									<div>조윤경님이 양치를 하였습니다.</div>
								</div></td>
						</tr>
						<tr>
							<td class="timeLineList"><img src="../images/man_04.png"
								alt="profile" />
								<div class="timeLineAction">
									<strong>강혜림</strong> <span>5시간전</span>
									<div>강혜림님이 칼퇴를 하였습니다.</div>
								</div></td>
						</tr>
						<tr>
							<td class="timeLineList"><img src="../images/man_05.png"
								alt="profile" />
								<div class="timeLineAction">
									<strong>이도윤</strong> <span>6시간전</span>
									<div>이도윤님이 채팅프로그램을 완성하였습니다!</div>
								</div></td>
						</tr>
						<tr>
							<td class="timeLineList"><img src="../images/man_03.png"
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
				<c:forEach var="list" items="${projectList}" end="1">
					<div class="projectBox">
						<div class="index" style="background-color: ${list.color}"></div>
						<p class="detail">
							자세히 보기 <i class="glyphicon glyphicon-play"></i>
						</p>
						<div class="projectTitle">
							<strong class="title">${list.name}</strong>
							<p>
								<i class="fab fa-internet-explorer"></i> 웹 프로젝트
							</p>
						</div>
						<div class="languagebox">
							<i class="fab fa-js" aria-hidden="true"></i> <label for="fab">JS</label>
							<i class="fab fa-css3" aria-hidden="true"></i> <label for="fab">CSS</label>
							<i class="fab fa-java" aria-hidden="true"></i> <label for="fab">Java</label>
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
						<form action="#" method="GET">
							<div>
								<h4>프로젝트 이름</h4>
								<input type="text" class="form-control" placeholder="프로젝트 이름"
									required />
								<h4>프로젝트 설명</h4>
								<input type="text" class="form-control" placeholder="프로젝트 설명"
									required />
								<h4>프로젝트 기간</h4>
								<div id="datePicker">
									<div
										class="tui-datepicker-input tui-datetime-input tui-has-focus">
										<input id="startpicker-input" type="text" aria-label="Date" />
										<span class="tui-ico-date"></span>
										<div id="startpicker-container" style="margin-left: -1px;"></div>
									</div>
									<span>to</span>
									<div
										class="tui-datepicker-input tui-datetime-input tui-has-focus">
										<input id="endpicker-input" type="text" aria-label="Date" />
										<span class="tui-ico-date"></span>
										<div id="endpicker-container" style="margin-left: -1px;"></div>
									</div>
								</div>
								<h4>프로젝트 유형</h4>
								<div id="checkProjectType">
									<input type="radio" id="web" name="projectType" />
									<label for="web"><i
										class="fab fa-internet-explorer"></i> 웹 프로젝트</label> <input
										type="radio" id="android" name="projectType" />
									<label for="android"><i
										class="fab fa-android"></i>안드로이드 프로젝트</label> <input type="radio"
										id="ios" name="projectType" />
									<label for="ios"><i
										class="fab fa-apple"></i> IOS 프로젝트</label> <br /> <input type="radio"
										id="extra" name="projectType" />
									<label for="extra"><i
										class="fas fa-cat"></i> 기타 프로젝트</label>
								</div>

								<h4>참여인원</h4>
								<div id="searchArea">
									<input type="text" class="form-control" placeholder="인원 검색"
										id="searchUser" />
								</div>
								<div style="height: 70px; padding: 10px;" id="workers">
									<div class="team-worker">
										<img src="../images/man_01.png" alt="worker" />
									</div>
									<div class="team-worker" style="z-index: 1; left: -20px;">
										<img src="../images/man_02.png" alt="worker" />
									</div>
									<div class="team-worker" style="z-index: 2; left: -40px;">
										<img src="../images/dog03.jpg" alt="worker"
											style="border-radius: 50%;" />
									</div>
									<div class="team-worker" style="z-index: 3; left: -60px;">
										<img src="../images/man_04.png" alt="worker" />
									</div>
									<div class="new-worker" style="left: -80px;">
										<i class="glyphicon glyphicon-plus"></i>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-info">생성 완료</button>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">취소</button>
					</div>
				</div>
			</div>
		</div>
	</section>

	<%@ include file="/inc/footer.jsp"%>
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
	</script>
</body>
</html>