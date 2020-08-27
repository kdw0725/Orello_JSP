<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="380478341630-8p93j9l00a6tq6s1vlb7tnjlnqts1vj2.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
 <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/orello/index.do"><img src="<%= request.getContextPath() %>/images/logo.png" alt="Logo" id="logo-img" style="width: 20px;" /></a>
          </div>
      
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
              <li class="active"><a href="/orello/member/index.do"><i class="glyphicon glyphicon-home"></i>&nbsp;Home</span><span class="sr-only">(current)</span></a></li>
              <li><a href="/orello//notice/notice.do"><i class="glyphicon glyphicon-bullhorn"></i>&nbsp; Notice</span></a></li>
              <li><a href="#"><i class="glyphicon glyphicon-folder-open"></i>&nbsp; Project</span></a></li>
              <li><a href="/orello/faq/faq.do"><i class="glyphicon glyphicon-question-sign"></i>&nbsp; QnA</span></a></li>
            </ul>
            <form method="GET" action="/orello/allsearch/allsearch.do" class="navbar-form navbar-right" role="search" id="searchForm">
              <div class="form-group">
                <input type="text" class="form-control" placeholder="검색어를 입력하세요." name="searchAll" id="searchAll" required value="${searchAll}">
              </div>
              <button type="button" class="btn btn-default" style="cursor:pointer;" onclick="$('#allSearchForm').submit();"><i class="glyphicon glyphicon-search"></i></button>
            </form>
            <ul class="nav navbar-nav navbar-right">
              <li class="dropdown">
              	<c:if test="${seq != null }">
              	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">${member.name}<span class="caret"></span></a>
              	</c:if>
              	<c:if test="${seq == null }">
                	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">User Name<span class="caret"></span></a>
              	</c:if>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="#">Profile</a></li>
                  <li><a href="#">Friends</a></li>
                  <li class="divider"></li>
                  <c:if test="${seq != null }">
                  	<li><a id="signout" onclick="signOut()" style="cursor: pointer;">Log out</a></li>
                  </c:if>
                  <c:if test="${seq == null }">
					<li><a href="/orello/member/login.do">Log In</a></li>
                  </c:if>
                  
                </ul>
              </li>
            </ul>
            <span class="nav navbar-nav navbar-right" id="alermArea">
              <i class="far fa-envelope" id="chatOpen"></i>
              <span class="alermCount">1</span>
              <i class="far fa-bell"></i>
              <span class="alermCount">1</span>
            </span>
          </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
      </nav>
      
	    <div id="chatModal">
	    	<input type="hidden" id="hiddenMseq">
	    	<input type="hidden" id="hiddenCseq">
	        <div class="modal fade">
	            <div class="modal-dialog" id="modal-dialog">
	                <div class="modal-content">
	                    <div class="container clearfix">
	                        <div class="people-list" id="people-list">
	                            <div class="search">
	                                <input type="text" placeholder="search" />
	                                <i class="fa fa-search"></i>
	                            </div>
	                            <div class="listConvert">
	                                <div id="toFriend" class="convertBtn" onclick="getFriendList();">Friend</div>
	                                <div id="toHistory" class="convertBtn" onclick="getProjectList();">Project</div>
	                            </div>
	                            <ul class="list" id="friendlist">
								<!-- 친구목록, 프로젝트 목록 -->
	                         	</ul>
	                        </div>   

							<!-- 오른쪽 채팅창 -->	
	                        <div class="chat">
	                        
	                        	<!-- 채팅 헤더 -->
	                            <div class="chat-header clearfix">
	                                <img src="/orello/images/9.png" alt="avatar" class="listTarget"/>
	                                <div class="chat-about">
	                                    <div class="chat-with"></div>
	                                    <!--  <div class="chat-num-messages">already 1902 messages</div> -->
	                                </div>
	                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                            </div> <!-- end chat-header -->
	
	                            <!-- 채팅 내용창 -->
	                            <div class="chat-history">
	                                <ul>
	
										<!-- 상대방이 입력중일때.. 
	                                    <li>
	                                        <div class="message-data">
	                                            <span class="message-data-name"><i class="fa fa-circle online"></i>Vincent</span>
	                                            <span class="message-data-time">10:31 AM, Today</span>
	                                        </div>
	                                        <i class="fa fa-circle online"></i>
	                                        <i class="fa fa-circle online" style="color: #AED2A6"></i>
	                                        <i class="fa fa-circle online" style="color:#DAE9DA"></i>
	                                    </li>
	                                    -->
	                                    
	                                </ul>
	                            </div> <!-- end chat-history -->
	
	                            <!-- 채팅 전송창 -->
	                            <div class="chat-message clearfix">
	                                <textarea name="message-to-send" id="message-to-send" placeholder="Type your message" rows="3"></textarea>
	                                <label for="attachFile"><i class="fa fa-file-o"></i></label><input type="file" id="attachFile" onchange="javascript:document.getElementById('attachmentName').value = this.value"> &nbsp;&nbsp;&nbsp;
	                                <label for="attachImg"><i class="fa fa-file-image-o"></i></label><input type="file" id="attachImg" onchange="javascript:document.getElementById('attachmentName').value = this.value">
	                                <input type="text" id="attachmentName" name="attachmentName">
	                                <button type="button" class="btn btn-info" id="btnSend">Send</button>
	                            </div> <!-- 채팅전송창 끝 -->
	                            
	                        </div><!-- end chat -->
	                    </div> <!-- end container -->
	
	                    <!-- 헤더 프로필 이미지 누르면 나타나는 채팅 멤버 목록 -->
	                    <ul id="chatMemberList" style="display:none;">
	                        <li class="chatMember">
	                            <img src="../images/0.png" alt="avatar">
	                            <div class="about">
	                                <div class="memberName">Member Name</div>
	                                <div class="memberRole">Team Leader</div>
	                            </div>
	                        </li>
	                        <li class="chatMember">
	                            <img src="../images/2.png" alt="avatar">
	                            <div class="about">
	                                <div class="memberName">Member Name</div>
	                                <div class="memberRole">Team Member</div>
	                            </div>
	                        </li>
	                        <li class="chatMember">
	                            <img src="../images/3.png" alt="avatar">
	                            <div class="about">
	                                <div class="memberName">Member Name</div>
	                                <div class="memberRole">Team Member</div>
	                            </div>
	                        </li>
	                        <li class="chatMember">
	                            <img src="../images/4.png" alt="avatar">
	                            <div class="about">
	                                <div class="memberName">Member Name</div>
	                                <div class="memberRole">Team Member</div>
	                            </div>
	                        </li>
	                    </ul>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>