<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="contentLeft">
        <div id="projectInfo">
            <div id='projectName'></div>
            <div id="projectPeriod"></div>
            <div id="projectContent">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Numquam, nesciunt?</div>
            <div id="projectMember"></div>
            <div id="addMember">
                <a href="#" data-toggle="modal" data-target="#addMemberModal"><i class="glyphicon glyphicon-user"></i><i class="glyphicon glyphicon-plus"></i> <span>Add Member</span></a>
            </div>
        </div>
        <div id="menu">
            <ul class="accordion"><div><i class="glyphicon glyphicon-home"></i>Project Home</div></ul>            
            <ul class="accordion parent"><div><i class="glyphicon glyphicon-calendar"></i>Schedule</div>
                <li class="panel"><div><i class="glyphicon glyphicon-minus"></i>Calendar</div></li>
                <li class="panel"><div><i class="glyphicon glyphicon-minus"></i>Time Line</div></li>   
            </ul>         
            <ul class="accordion"><div><i class="glyphicon glyphicon-check"></i>Check List</div></ul>
            <ul class="accordion parent"><div><i class="glyphicon glyphicon-list-alt"></i>Board</div>
                <li class="panel"><div><i class="glyphicon glyphicon-minus"></i>Notice</div></li>
                <li class="panel"><div><i class="glyphicon glyphicon-minus"></i>Codes</div></li>
                <li class="panel"><div><i class="glyphicon glyphicon-minus"></i>Free</div></li>
            </ul>
            <ul class="accordion"><div><i class="glyphicon glyphicon-hdd"></i>Files</div></ul>
            <ul class="accordion"><div><i class="fas fa-code-branch"></i>Git</div></ul>
            <ul class="accordion"><div><i class="glyphicon glyphicon-comment"></i>Chat</div></ul>
        </div>
    </div>