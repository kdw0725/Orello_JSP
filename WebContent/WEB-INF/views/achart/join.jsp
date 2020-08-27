<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<style>
<%@ include file="/css/achart/point.css"%>

</style>
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<section id="content" class="container">
		<div id="contentLeft">
			<div id="projectInfo">
				<div id="projectName"></div>
				<div id="projectPeriod">Manager Name</div>
				<div id="projectContent">lorem1234@gmail.com</div>
				<div id="projectMember"></div>
			</div>
			<div id="menu">
				<ul class="accordion">
					<div>
						<i class="glyphicon glyphicon-home"></i>Home
					</div>
				</ul>
				<ul class="accordion">
					<div>
						<i class="glyphicon glyphicon-calendar"></i>Project
					</div>
					<!-- <li class="panel"><div><i class="glyphicon glyphicon-minus"></i></div></li>
            <li class="panel"><div><i class="glyphicon glyphicon-minus"></i></div></li>    -->
				</ul>
				<ul class="accordion">
					<div>
						<i class="glyphicon glyphicon-check"></i>User
					</div>
				</ul>
				<ul class="accordion parent">
					<div>
						<i class="glyphicon glyphicon-list-alt"></i>Chart
					</div>
					<li class="panel">
						<div>
							<i class="glyphicon glyphicon-minus"></i>자료실(사용량)
						</div>
					</li>
					<li class="panel">
						<div>
							<i class="glyphicon glyphicon-minus"></i>포인트(결제)
						</div>
					</li>
					<li class="panel">
						<div>
							<i class="glyphicon glyphicon-minus"></i>프로젝트 신규
						</div>
					</li>
					<li class="panel">
						<div>
							<i class="glyphicon glyphicon-minus"></i>유저 신규
						</div>
					</li>
				</ul>
				<ul class="accordion">
					<div>
						<i class="glyphicon glyphicon-hdd"></i>QnA
					</div>
				</ul>
				<!-- <ul class="accordion"><div><i class="fas fa-code-branch"></i>Git</div></ul> -->
				<ul class="accordion">
					<div>
						<i class="glyphicon glyphicon-user"></i>FAQ
					</div>
				</ul>
				<ul class="accordion">
					<div>
						<i class="glyphicon glyphicon-user"></i>공지사항
					</div>
				</ul>

			</div>
		</div>
		<div id="contentRight">
			<div id="main_list">
				<div id="main_title">
					<li class="glyphicon glyphicon-stats"></li> Join Statistic
				</div>
			<form method="GET" action="/orello/achart/ajoinok.do" id="pointForm">
				<div id="dateform">
					<div id="datePicker" style="width: 500px; display: inline-block;">
						
					
						<div class="tui-datepicker-input tui-datetime-input tui-has-focus">
							<input id="startpicker-input" type="text" aria-label="Date" name="startdate"/> <span
								class="tui-ico-date"></span>
							<div id="startpicker-container" style="margin-left: -1px;"></div>
						</div>
						
						<span>to</span>
						
						
						<div class="tui-datepicker-input tui-datetime-input tui-has-focus">
							<input id="endpicker-input" type="text" aria-label="Date" name="enddate"/> <span
								class="tui-ico-date"></span>
							<div id="endpicker-container" style="margin-left: -1px;"></div>
						</div>
						
					</div>

					<button id="okbtn" class="btn btn-info" type="submit">
						<i class="glyphicon glyphicon-ok"></i>OK
					</button>
				</div>
				</form>

				<div id="hidden_container">
					<table id="tbl" class="table">
						<thead>
						<tr>
							<th>No.</th>
							<th>Name</th>
							<th>Regdate</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${plist}" var="dto">
								<tr>
									<td>${dto.cnt }</td>
									<td>${dto.name }</td>
									<td>${dto.regdate }</td>
								</tr>
							</c:forEach>
						</tbody>
					
					</table>
					<hr style="border: 0.2px solid #bbb;" />

					<div id="search" style="text-align:center;">
						<div id="select_title">
							<select name="" id="" class="form-control">
								<option value="0">total</option>
								<option value="1">name</option>
								<option value="2">use point</option>
								<option value="3">total point</option>
							</select>
						</div>
						<input type="text" class="form-control" />
						<button class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</div>

					<!-- pagination -->
					<div style="text-align:center;">${pagebar }</div>
				</div>
			</div>
		</div>
	</section>

	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>

	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="/orello/js/jquery-ui.js"></script>
	<script>
		//클릭한 메뉴만 표시되도록
		$(".list-group > a").click(function() {
			$(".list-group > a").removeClass("active");
			$(this).addClass("active");
		});

		//아코디언 메뉴
		var acc = document.getElementsByClassName("parent");
		for (var i = 0; i < acc.length; i++) {
			acc[i].addEventListener("click", function() {
				var li = $(this).children("li");
				li.slideToggle(1000);
				this.classList.toggle("active");
			});
		}

		$("#okbtn").click(function() {
			$("#hidden_container").css("display", "block");
		});
	</script>
	<link rel="stylesheet"
		href="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.css" />
	<script
		src="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.js"></script>
	<script>
		var today = new Date();
		var picker = tui.DatePicker.createRangePicker({
			startpicker : {
				date : today,
				input : "#startpicker-input",
				container : "#startpicker-container",
			},
			endpicker : {
				date : today,
				input : "#endpicker-input",
				container : "#endpicker-container",
			},
		
		});
	</script>
	
	<script>
	function movePage() {
		//alert(event.srcElement.value);
		location.href = "/orello/achart/ajoinok.do?page=" + event.srcElement.value;
	}
	
	$("#pagebar").val(${page});
	</script>

</body>
</html>