<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/memberLeft.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/mypage_main.css">
<style>
</style>
<%@ include file="/inc/asset.jsp"%>
</head>
<body>
	<%@ include file="/inc/header.jsp"%>
	<section id="content" class="container">
		<%@ include file="/inc/member.jsp"%>

		<div id="contentRight">
			<div></div>
			<h1>My Page</h1>
			<div id="top">
				<div id="profile">
					<div id="edit_icon">
						<li class="glyphicon glyphicon-cog"></li>
					</div>
					<div id="edit_img">
						<input type="file" />
						<div id="edit_btn">
							<button class="btn btn-info">save</button>
							<button class="btn btn-info">close</button>
						</div>
					</div>

					<img src="/orello/images/1.png" alt="" />
					<div>${dto.name}</div>
					<div>${dto.statusmsg}</div>
				</div>
				<div id="side_profile">
					<div class="edit_before">
						<span>name:</span> <input type="text" value="${dto.name}" readonly />
						<button class="btn btn-info">
							<li class="glyphicon glyphicon-pencil"></li>
						</button>
					</div>
					<div class="edit">
						<span>name:</span> <input type="text" value="" />
						<button class="btn btn-info save">save</button>
						<button class="btn btn-info cancel">cancel</button>
					</div>

					<div class="edit_before">
						<span>email:</span> <input type="text" value="${dto.email}"
							readonly />
						<button class="btn btn-info">
							<li class="glyphicon glyphicon-pencil"></li>
						</button>
					</div>
					<div class="edit">
						<span>email:</span> <input type="text" value="" />
						<button class="btn btn-info save">save</button>
						<button class="btn btn-info cancel">cancel</button>
					</div>

					<div class="edit_before">
						<span>tel:</span><input type="text" value="${dto.tel}" readonly />
						<button class="btn btn-info">
							<li class="glyphicon glyphicon-pencil"></li>
						</button>
					</div>
					<div class="edit">
						<span>tel:</span> <input type="text" value="" />
						<button class="btn btn-info save">save</button>
						<button class="btn btn-info cancel">cancel</button>
					</div>
					<div>
						<span>point:</span> ${dto.point}p
						<button class="btn btn-info">
							<li class="glyphicon glyphicon-shopping-cart"></li>
						</button>
					</div>
				</div>
			</div>

			<div id="page_content">
				<div id="friend">
					<div>My Friend</div>
					<div id="friend_box">
						<table class="tbl">
							<tr>
								<th>friend</th>
								<th></th>
								<th></th>
							</tr>

							<c:forEach items="${flist}" var="dto">
								<tr>
									<td><img src="/orello/images/2.png" alt="" /></td>
									<td>${dto.name}
										<div>${dto.statusmsg}</div>
									</td>
									<td>
										<button type="button" class="btn btn-info" id="unfollow"
											onclick="unfollow(${dto.friend_seq})">unfollow</button>

									</td>
								</tr>
							</c:forEach>


						</table>
					</div>
				</div>

				<div id="project">
					<div>My Project</div>
					<div id="project_box">
						<table class="tbl">
							<tr>
								<th>project name</th>
								<th>period</th>
								<th></th>
							</tr>

							<c:forEach items="${plist}" var="dto">
								<tr>
									<td>${dto.name}</td>
									<td>${dto.startdate}~${dto.enddate}</td>
									<td>
										<button type="button" class="btn btn-info" data-toggle="modal"
											data-target="#myModal" onclick="showDetail(${dto.pseq})">
											detail</button>
									</td>
								</tr>
							</c:forEach>

						</table>
					</div>
				</div>
			</div>

			<!-- modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Project Detail</h4>
						</div>
						<div class="modal-body">
							<div id="modal_content" style="height: 300px;">
								<span> </span> 
								<span> </span> 
								<span>
									<li class="glyphicon glyphicon-calendar"></li> 
									<span> </span>
								</span> 
								<span>
									<li class="glyphicon glyphicon-bookmark"
									style="color: #5bc0de;"></li> <span> </span>
								</span> 
								<span>Attendant</span>
								<div id="attendant_list">
								
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-info">more</button>
						</div>
					</div>
				</div>
			</div>


		</div>


	</section>

	<%@ include file="/inc/footer.jsp"%>
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/memberLeft.js">
	
	
	</script>

	<script>
    $("#side_profile > div.edit_before > button").click(function () {
        $(this).parent().css("display", "none");
        $(this).parent().next().css("display", "block");
        $(this).parent().next().children("input").focus();
        $(this).parent().next().children("input").val($(this).prev().val());
    });

    $("#side_profile > div.edit > .save").click(function () {
        // console.log($(this).parent().prev().children("input").val());
        // console.log($(this).prev().val());

        $(this).parent().prev().children("input").val($(this).prev().val());

        $(this).parent().css("display", "none");
        $(this).parent().prev().css("display", "block");
    });
    $("#side_profile > div.edit > .cancel").click(function () {
        $(this).parent().css("display", "none");
        $(this).parent().prev().css("display", "block");
    });

    //-------개인정보 수정-------------------------
	
    
 	function showDetail(pseq) {
	
 		$("#attendant_list > div").remove();  	
    	
    $.ajax({
    	type: "GET",
    	url: "/orello/mypage/projectdetail.do",
    	data: "seq=" + pseq,
    	success: function(result) {
    		//alert(result);
    		//console.log(result.name)
    		$("#modal_content > span:nth-child(1)").html(result.name);
    		$("#modal_content > span:nth-child(2)").html("this is project description");
    		$("#modal_content > span:nth-child(3) > span")
    						.html(result.startdate + " ~ " + result.enddate);
    		
    		$("#modal_content > span:nth-child(4) > span")
    						.html(result.type);
    		
				for(var i=0 in result.member){
					$("#attendant_list")
						.append(("<div><img src='/orello/images/"+i+".png'><span>"+result.member[i]+"</span></div>"));					
				}
			
    	},
    	error: function(a,b,c) {	
    		console.log(a,b,c);
    	}
    });
    	
    	
    }
    
    
    
    
    function unfollow(seq) {
    	
    	var flag = confirm("delete this friend?");
    	
    	
    	if(flag){
    	$.ajax ({
    		
    		type: "GET",
    		url: "/orello/mypage/deleteFriend.do",
    		data: "fseq=" + seq,
    		success: function(result) {
    			if(result == 0) {
    				alert("삭제 실패");
    			}else {
    				
    			}
    		},
    		error: function(a,b,c) {
    			console.log(a,b,c);
    		}
    		
    		
    		
    	});//ajax
    	}
    	
    	
    }
    
    
    

    $("#project td > button").click(function () {
        // alert();
    });

    //profile image edit
    $("#profile > #edit_icon").click(function () {
        $("#profile > #edit_img").css("display", "block");
    });

    $("#profile > #edit_img > div > button:last-child").click(function () {
        $("#profile > #edit_img").css("display", "none");
    });

    //move payment page
    $("#side_profile > div:last-child > button").click(function () {
        location.href = "payment.html";
    });
    
    
    
    
    
    
    
</script>
</body>
</html>