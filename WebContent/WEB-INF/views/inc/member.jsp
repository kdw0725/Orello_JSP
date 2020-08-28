<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="contentLeft">
        <div id="projectInfo">
            <div id='projectName'>[${dto.name}]</div>
            <div id="projectPeriod"><span>${dto.startdate} ~ ${dto.enddate}</span><br></div>
           	<div id="projectContent">${dto.description}</div>            
            <div id="projectMember">
            <c:forEach items="${dto.mlist}" var="member"> 
				<div class='profilePic' style="background-image: url('/orello/images/${member.profilepic}');"></div>            
            </c:forEach>
            </div>
            <div id="addMember">
                <a href="#" data-toggle="modal" data-target="#addMemberModal"><i class="glyphicon glyphicon-user"></i><i class="glyphicon glyphicon-plus"></i> <span>Add Member</span></a>
            </div>
        </div>
        <div id="menu">
            <ul class="accordion" onclick="location.href='/orello/project/index.do?pseq=${pseq}';"><div><i class="glyphicon glyphicon-home"></i>Project Home</div></ul>            
            <ul class="accordion" onclick="location.href='/orello/calendar/calendar.do?pseq=${pseq}';"><div><i class="glyphicon glyphicon-calendar"></i>Schedule</div></ul>         
            <ul class="accordion" onclick="location.href='/orello/checklist/list.do?pseq=${pseq}';"><div><i class="glyphicon glyphicon-check"></i>Check List</div></ul>
            <ul class="accordion parent"><div><i class="glyphicon glyphicon-list-alt"></i>Board</div>
                <li class="panel"><div><i class="glyphicon glyphicon-minus"></i>Notice</div></li>
                <li class="panel" onclick="location.href='/orello/codeboard/list.do?pseq=${pseq}';"><div><i class="glyphicon glyphicon-minus"></i>Codes</div></li>
                <li class="panel" onclick="location.href='/orello/board/list.do?pseq=${pseq}';"><div><i class="glyphicon glyphicon-minus"></i>Free</div></li>
            </ul>
            <ul class="accordion"><div><i class="glyphicon glyphicon-hdd"></i>Files</div></ul>
            <ul class="accordion"><div><i class="glyphicon glyphicon-comment"></i>Chat</div></ul>
        </div>
    </div>