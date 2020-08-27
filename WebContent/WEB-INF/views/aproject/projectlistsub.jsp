<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<style>
<%@ include file="/css/aproject/projectlistsub.css"%>
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
					<div onclick="location.href='/orello/aproject/projectlist.do';">
						<i class="glyphicon glyphicon-calendar"></i>Project
					</div>
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

				<!-- Email 카테고리 추가 -->
				<!-- <ul class="accordion">
                <div onclick="location.href='http:mail.google.com'">
                    <i class="glyphicon glyphicon-envelope"></i>Email
                </div>
            </ul> -->


			</div>
		</div>
		<div id="contentRight">
			<div class="welcome">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<div class="content">
								<h2>Project Info</h2>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
									sed do eiusmod tempor.</p>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- /////////////////////////////////////////////////////////////////// -->

			<div class="container-fluid">
				<div class="row">
					<main role="main" class="col-md-9 ml-sm-auto col-lg-10 my-3">
						<section class="statistics">
							<div class="container-fluid">
								<div class="row">
									<div class="col-md-4">
										<div class="box">
											<i class="fa fa-envelope fa-fw bg-primary"
												style="background-color: #80B1D3;"></i>
											<div class="info">
												<h3>${dto.name }</h3>
												<!-- <span>Emails</span> -->
												<p>Project Name</p>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="box">
											<i class="fa fa-file fa-fw danger"
												style="background-color: #FB8072;"></i>
											<div class="info" id="teamnameinfo">
												<h3>${dto.startdate}</h3>
												<!-- <span>Projects</span> -->
												<p>Start Date</p>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="box">
											<i class="fa fa-users fa-fw success"
												style="background-color: #B3DE69;"></i>
											<div class="info" id="startinfo">
												<h3>${dto.enddate }</h3>
												<!-- <span>Users</span> -->
												<p>End Date</p>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="box">
											<i class="fa fa-users fa-fw success"
												style="background-color: #BC80BD;"></i>
											<div class="info" id="finishinfo">
												<h3>${dto.regdate }</h3>
												<!-- <span>Users</span> -->
												<p>State</p>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="box">
											<i class="fa fa-users fa-fw success"
												style="background-color: #CCEBC5;"></i>
											<div class="info" id="dataroominfo">
												<h3>
													<a href="#">http://DataRoom.html</a>
												</h3>
												<!-- <span>Users</span> -->
												<p>DataRoom Link</p>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="box">
											<i class="fa fa-users fa-fw success"
												style="background-color: #FFED6F;"></i>
											<div class="info" id="boardinfo">
												<h3>
													<a href="#">http://Board.html</a>
												</h3>
												<!-- <span>Users</span> -->
												<p>Board Link</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</section>

						<!-- /////////////////////////////////////////////////////////////////// -->

						<div class="projects mb-4">
							<div class="projects-inner">
								<header class="projects-header">
									<div class="title">Project Members</div>
									<div class="count">| 6 Members</div>
									<i class="zmdi zmdi-download"></i>
								</header>
								<table class="projects-table">
									<thead>
										<tr>
											<th>Name</th>
											<th>Deadline</th>
											<th>Team Leader</th>
											<th>Status</th>

										</tr>
									</thead>
									<c:forEach items="${list }" var="dto">
									<tr onclick="location.href='userListSub.html'">
										<td>
											<p>${dto.memberName }</p>
											<p>${dto.email }</p>	
										</td>
										<td>${dto.enddate }</td>
										<td class="member">
											<figure>
												<img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/584938/people_8.png" />
											</figure>
											<div class="member-info">
												<p>${dto2.leadername }</p>
												<p>${dto2.leaderemail }</p>
											</div>
										</td>
										<td class="status">
											<c:if test="${dto.position == '팀장'}">
											<span class="status-text status-red">${dto.position }</span>
											</c:if>
											<c:if test="${dto.position == '팀원'}">
											<span class="status-text status-blue">${dto.position }</span>
											</c:if>
										</td>
									</tr>
									</c:forEach>
									
								</table>
							</div>
						</div>


						<section class="charts">
							<div class="container-fluid">
								<div class="row">
									<div class="col-md-6" id="chart1">
										<div class="chart-container">
											<span>팀원 참여도</span> <i class="glyphicon glyphicon-search"
												id="btn1"></i>
											<canvas id="myChart1"></canvas>
										</div>
									</div>
									<div class="col-md-6" id="chart2">
										<div class="chart-container">
											<span>날짜별 활성화율</span> <i class="glyphicon glyphicon-search"
												id="btn2"></i>
											<canvas id="myChart2"></canvas>
										</div>
									</div>
								</div>
							</div>
						</section>
				</div>
			</div>


			<!-- modal window -->

			<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content" style="background-color: #fff;">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h3>팀원 참여도</h3>
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
					<div class="modal-content" style="background-color: #fff;">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h3>날짜별 활성화율</h3>
						</div>
						<div class="modal-body">
							<div class="chart-container">
								<canvas id="dialog2"></canvas>
							</div>
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
	<script>
		//상자에 마우스 오버시 해당 차트의 보더가 두꺼워진다.
		$("#box_page").mouseover(function() {

		});

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
	</script>
	<script>
	
	//팀원 참여도 차트

		var options1 = {
				type : 'bar',
				data : {
				    labels : ${membername},

					datasets : [ {
						label : "참여율",
						fill : false,
						lineTension : 0,
						data : ${joinchart},
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

		
	var chart = document.getElementById('myChart1');
	var myChart = new Chart(chart, options1);
		
		var Chart2 = document.getElementById('myChart2').getContext('2d');
		var chart = new Chart(Chart2, {
			type : 'line',
			data : {
				labels : ${projectDate},
				datasets : [ {
					label : "전체",
					backgroundColor : 'rgb(255, 99, 132)',
					borderColor : 'rgb(255, 79, 116)',
					borderWidth : 2,
					pointBorderColor : false,
					data : ${projectuseTotal},
					fill : false,
					lineTension : .4,

				}, {
					label : "자료실",
					fill : false,
					lineTension : .4,
					startAngle : 2,
					data : ${projectuseArchive},
					// , '#ff6384', '#4bc0c0', '#ffcd56', '#457ba1'
					backgroundColor : "transparent",
					pointBorderColor : "#4bc0c0",
					borderColor : '#4bc0c0',
					borderWidth : 2,
					showLine : true,
				}, {
					label : "게시판",
					fill : false,
					lineTension : .4,
					startAngle : 2,
					data : ${projectuseBoard},
					backgroundColor : "transparent",
					pointBorderColor : "#ffcd56",
					borderColor : '#ffcd56',
					borderWidth : 2,
					showLine : true,
				} ]
			},
			options : {
				scales : {
					yAxes : [ {
						ticks : {
							// beginAtZero: true,
							suggestedMin : 10,
							suggestedMax : 100

						}
					} ]
				}
			}
		});
	</script>

	<script>
	
	var options2 = {
			type : 'bar',
			data : {
				labels : ${membername},
				datasets : [ {
					label : "참여율",
					fill : false,
					lineTension : 0,
					data : ${joinchart},
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
			}
		};
		var dialog1 = document.getElementById('dialog1');
		var myChart2 = new Chart(dialog1, options2);

		$("#btn1").click(function() {
			$("#myModal1").modal();

		});

		var dialog2 = document.getElementById('dialog2');
		var myChart2 = new Chart(dialog2, {
			type : 'line',
			data : {
				labels : ${projectDate},
				datasets : [ {
					label : "전체",
					backgroundColor : 'rgb(255, 99, 132)',
					borderColor : 'rgb(255, 79, 116)',
					borderWidth : 2,
					pointBorderColor : false,
					data : ${projectuseTotal},
					fill : false,
					lineTension : .4,
				}, {
					label : "자료실",
					fill : false,
					lineTension : .4,
					startAngle : 2,
					data : ${projectuseArchive},
					// , '#ff6384', '#4bc0c0', '#ffcd56', '#457ba1'
					backgroundColor : "transparent",
					pointBorderColor : "#4bc0c0",
					borderColor : '#4bc0c0',
					borderWidth : 2,
					showLine : true,
				}, {
					label : "게시판",
					fill : false,
					lineTension : .4,
					startAngle : 2,
					data : ${projectuseBoard},
					// , '#ff6384', '#4bc0c0', '#ffcd56', '#457ba1'
					backgroundColor : "transparent",
					pointBorderColor : "#ffcd56",
					borderColor : '#ffcd56',
					borderWidth : 2,
					showLine : true,
				} ]
			}, // Configuration options
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
			}
		});

		$("#btn2").click(function() {
			$("#myModal2").modal();
		});
	</script>

</body>
</html>