<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/orello/css/jquery-ui.css">
<link rel="stylesheet" href="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.css">
<link rel="stylesheet" href="/orello/css/checklist.css">

<style>

</style>
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<section id="content" class="container">

        <div id="title">
            <h1>Check List</h1>
            <div class="titleBarFunction" id="listAdd" data-pseq="${pseq}"><i class="glyphicon glyphicon-plus-sign"></i> <span>ADD</span></div>
            <div class="titleBarFunction" id="goToProjectHome" onclick="location.href='/orello/project/index.do?pseq=${pseq}';"><i class="glyphicon glyphicon-home"></i> <span>PROJECT</span></div>
        </div>
        
        <c:forEach items="${list}" var="dto">
        <div class="steps" data-cseq="${dto.seq}">
            <div class="listTitle"><h3>${dto.title}</h3><input type="text" style="display:none;"><i class="glyphicon glyphicon-trash deleteList" data-cseq="${dto.seq}" onclick="deleteList();"></i></div>
            <ul class="sortable connectedSortable">
                
        		<c:forEach items="${dto.list}" var="idto">
	                <li>
	                <c:if test="${idto.completeflag == 0}">
	                    <input id='item${idto.seq}' type='checkbox' onchange='changeCheck(${idto.seq});'/>
	                </c:if>    
	                <c:if test="${idto.completeflag == 1}">
	                    <input id='item${idto.seq}' type='checkbox' checked onchange='changeCheck(${idto.seq});'/>
	                </c:if>    
	                    <div>
	                        <label for='item${idto.seq}'>
	                            <h4>${idto.title}</h4>
	                            <span><i class="glyphicon glyphicon-user"></i> Assigned to: ${idto.name}</span><br>
	                        </label>
	                        <span class="modify" onclick="openModify();" data-ciseq="${idto.seq}"><i class="glyphicon glyphicon-menu-hamburger"></i> <i class="glyphicon glyphicon-paperclip"></i> ${idto.attachcount} <i class="glyphicon glyphicon-comment"></i> ${idto.commentcount}</span>   
	                    </div>
	                </li>
                </c:forEach>
                
            </ul>
            <div class="addCheckList">
                <i class="glyphicon glyphicon-plus-sign" data-cseq="${dto.seq}" onclick="openItemAdd();"></i>
            </div>
        </div>   
        </c:forEach>


    <!-- 체크리스트 항목 수정&확인 모달 -->
    <div class="modal fade" id="checkListModify">
      <form method="POST" action="/orello/checklist/modifychecklistitemok.do" enctype="multipart/form-data">
        <div class="modal-dialog">
       		<div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id="titleArea"></h4>
	                <input type="text" id="titleModify" name="title"></input>
	            </div>
	            <div class="modal-body">
	                <div class="modal-menu">
	                    
	                    <h5>업무 담당자</h5>
	                    <div id="members">
	                    <%-- 멤버 리스트 동적으로 삽입 --%>
	                    </div>
	                    <hr>
	                    <h5>업무 기간</h5>
	                    <div id="datePicker">
	                        <div class="tui-datepicker-input tui-datetime-input tui-has-focus">
	                            <input id="startpicker-input" type="text" aria-label="Date" name="startDate">
	                            <span class="tui-ico-date"></span>
	                            <div id="startpicker-container" style="margin-left: -1px;"></div>
	                        </div>
	                        <span>to</span>
	                        <div class="tui-datepicker-input tui-datetime-input tui-has-focus">
	                            <input id="endpicker-input" type="text" aria-label="Date" name="endDate">
	                            <span class="tui-ico-date"></span>
	                            <div id="endpicker-container" style="margin-left: -1px;"></div>
	                        </div>
	                    </div>
	                    <hr>
	                    <h5>내용</h5>                    
	                    <div class="checkListContent">
	                        <textarea resize="none" name="content"></textarea>
	                    </div>

	                    <hr>
	                    
                        <h5>첨부파일<small onclick="modifyFileAdd();">파일 추가 <i class="glyphicon glyphicon-plus addFile"></i></small></h5>
	                    <div id="fileParent">
							<!-- 파일박스 들어갈 위치 -->
	                    </div>
	                    <hr>
	                    
	                    <h5>댓글</h5>                    
	                    <div class="be-comment-block">
	                    <!-- 댓글 동적으로 추가 -->
	                    </div>
	                        <!-- 댓글 추가 -->
	                        <div class="form-group">
	                            <textarea class="form-input" id="commentArea" placeholder="내용을 입력하세요."></textarea>
	                            <input type="button" id="commentEnter"class="btn btn-info pull-right" value="Enter"></input>
	                        </div>
	                        
	                    </div>
	                </div>
	            </div>
	            <div class="modal-footer">
	            	<input type="hidden" name="ciseq" id="hiddenCiseq"> 
	            	<input type="hidden" name="paseq" id="hiddenPaseq" value="${paseq}"> 
	                <button type="button" class="btn btn-info" id="btnDelete" data-dismiss="modal">Delete</button>
	                <button type="submit" class="btn btn-info">Save</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	            </div>
          </div>
       </form>
   </div>

    <!-- 체크리스트 항목 추가 모달 -->
    <div class="modal fade" id="checkListItemAdd">
        <div class="modal-dialog">
          <div class="modal-content">
          
			<form method="POST" action="/orello/checklist/addchecklistitemok.do" enctype="multipart/form-data" name="checklistitemadd">          
	            <div class="modal-header">
	              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	              <input type="text" id="itemtitle" placeholder="제목을 입력하세요." name="title"></input>
	            </div>
	          
	            <div class="modal-body">
	                <div class="modal-menu">
	                    <h5>업무 담당자</h5>
	                    <div id="memberlist">
						
	                    </div>
	                    <hr>
	                    
	                    <h5>업무 기간</h5>
	                    <div id="datePicker">
	                        <div class="tui-datepicker-input tui-datetime-input tui-has-focus">
	                            <input id="startpicker-input2" type="text" aria-label="Date" name="startdate">
	                            <span class="tui-ico-date"></span>
	                            <div id="startpicker-container2" style="margin-left: -1px;"></div>
	                        </div>
	                        <span>to</span>
	                        <div class="tui-datepicker-input tui-datetime-input tui-has-focus">
	                            <input id="endpicker-input2" type="text" aria-label="Date" name="enddate">
	                            <span class="tui-ico-date"></span>
	                            <div id="endpicker-container2" style="margin-left: -1px;"></div>
	                        </div>
	                    </div>
	                    <hr>
	                    
	                    <h5>내용</h5>                    
	                    <div class="checkListContent">
	                        <textarea resize="none" name="content" id="checkListItemContent"></textarea>
	                    </div>
	                    <hr>
	                    
	                    <h5>첨부파일<small onclick="fileAdd();">파일 추가 <i class="glyphicon glyphicon-plus addFile"></i></small></h5>
	                    <div id="fileParent">
							<!-- 파일박스 들어갈 위치 -->
	                    </div>
	                </div>
	            </div><!-- ### modal.body 끝 -->
	            
	            <div class="modal-footer">
	            	<input type="hidden" name="cseq" id="hiddenCseq"> 
	            	<button type="submit" class="btn btn-info" id="addListBtn">Save</button>
	            	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	            </div>
            </form>
            
          </div>
        </div>
    </div>

	
	</section>
	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
	
	<script src="/orello/js/jquery-ui.js"></script>
	<script src="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.js"></script>
	<script src="/orello/js/checklist.js"></script>
	<script>
	
	</script>
</body>
