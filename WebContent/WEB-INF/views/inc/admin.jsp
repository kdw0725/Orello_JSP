<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="contentLeft">
			<div id="projectInfo">
				<div id="projectName"></div>
				<div id="projectPeriod">${dto.id}</div>
				<div id="projectContent">lorem1234@gmail.com</div>
				<div id="projectMember"></div>
			</div>
			<div id="menu">
				<ul class="accordion" onclick="location.href='/orello/achart/amain.do';">
					<div>
						<i class="glyphicon glyphicon-home"></i>Home
					</div>
				</ul>
				<ul class="accordion">
					<div onclick="location.href='/orello/aproject/projectlist.do';">
						<i class="glyphicon glyphicon-calendar"></i>Project
					</div>
					<!-- <li class="panel"><div><i class="glyphicon glyphicon-minus"></i></div></li>
                <li class="panel"><div><i class="glyphicon glyphicon-minus"></i></div></li>    -->
				</ul>
				<ul class="accordion" onclick="location.href='/orello/amember/userlist.do';">
					<div>
						<i class="glyphicon glyphicon-check"></i>User
					</div>
				</ul>
				<ul class="accordion parent">
					<div>
						<i class="glyphicon glyphicon-list-alt"></i>Chart
					</div>
					<li class="panel">
						<div >
							<i class="glyphicon glyphicon-minus"></i>Archive
						</div>
					</li>
					<li class="panel" onclick="location.href='/orello/achart/apoint.do';">
						<div>
							<i class="glyphicon glyphicon-minus"></i>Point
						</div>
					</li>
					<li class="panel">
						<div>
							<i class="glyphicon glyphicon-minus"></i>New Projects
						</div>
					</li>
					<li class="panel" onclick="location.href='/orello/achart/ajoin.do';">
						<div>
							<i class="glyphicon glyphicon-minus"></i>New Users
						</div>
					</li>
				</ul>
				<ul class="accordion">
					<div>
						<i class="glyphicon glyphicon-hdd"></i>QnA
					</div>
				</ul>
				<!-- <ul class="accordion"><div><i class="fas fa-code-branch"></i>Git</div></ul> -->
				<ul class="accordion" onclick="location.href='/orello/faq/faq.do';">
					<div>
						<i class="glyphicon glyphicon-user"></i>FAQ
					</div>
				</ul>
				<ul class="accordion" onclick="location.href='/orello/notice/notice.do';">
					<div>
						<i class="glyphicon glyphicon-user"></i>Notice
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