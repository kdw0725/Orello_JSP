<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/adminLeft.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/userView.css">

<style>
</style>
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>
</head>
<body>
<jsp:useBean id="now" class="java.util.Date"/>
   <fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="nowdate"/>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<section id="content" class="container">
		<%
		out.flush();
	    RequestDispatcher dheader = request.getRequestDispatcher("/inc/admin.do");
	    dheader.include(request, response);
	%>

		<div id="contentRight">
			<div class="welcome">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<div class="content">
							<c:forEach items="${data}" var="dto">
								<h2>Welcome to ${dto.name}'s Board</h2>
							</c:forEach>
								<!-- <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor.</p> -->
							</div>
						</div>
					</div>
				</div>
			</div>
						
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 my-3">
				<div class="card-list">
					<div class="row">
						<div class="col-12 col-md-6 col-lg-4 col-xl-3 mb-4" id="col1">
							<div class="card blue">
								<div class="title">All Project</div>
								<i class="zmdi zmdi-upload"></i>
								<div class="value">${pcnt}</div>
								<div class="stat">
									<b>13</b>% increase
								</div>
							</div>
						</div>
						<div class="col-12 col-md-6 col-lg-4 col-xl-3 mb-4" id="col2">
							<div class="card green">
								<div class="title">Posts written</div>
								<i class="zmdi zmdi-upload"></i>
								<div class="value">${bcnt}</div>
								<div class="stat">
									<b>4</b>% increase
								</div>
							</div>
						</div>
						<div class="col-12 col-md-6 col-lg-4 col-xl-3 mb-4" id="col3">
							<div class="card orange">
								<div class="title">RegDate</div>
								<i class="zmdi zmdi-download"></i>
								<div class="value">
								<c:forEach items="${data}" var="dto">
								${(dto.regdate).substring(0,10)}
								</c:forEach>
								</div>
								<div class="stat">
									<b>13</b>% decrease
								</div>
							</div>
						</div>
						<div class="col-12 col-md-6 col-lg-4 col-xl-3 mb-4" id="col4">
							<div class="card red">
								<div class="title">Tel</div>
								<i class="zmdi zmdi-download"></i>
								<div class="value">
								<c:forEach items="${data}" var="dto">
								${dto.tel}
								</c:forEach>
								</div>
								<div class="stat">
									<b>13</b>% decrease
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="projects mb-4">
					<div class="projects-inner">
						<header class="projects-header">
							<div class="title">All Projects</div>
							<div class="count">| ${pcnt} Projects</div>
							<i class="zmdi zmdi-download"></i>
						</header>
						<div overflow:auto>
							<table class="projects-table">
								<thead>
									<tr>
										<th>Project</th>
										<th>Period</th>
										<th>Leader</th>
										<th>Status</th>
									</tr>
								</thead>
								<c:forEach items="${info}" var="dto">
									<tr>
										<td>
											<p class="project_name">${dto.projectname}</p>
										</td>
										<td>
											<p>${dto.startdate}~ ${dto.enddate}</p>
										</td>
										<td class="member">
											<figure>
												<img
													src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/584938/people_8.png" />
											</figure>
											<div class="member-info">
												<p class="leader_name">${dto.leader}</p>
											</div>
										</td>
										<td class="status">
										<c:if test="${nowdate >= dto.startdate}">
											<c:if test="${nowdate > dto.enddate}">
												<span class="status-text status-red">End</span>
											</c:if>
											<c:if test="${nowdate <= dto.enddate}">
												<span class="status-text status-blue">Ing</span>
											</c:if>
										
										</c:if>
											<c:if test="${nowdate < dto.startdate}">
												<span class="status-text status-green">Yet</span>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
				<div id="charts1">
					<section class="charts">
						<div class="container-fluid">
							<div class="row">
								<div class="col-md-6" id="chart1">
									<div class="chart-container">
										<span>Project</span> <i class="glyphicon glyphicon-search"
											id="btn1"></i>
										<canvas id="myChart1"></canvas>
									</div>
								</div>
								<div class="col-md-6">
									<div class="chart-container">
										<span>Post</span> <i class="glyphicon glyphicon-search"
											id="btn2"></i>
										<canvas id="myChart2"></canvas>
									</div>
								</div>
							</div>
							
						</div>
					</section>
				</div>
			</main>
		</div>

		<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content"
					style="background-color: #fff; margin: 100px -200px;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h3>Chart1</h3>
					</div>
					<div class="modal-body">
						<div class="chart-container">

							<canvas id="dialog1"></canvas>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content"
					style="background-color: #fff; margin: 100px -200px;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h3>Chart2</h3>
					</div>
					<div class="modal-body">
						<div class="chart-container">
							<canvas id="dialog2"></canvas>
						</div>
					</div>
				</div>

			</div>

		</div>
	</section>

	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
	<!-- <script src="//code.jquery.com/jquery-1.11.0.min.js"></script> -->
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
	<script src="/orello/js/jquery-ui.js"></script>
	<script src="<%=request.getContextPath()%>/js/adminLeft.js">
	
	  var chart = document.getElementById('myChart1');
	  
	  var options1 = {
				type : 'bar',
				data : {
				    labels : ${proName},

					datasets : [ {
						label : "참여율",
						fill : false,
						lineTension : 0,
						data : ${board},
						pointBorderColor : "#4bc0c0",
						borderColor : '#4bc0c0',
						borderWidth : 2,
						showLine : true,
					}, ]
				},
				options : {
					scales : {
						yAxes : [ {
							ticks : {
								// beginAtZero: true,
								suggestedMin : 0,
								suggestedMax : 100

							}
						} ]
					}
				}};
	  
	  var myChart = new Chart(chart, options1);
	  

	  
	  
	  
	  
	  
	  var Chart2 = document.getElementById('myChart2').getContext('2d');
	  var chart = new Chart(Chart2, {
	    type: 'line',
	    data: {
	      labels: ["January", "February", "March", "April", 'test', 'test', 'test', 'test'],
	      datasets: [{
	        label: "My First dataset",
	        backgroundColor: 'rgb(255, 99, 132)',
	        borderColor: 'rgb(255, 79, 116)',
	        borderWidth: 2,
	        pointBorderColor: false,
	        data: [5, 10, 5, 8, 20, 30, 20, 10],
	        fill: false,
	        lineTension: .4,

	      }]
	    },    // Configuration options
	    options: {
	      title: {
	        display: false
	      }
	    }
	  });
	</script>
	<script src="/orello/js/jquery-ui.js"></script>
	<script>
	  var dialog1 = document.getElementById('dialog1');
	  var myChart2 = new Chart(dialog1, {
	    type: 'bar',
	    data: {
	      labels: ["January", "February", "March", "April", "May", 'Jul'],
	      datasets: [{
	        label: "Lost",
	        fill: false,
	        lineTension: 0,
	        data: [45, 25, 40, 20, 45, 20],
	        pointBorderColor: "#f87c85",
	                borderColor: '#f87c85',
	                backgroundColor: '#f87c85',
	        borderWidth: 2,
	        showLine: true,
	      }, {
	        label: "Succes",
	        fill: false,
	        lineTension: 0,
	        startAngle: 2,
	        data: [20, 40, 20, 45, 25, 60],
	        // , '#ff6384', '#4bc0c0', '#ffcd56', '#457ba1'
	        backgroundColor: "#b985ea",
	                pointBorderColor: "#b985ea",
	                borderColor: '#b985ea',
	        borderWidth: 2,
	        showLine: true,
	      }]
	    },
	  });

	  // $("#dialog1").hide();

	  $("#btn1").click(function () {
	    $("#myModal1").modal();

	  });

	  var dialog2 = document.getElementById('dialog2');
	  var myChart2 = new Chart(dialog2, {
	    type: 'line',
	    data: {
	      labels: ["January", "February", "March", "April", 'test', 'test', 'test', 'test'],
	      datasets: [{
	        label: "My First dataset",
	        backgroundColor: 'rgb(255, 99, 132)',
	        borderColor: 'rgb(255, 79, 116)',
	        borderWidth: 2,
	        pointBorderColor: false,
	        data: [5, 10, 5, 8, 20, 30, 20, 10],
	        fill: false,
	        lineTension: .4,
	      }]
	    },    // Configuration options
	    options: {
	      title: {
	        display: false
	      }
	    }
	  });

	  $("#btn2").click(function () {
	    $("#myModal2").modal();
	  });
	</script>
</body>
</html>