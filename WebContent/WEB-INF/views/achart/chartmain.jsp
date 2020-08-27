<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/orello/css/achart/chartmain.css">

<style>

</style>

<%@ include file="/WEB-INF/views/inc/asset.jsp"%>

</head>
<body>
	<jsp:useBean id="now" class="java.util.Date"/>
	<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="nowdate"/>
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
					<div onclick="location.href='/orello/aproject/projectlist.do'">
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

				<!-- Email 카테고리 추가 -->
				<!-- <ul class="accordion">
                <div onclick="location.href='http:mail.google.com'">
                    <i class="glyphicon glyphicon-envelope"></i>Email
                </div>
            </ul> -->

			</div>
		</div>
		<div id="contentRight">
			

			<div id="titleContainer">
				<div id="titleicon"></div>
				<div id="title">Summary Chart</div>
			</div>

			<!-- 실시간 접속 인원 차트 박스 -->
			<div id="user_time"></div>

			<!-- 자주 사용하는 PL 차트 박스 -->
			<div id="pl"></div>

			<!-- 날짜별 포인트 사용량 차트 박스 -->
			<div id="point_date"
				onclick="location.href='/orello/achart/apoint.do'">
				<span>포인트 사용량</span>

				<div id="pbarChart1"></div>
				<div id="pbarChart2"></div>
				<div id="pbarChart3"></div>
				<div id="pbarText">
					<span><strong>3,560P</strong></span>
					<p>${nowdate}</p>
				</div>
			</div>

			<!-- 날짜별 회원가입 수 차트 박스 -->
			<div id="join_date"
				onclick="location.href='/orello/achart/ajoin.do'">
				<span>날짜별 회원가입 수</span>
				<div id="jbarChart1"></div>
				<div id="jbarChart2"></div>
				<div id="jbarChart3"></div>
				<div id="jbarText">
					<span><strong>3.5k</strong></span>
					<p>${nowdate}</p>
				</div>
			</div>

			<!-- 월별 프로젝트 생성 수 차트 박스 -->
			<div id="project_month">
				<span>월별 프로젝트 생성수</span>
			</div>

			<!-- 실시간 채팅 빈도수 차트 박스 -->
			<div id="chating_time"></div>

			<!-- ////////////Modal//////////// -->
			<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content" style="background-color: #fff;">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h3>자주 사용하는 Programming Language</h3>
						</div>
						<div class="modal-body">
							<div class="chart-container" id="dialog1">
								<!-- <canvas id="dialog1"></canvas> -->
							</div>
						</div>
					</div>
				</div>
			</div>



			<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content" style="background-color: #fff;">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h3>날짜별 회원가입 수</h3>
						</div>
						<div class="modal-body">
							<div class="chart-container">
								<canvas id="dialog3"></canvas>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="modal fade" id="myModal4" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content" style="background-color: #fff;">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h3>날짜별 채팅 빈도수</h3>
						</div>
						<div class="modal-body">
							<div class="chart-container" id="dialog4">
								<!-- <canvas id="dialog4"></canvas> -->
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="modal fade" id="myModal5" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content" style="background-color: #fff;">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h3>월별 프로젝트 생성수</h3>
						</div>
						<div class="modal-body">
							<div class="chart-container" id="dialog5">
								<!-- <canvas id="dialog5"></canvas> -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
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
	</script>


	<script src="https://code.highcharts.com/modules/variable-pie.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
	<script src="https://code.highcharts.com/modules/export-data.js"></script>
	<script src="https://code.highcharts.com/modules/accessibility.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
	<script src="/orello/js/highcharts.js"></script>
	<script type="text/javascript">
	
	
	var options = {
			title : {
				text : "날짜별 접속 인원",
			},

	/* 		subtitle : {
				text : "오전 9시 측정",
			}, */

			yAxis : {
				title : {
					text : "접속 인원(명)",
				},
			},

	 		//xAxis : {
	/* 			accessibility : {
					rangeDescription : "Range: 2010 to 2017",
				}, */
			//},
			
			xAxis: {
		        type: 'datetime',
		        dateTimeLabelFormats: {
		        	day: '%m-%d'
		        }
		    },

			legend : {
				layout : "vertical",
				align : "right",
				verticalAlign : "middle",
			},

			plotOptions : {
				series : {
					label : {
						connectorAllowed : false,
					},
					pointStart : Date.UTC(2020, 7, 13),
					pointInterval: 24 * 3600 * 1000
				},
			},
			
			
			

			series : [ {
				name : "인원수",
				/* data : [ 70, 69, 71, 73, 75, 79, 86, 72, 69, 68, 70, 71 ], */
			} ],

			responsive : {
				rules : [ {
					condition : {
						maxWidth : 500,
					},
					chartOptions : {
						legend : {
							layout : "horizontal",
							align : "center",
							verticalAlign : "bottom",
						},
					},
				}, ],
			},
			user_time : {
				bgColor : "#eee",
			},
		}
	
	
	

		$.ajax({
			type: "GET",
			url: "/orello/achart/achartmainok.do",
			dataType: "json",
			success:function(result){
				
				//var item = result.split(",");
				
			//	alert(result);
				
				
				options.plotOptions.series.pointStart = Date.UTC(2020, result[0].substring(0,2)-1, result[0].substring(3,5));
				
				//받아온 데이터
				
				//options.series[0].data = result;
				//실시간 접속 인원 차트
				Highcharts.chart("user_time", options);
			},
			error:function(a,b,c){
				console.log(a,b,c);
			}
		});
	
	$.ajax({
		type: "GET",
		url: "/orello/achart/achartmainoksub.do",
		dataType: "json",
		success:function(result){
			//alert(result);
			options.series[0].data = result;
			Highcharts.chart("user_time", options);
		},
		error:function(a,b,c){
			console.log(a,b,c);
		}
	});

	
	
	
	
	
	
	
	//PL 사용량 차트 ajax
	var dia1;
		$.ajax({
			type : "GET",
			url : "/orello/achart/amainok.do",
			dataType : "json",
			success : function(result) {
				plChart.series[0].data = result;
				Highcharts.chart("pl", plChart);
				plChart2.series[0].data = result;
				dia1 = Highcharts.chart("dialog1", plChart2);
			},
			error : function(a, b, c) {
				console.log(a, b, c);
			}
		})

		

		//자주 사용하는 pl 통계 차트
		var plChart = {
			chart : {
				type : "bar",
			},
			title : {
				text : "자주 사용하는 PL",
			},
			xAxis : {
				categories : [ "C#", "JavaScript", "Java", "Python", "CSS",
						"C++", "HTML", "Swift" ],
			},
			yAxis : {
				min : 0,
			/* title : {
				text : "Total fruit consumption",
			}, */
			},
			legend : {
				reversed : true,
			},
			plotOptions : {
				series : {
					stacking : "normal",
				},
			},
			series : [ {
				name : "회원",
			//data : [ 5, 3, 4, 7, 2, 8, 2, 3 ],
				color : "#BEBADA",
			}, ],
		}
		
		
		//pl 차트 모달창
		var plChart2 = {
				chart : {
					type : "bar",
				},

				xAxis : {
					categories : [ "C#", "JavaScript", "Java", "Python", "CSS",
							"C++", "HTML", "Swift" ],
				},
				yAxis : {
					min : 0,

				},
				legend : {
					reversed : true,
				},
				plotOptions : {
					series : {
						stacking : "normal",
					},
				},
				series : [ {
					name : "회원",
					//data : [ 5, 3, 4, 7, 2, 8, 2, 3  ],
					color : "#BEBADA",
				}, ],
			}
		
		
		// 자주 사용하는 프로그래밍 언어 모달창 차트
		$("#pl").click(function() {
			$("#myModal1").modal();
		});
	
		
		//dia1.setSize(600, 500);
		
		
		
		//월별 프로젝트 생성 수 차트
		var projectChart;
		var dia5;
		
		$.ajax({
			type: "GET",
			url: "/orello/achart/aprojectmainok.do",
			dataType: "json",
			success: function(result){
				monproject.series[0].data = result;
				projectChart = Highcharts.chart("project_month", monproject);
				monproject2.series[0].data =result;
				dia5 = Highcharts.chart("dialog5", monproject2);
				//alert(result);
			},
			error: function(a,b,c){
				console.log(a,b,c);
			}
		})
		
		
		//월별 프로젝트 생성 차트
		
		var monproject = {
			chart : {
				type : "line",
			},
			title : {
				text : "월별 프로젝트 생성 수",
			},
		/* 	xAxis : {
				title: {
					text : "월",
				},
				min : 1,
				max : 12,
			}, */
	 		yAxis : {
				title : {
					text : "생성수(수)",
				},
				min : 0,
				max : 100,
			}, 
			plotOptions : {
				line : {
					dataLabels : {
						enabled : true,
					},
					enableMouseTracking : false,
				},
			},
			series : [ {
				name : "프로젝트",
				//data : [ 36, 69, 95 ],
				color : "#5cb85c",
			}, ],
		}
		
		
		
		var monproject2 = {
				chart : {
					type : "line",
				},
				xAxis : {
					categories : [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월",
							"9월", "10월", "11월", "12월", ],
				},
				yAxis : {
					title : {
						text : "생성수(수)",
					},
					min : 0,
				// max: 100,
				},
				plotOptions : {
					line : {
						dataLabels : {
							enabled : true,
						},
						enableMouseTracking : false,
					},
				},
				series : [
						{
							name : "프로젝트",
						/* 	data : [ 7850, 9620, 10020, 1352, 68250, 81002, 9568,
									4859, 11235, 15685, 36565, 20101, ], */
							color : "#5cb85c",
						}, ],
			}
		
		
		//월별 프로젝트 생성수 모달창 차트

		

		
		$("#project_month").click(function() {

			$("#myModal5").modal();
		});

		//dia5.setSize(550, 400);

		
		
		
		
		
		
		//실시간 채팅 빈도수 차트
		var chating = {
				title : {
					text : "날짜별 채팅 빈도수",
				},

				subtitle : {
					text : "오전 9시 측정",
				},

				yAxis : {
					title : {
						text : "빈도수(번)",
					},
				},

				xAxis: {
			        type: 'datetime',
			        dateTimeLabelFormats: {
			        	day: '%m-%d'
			        }
			    },

				legend : {
					layout : "vertical",
					align : "right",
					verticalAlign : "middle",
				},

				plotOptions : {
					series : {
						label : {
							connectorAllowed : false,
						},
						pointStart : Date.UTC(2020, 7, 13),
						pointInterval: 24 * 3600 * 1000
					},
				},

				series : [
						{
							name : "빈도수",
							data : [ 100, 862, 732, 4832, 4246, 5835, 19853, 19885,
									24854, 9425, 12123, 15321, ],
							color : "#D9534F",
						}, ],

				responsive : {
					rules : [ {
						condition : {
							maxWidth : 500,
						},
						chartOptions : {
							legend : {
								layout : "horizontal",
								align : "center",
								verticalAlign : "bottom",
							},
						},
					}, ],
				},
				user_time : {
					bgColor : "#eee",
				},
			}
		
		
		$.ajax({
			type:"GET",
			url: "/orello/achart/achartmainok.do",
			datatype: "json",
			success: function(result){
				//alert(result);
				chating.plotOptions.series.pointStart = Date.UTC(2020, result[0].substring(0,2)-1, result[0].substring(3,5));
				chating2.plotOptions.series.pointStart = Date.UTC(2020, result[0].substring(0,2)-1, result[0].substring(3,5));
				Highcharts.chart("chating_time", chating);
				Highcharts.chart("dialog4", chating2);
			},
			error: function(a,b,c){
				console.log(a,b,c);
			}
		});
		
		$.ajax({
			type:"GET",
			url: "/orello/achart/achartmainokssub.do",
			datatype: "json",
			success: function(result){
				chating.series[0].data = result;
				chating2.series[0].data = result;
				Highcharts.chart("chating_time", chating);
				Highcharts.chart("dialog4", chating2);
			},
			error: function(a,b,c){
				console.log(a,b,c);
			}
		})
		
		

		//chat.setSize(320, 270);


		// 실시간 채팅 빈도수 모달창 차트
		var chating2 = {

				yAxis : {
					title : {
						text : "빈도수(번)",
					},
				},

				xAxis: {
			        type: 'datetime',
			        dateTimeLabelFormats: {
			        	day: '%m-%d'
			        }
			    },

				legend : {
					layout : "vertical",
					align : "right",
					verticalAlign : "middle",
				},

				plotOptions : {
					series : {
						label : {
							connectorAllowed : false,
						},
						pointStart : Date.UTC(2020, 7, 13),
						pointInterval: 24 * 3600 * 1000
					},
				},

				series : [
						{
							name : "빈도수",
							/* data : [ 100, 862, 732, 4832, 4246, 5835, 19853, 19885,
									24854, 9425, 12123, 15321, ], */
							color : "#D9534F",
						}, ],

				responsive : {
					rules : [ {
						condition : {
							maxWidth : 500,
						},
						chartOptions : {
							legend : {
								layout : "horizontal",
								align : "center",
								verticalAlign : "bottom",
							},
						},
					}, ],
				},
			}
		

		
		/* var dia4 = Highcharts.chart("dialog4", chating2); */

		$("#chating_time").click(function() {
			$("#myModal4").modal();
		});

		//dia4.setSize(600, 500);

		
	
		$(".highcharts-credits").remove();
	</script>
</body>
</html>