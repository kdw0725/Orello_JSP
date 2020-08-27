<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/css/allsearch.css">
<style>
</style>
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<section id="content" class="container">

		<div class="search">
			<div class="form-group">
				<div class="input-group">
					<input type="text" class="form-control" value="${searchAll}" id="textbox"><span
						class="input-group-addon" id="searchbtn"><i
						class="glyphicon glyphicon-search search_Icon"></i></span>
				</div>
			</div>
		</div>
		<div id="allSearch">
			<div class="allText">
				<i class="glyphicon glyphicon-search search_Icon"></i><span>통합검색</span><span
					class="searchNumber">(${noticeCount + freeCount + pNoticeCount})</span>
			</div>
		</div>
		<!-- 통합검색 list start -->

		<div class="search_listbox">
			<div class="bd_box">
				<div class="menu">
					<i class="glyphicon glyphicon-search search_icon"></i><span
						class="info">프로젝트 공지사항<span>(${pNoticeCount})</span></span>
				</div>
				<ul class="sh_box" id="sh_box1">
					<!-- 메뉴안내 start -->
					<li><c:forEach items="${pNotice}" var="dto">
							<dl>
								<dt>
									<a href="#">${dto.ptitle}</a>
								</dt>
								<dd>${dto.pcontent}</dd>
							</dl>
						</c:forEach></li>
				</ul>
				<div class="btn_box1">
					<button class="btn btn-info btn2" id="pNoticeMore">더보기</button>
				</div>
			</div>

			<div class="bd_box">
				<div class="menu">
					<i class="glyphicon glyphicon-search search_icon"></i><span
						class="info">전체 공지사항<span>(${noticeCount})</span></span>
				</div>
				<ul class="sh_box" id="sh_box2">
					<!-- 메뉴안내 start -->
					<li><c:forEach items="${notice}" var="dto">
							<dl>
								<dt>
									<a href="#">${dto.ntitle}</a>
								</dt>
								<dd>${dto.ncontent}</dd>
							</dl>
						</c:forEach></li>
				</ul>
				<div class="btn_box1">
					<button class="btn btn-info btn2" id="noticeMore">더보기</button>
				</div>
			</div>

			<div class="bd_box">
				<div class="menu">
					<i class="glyphicon glyphicon-search search_icon"></i><span
						class="info">자유게시판<span>(${freeCount})</span></span>
				</div>
				<ul class="sh_box" id="sh_box3">
					<li><c:forEach items="${free}" var="dto">
							<dl>
								<dt>
									<a href="#">${dto.title}</a>
								</dt>
								<dd>${dto.content}</dd>
							</dl>
						</c:forEach></li>
				</ul>
				<div class="btn_box1">
					<button class="btn btn-info btn3" id="freeMore">더보기</button>
				</div>
			</div>
		</div>

	</section>

	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
	<script>
	
	var begin = ${map.begin+5};
	var end = ${map.end+5};
	
	//전체 공지사항 더보기
	$("#noticeMore").click(function(){
		
		//서버에게 그 다음 5개 게시물을 주세요~ 요청
		$.ajax({
			
			type:"GET",
			url: "/orello/allsearch/allsearchnotice.do",
			data: "begin=" + begin + "&end=" + end + "&searchAll=${searchAll}",
			dataType: "json",
			success: function(result) {
				
				if (result.length == 0) {
					alert("더 이상 가져올 게시물이 없습니다.");
					$("#noticeMore").attr("disabled", true);
					return;
				}
				
				//alert(result.length);
				$(result).each(function(index, item) {
					//게시물 1개씩
					var temp = "";
					temp += "<dl>";
					temp += "<dt><a href='#'>" + item.ntitle + "</dt>";
					temp += "<dd>" + item.ncontent + "</dd>";
					temp += "</dl>";
					
					$("#sh_box2 > li").append(temp);
				});
			},
			error: function(a,b,c) {
				console.log(a,b,c);
			}
			
		});
		
		begin += 5;
		end += 5;
	});
	
	//자유게시판 더보기 
	$("#freeMore").click(function(){
		
		//서버에게 그 다음 5개 게시물을 주세요~ 요청
		$.ajax({
			
			type:"GET",
			url: "/orello/allsearch/allsearchfree.do",
			data: "begin=" + begin + "&end=" + end + "&searchAll=${searchAll}",
			dataType: "json",
			success: function(result) {
				
				if (result.length == 0) {
					alert("더 이상 가져올 게시물이 없습니다.");
					$("#freeMore").attr("disabled", true);
					return;
				}
				
				//alert(result.length);
				$(result).each(function(index, item) {
					//게시물 1개씩
					var temp = "";
					temp += "<dl>";
					temp += "<dt><a href='#'>" + item.title + "</dt>";
					temp += "<dd>" + item.content + "</dd>";
					temp += "</dl>";
					
					$("#sh_box3 > li").append(temp);
				});
			},
			error: function(a,b,c) {
				console.log(a,b,c);
			}
			
		});
		
		begin += 5;
		end += 5;
	});
	
	//프로젝트 공지사항 더보기
	$("#pNoticeMore").click(function(){
		
		//서버에게 그 다음 5개 게시물을 주세요~ 요청
		$.ajax({
			
			type:"GET",
			url: "/orello/allsearch/allsearchpnotice.do",
			data: "begin=" + begin + "&end=" + end + "&searchAll=${searchAll}",
			dataType: "json",
			success: function(result) {
			
				if (result.length == 0) {
					alert("더 이상 가져올 게시물이 없습니다.");
					$("#noticeMore").attr("disabled", true);
					return;
				}
				
				//alert(result.length);
				$(result).each(function(index, item) {
					//게시물 1개씩
					var temp = "";
					temp += "<dl>";
					temp += "<dt><a href='#'>" + item.ntitle + "</dt>";
					temp += "<dd>" + item.ncontent + "</dd>";
					temp += "</dl>";
					
					$("#sh_box1 > li").append(temp);
				});
			},
			error: function(a,b,c) {
				console.log(a,b,c);
			}
			
		});
		
		begin += 5;
		end += 5;
	});
	
	
	
	</script>
</body>
</html>