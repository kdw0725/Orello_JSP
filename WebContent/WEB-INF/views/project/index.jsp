<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/memberLeft.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/project.css">
<style>


</style>
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<section id="content" class="container">
	<%
		out.flush();
	    RequestDispatcher dheader = request.getRequestDispatcher("/inc/member.do?pseq=" + request.getAttribute("pseq"));
	    dheader.include(request, response);
	%>
	
	<div id="contentRight">
	
        <div class="timelines-chart"></div>
        
        <div id="contributor"></div>
        
        <div id="dataStorage">
            <i class="glyphicon glyphicon-hdd"></i> <span>자료실 이용량</span>
            <div class="progress">
                <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="50"
                aria-valuemin="0" aria-valuemax="100" style="width:50%">
                  5.0GB Used
                </div>
            </div>
        </div>
        
        <div id="memberProgress"> 
            <i class="fas fa-chart-pie"></i> <span>최근 활동</span><br>
        </div>
        
        <div id="projectCondition">
            <i class="fas fa-stopwatch"></i> <span>프로젝트 진행상황</span>
            <div class="progress">
                <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20"
                aria-valuemin="0" aria-valuemax="100" style="width:20%">
                12 Tasks Left
                </div>
            </div>
        </div>
        
    </div>
	
	</section>
	
	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
	<!--  <script src="//code.jquery.com/jquery-1.11.0.min.js"></script> -->
	<script src="<%=request.getContextPath()%>/js/memberLeft.js"></script>
    <script src="<%=request.getContextPath()%>/js/timelines-chart.js"></script>
    <script src="<%=request.getContextPath()%>/js/highcharts.js"></script>

    <script>

  	//타임라인 출력하기 
    //사용자 정의 데이터 생성
    var myData1 = ${arr};
    
    //Date가 문자열로 반환되는 현상 해결을 위해 eval함수 사용
    for (var i=0; i<myData1.length; i++) {
    	for (var j=0; j<myData1[i].data.length; j++) {
    		for(var k=0; k<myData1[i].data[j].data.length; k++) {
    			for(var l=0; l<myData1[i].data[j].data[k].timeRange.length; l++) {
    			//	alert(myData1[i].data[j].data[k].timeRange[l]);
    				myData1[i].data[j].data[k].timeRange[l] = eval(myData1[i].data[j].data[k].timeRange[l]);
    			}
    		}
    	}
    }
  	
    //타임라인을 출력할 DOM 요소 선언
    var timelines = document.getElementsByClassName("timelines-chart")[0];
    //타임라인 생성 후 출력
    const myChart = TimelinesChart();
    myChart.data(myData1).width(840).maxHeight(400).zQualitative(true)(timelines);
    //타임라인 그라데이션 배경화면 지우기
    $(".timelines-chart").children().eq(0).children().eq(0).remove();


    // Build the chart
    
    var options = {
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            colors: [
                '#8dd3c7','#ffffb3','#bebada', '#fb8072','#80b1d3','#fdb462','#b3de69','#fccde5','#d9d9d9','#bc80bd','#ccebc5','#ffed6f'
            ],
            title: {
                text: 'Project Contibutors'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            accessibility: {
                point: {
                    valueSuffix: '%'
                }
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
            series: [{
                name: 'Members',
                colorByPoint: true,
                data: []
            }]
        };
    
    //받아온 데이터로 설정해주기
    options.series[0].data = ${chart};
    Highcharts.chart('contributor', options);
    Highcharts.setOptions({
        colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4']
    });

    //하이차트 라벨?? 없애기
    $(".highcharts-credits").remove();
	
	</script>
</body>
</html>