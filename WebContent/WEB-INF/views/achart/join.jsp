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
		<%
		out.flush();
	    RequestDispatcher dheader = request.getRequestDispatcher("/inc/admin.do");
	    dheader.include(request, response);
	%>
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